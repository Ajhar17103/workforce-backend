package com.workforce.service;

import com.workforce.dto.master.DepartmentDto;
import com.workforce.param.master.DepartmentParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    DepartmentDto create(DepartmentParam param) throws Exception;

    DepartmentDto getById(UUID id);

    Page<DepartmentDto> getAll(Pageable pageable);

    List<DepartmentDto> getAll();

    DepartmentDto update(DepartmentParam param) throws Exception;

    DepartmentDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
