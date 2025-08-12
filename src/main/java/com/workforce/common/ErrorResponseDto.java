package com.workforce.common;

import com.workforce.utils.common.LocalDateFormatPattern;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponseDto<T>(
        int statusCode,
        String statusMessage,
        String message,
        T data,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateFormatPattern.pattern)
        LocalDateTime timestamp
) {}
