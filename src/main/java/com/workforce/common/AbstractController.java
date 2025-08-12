package com.workforce.common;


import com.workforce.dto.BaseDto;
import com.workforce.support.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractController implements I18nService {

    public  <S extends BaseDto> ResponseEntity<ApiResponseDto<S>> generateResponse(S data, HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiResponseDto.<S>builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .payload(data)
                                .build()
                );
    }
    public  <S> ResponseEntity<ApiResponseDto<S>> generateResponse(S data, HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiResponseDto.<S>builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .payload(data)
                                .build()
                );
    }

    public  <S> ResponseEntity<ApiResponseDto<S>> generateResponse(Optional<S> data, HttpStatus status, String message) {
        if (data.isPresent()) {
            return ResponseEntity
                    .status(status)
                    .body(
                            ApiResponseDto.<S>builder()
                                    .nonce(Instant.now().toEpochMilli())
                                    .status(status.value())
                                    .message(message)
                                    .payload(data.get())
                                    .build()
                    );
        } else {
            ErrorDto errorDto = ErrorDto.builder()
                    .code("NOT_FOUND")
                    .message("Entity is not found")
                    .build();
            return ResponseEntity
                    .status(206)
                    .body(
                            ApiResponseDto.<S>builder()
                                    .nonce(Instant.now().toEpochMilli())
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .message("Entity is not found")
                                    .error(errorDto)
                                    .build()
                    );
        }
    }

    public  <S> ResponseEntity<ListResponseDto<S>> generateResponse(List<S> data, HttpStatus status, String message) {
        int total = data == null ? 0 : data.size();
        return ResponseEntity
                .status(status)
                .body(
                        ListResponseDto.<S>builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .total(total)
                                .payload(data)
                                .build()
                );

    }

    public  <S> ResponseEntity<PaginationResponseDto<S>> generateResponse(Page<S> page, HttpStatus status, String message) {
        return
                ResponseEntity
                        .status(status)
                        .body(
                                PaginationResponseDto.<S>builder()
                                        .nonce(Instant.now().toEpochMilli())
                                        .status(status.value())
                                        .message(message)
                                        .payload(page.getContent())
                                        .pageable(PageDto.builder()
                                                .pageNumber(page.getNumber()+1)
                                                .pageSize(page.getSize())
                                                .totalElements(page.getTotalElements())
                                                .totalPages(page.getTotalPages())
                                                .first(page.isFirst())
                                                .last(page.isLast())
                                                .build())
                                        .build()
                        );

    }

    public  ResponseEntity<DeleteResponseDto> generateResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        DeleteResponseDto.builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .deleted(true)
                                .build()
                );
    }

    public ResponseEntity<ApiResponseDto<?>> generateErrorResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiResponseDto.builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .error(ErrorDto.builder()
                                        .code(status.name())
                                        .message(message)
                                        .build())
                                .build()
                );
    }
}
