package com.workforce.dto.auth;

import java.util.List;

public record ResponseUserDto(
        String email, List<String> roles
) {
}
