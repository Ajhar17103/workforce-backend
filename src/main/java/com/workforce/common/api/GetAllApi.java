package com.workforce.common.api;


import com.workforce.param.PageableParam;
import org.springframework.http.ResponseEntity;

public interface GetAllApi<T> {
    ResponseEntity<?> findAll(PageableParam pageable);
}
