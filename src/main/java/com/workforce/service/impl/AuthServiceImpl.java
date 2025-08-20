package com.workforce.service.impl;

import com.workforce.dto.auth.AuthDto;
import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.User;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.UserMapper;
import com.workforce.param.auth.AuthParam;
import com.workforce.param.auth.RefreshTokenParam;
import com.workforce.repository.UserRepository;
import com.workforce.security.JwtService;
import com.workforce.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthDto login(AuthParam request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userDto=userMapper.entityToDto(user);

        UserDetails userDetails = mapToUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        return AuthDto.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userDto)
                .build();
    }

    @Override
    public AuthDto refreshToken(RefreshTokenParam req) {
        var user = userRepository.findByEmailIgnoreCase(req.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        UserDetails userDetails = mapToUserDetails(user);

        if (jwtService.isTokenValid(req.getRefreshToken(), userDetails)) {
            var accessToken = jwtService.generateToken(userDetails);
            return AuthDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(req.getRefreshToken())
                    .build();
        }
        throw new RuntimeException("Invalid refresh token");
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = userRepository.findByEmailIgnoreCase(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UserDetails userDetails = mapToUserDetails(user);

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateToken(userDetails);

                var authResponse = AuthDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private UserDetails mapToUserDetails(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase()));
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getActive()) // Assuming you have a field active
                .build();
    }
}
