package com.workforce.common.api;


import com.workforce.support.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


public interface StatusUpdateApi<S> {
    ResponseEntity<ApiResponseDto<S>> statusUpdate(UUID id) throws Exception;
}
