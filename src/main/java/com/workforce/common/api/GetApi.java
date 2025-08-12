package com.workforce.common.api;


import com.workforce.support.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface GetApi<S> {
    ResponseEntity<ApiResponseDto<S>> findById(UUID id);
}
