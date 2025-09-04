package com.workforce.param.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthParam {
    @NotBlank(message = "VALIDATION.AUTHENTICATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.AUTHENTICATION.EMAIL.FORMAT")
    @Schema(example = "super@gmail.com")
    String email;
    @NotBlank(message = "VALIDATION.AUTHENTICATION.PASSWORD.NOT_BLANK")
    @Schema(example = "12345678")
    String password;
}