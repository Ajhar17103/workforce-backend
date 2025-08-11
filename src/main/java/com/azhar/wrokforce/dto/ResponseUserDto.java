package com.azhar.wrokforce.dto;

import java.util.List;

public record ResponseUserDto(
        String email, List<String> roles
) {
}
