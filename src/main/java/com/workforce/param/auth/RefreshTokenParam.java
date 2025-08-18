package com.workforce.param.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenParam {
    private String email;
    private String refreshToken;
}