package com.workforce.common.api;


import com.workforce.support.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface CreateApi<S,U> {
    ResponseEntity<ApiResponseDto<S>> save(U param) throws Exception;
}
