package com.workforce.dto.auth;

import com.workforce.dto.master.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthDto {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private UserDto userDto;
}