package com.workforce.controller;


import com.workforce.controller.api.AuthApi;
import com.workforce.dto.auth.AuthDto;
import com.workforce.param.auth.AuthParam;
import com.workforce.param.auth.RefreshTokenParam;
import com.workforce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController implements AuthApi {

    private final AuthService service;

    @Override
    public ResponseEntity<AuthDto> login(@Valid AuthParam request) {
        return ResponseEntity.ok(this.service.login(request));
    }

    @Override
    public ResponseEntity<AuthDto> refresh(RefreshTokenParam request) {
        return ResponseEntity.ok(this.service.refreshToken(request));
    }
}
