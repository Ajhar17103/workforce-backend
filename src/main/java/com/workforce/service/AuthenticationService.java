package com.workforce.service;


import com.workforce.dto.auth.AuthenticationResponse;
import com.workforce.param.auth.AuthenticationRequest;
import com.workforce.param.auth.RefreshRequest;
import com.workforce.param.auth.RegistrationRequest;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest req);
}