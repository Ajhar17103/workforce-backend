package com.workforce.service;


import com.workforce.dto.auth.AuthDto;
import com.workforce.param.auth.AuthParam;
import com.workforce.param.auth.RefreshTokenParam;

public interface AuthService {

    AuthDto login(AuthParam request);

    AuthDto refreshToken(RefreshTokenParam req);
}