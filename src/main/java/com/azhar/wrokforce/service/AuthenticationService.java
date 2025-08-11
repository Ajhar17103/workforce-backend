package com.azhar.wrokforce.service;


import com.azhar.wrokforce.dto.AuthenticationResponse;
import com.azhar.wrokforce.param.AuthenticationRequest;
import com.azhar.wrokforce.param.RefreshRequest;
import com.azhar.wrokforce.param.RegistrationRequest;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest req);
}