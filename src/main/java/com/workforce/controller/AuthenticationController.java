package com.workforce.controller;


import com.workforce.controller.api.AuthenticationApi;
import com.workforce.dto.auth.AuthenticationResponse;
import com.workforce.param.auth.AuthenticationRequest;
import com.workforce.param.auth.RefreshRequest;
import com.workforce.param.auth.RegistrationRequest;
import com.workforce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;

    @Override
    public ResponseEntity<AuthenticationResponse> login(@Valid AuthenticationRequest request) {
        return ResponseEntity.ok(this.service.login(request));
    }

    @Override
    public ResponseEntity<Void> register(@Valid RegistrationRequest request) {
        this.service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<AuthenticationResponse> refresh(RefreshRequest request) {
        return ResponseEntity.ok(this.service.refreshToken(request));
    }
}
