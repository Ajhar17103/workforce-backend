package com.workforce.service;

import com.workforce.dto.master.DesignationDto;
import com.workforce.param.master.DesignationParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DesignationService {

    DesignationDto create(DesignationParam param) throws Exception;

    DesignationDto getById(UUID id);

    Page<DesignationDto> getAll(Pageable pageable);

    List<DesignationDto> getAll();

    List<DesignationDto> getDesignationsByDepartmentId(UUID id);

    DesignationDto update(DesignationParam param) throws Exception;

    DesignationDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}

