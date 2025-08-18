package com.workforce.service.impl;


import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.User;
import com.workforce.mapper.UserMapper;
import com.workforce.param.master.UserParam;
import com.workforce.repository.UserRepository;
import com.workforce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto create(UserParam param) throws Exception {
        User entity = userMapper.toEntity(param);
        entity = userRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public UserDto getById(UUID id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return entityToDto(entity);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> role = userRepository.findAll();
        return role.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto update(UserParam param) throws Exception {
        User existingUser = userRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + param.getId()));

        userMapper.mergeUserInfo(existingUser, param);
        User updatedMenu = userRepository.save(existingUser);
        return entityToDto(updatedMenu);
    }

    @Override
    @Transactional
    public UserDto statusUpdate(UUID id) throws Exception {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        entity.setActive(!entity.getActive());
        entity = userRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        User entity = userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (entity.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + entity.getRole().getName().toUpperCase()));
        }
        return new org.springframework.security.core.userdetails.User(
                entity.getEmail(),
                entity.getPassword(),
                entity.getActive(), // enabled
                true,               // accountNonExpired
                true,               // credentialsNonExpired
                true,               // accountNonLocked
                authorities
        );
    }

    private UserDto entityToDto(User entity) {
        return userMapper.toDto(entity);
    }
}
