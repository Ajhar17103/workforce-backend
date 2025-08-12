package com.workforce.common.api;


import com.workforce.support.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UpdateApi<S,U> {
    ResponseEntity<ApiResponseDto<S>> update(UUID id, U param) throws Exception;
}
