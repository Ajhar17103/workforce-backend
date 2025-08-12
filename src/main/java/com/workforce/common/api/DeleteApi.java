package com.workforce.common.api;



import com.workforce.support.DeleteResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface DeleteApi {
    ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception;
}
