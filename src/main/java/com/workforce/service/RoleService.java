package com.workforce.service;

import com.workforce.dto.master.RoleDto;
import com.workforce.param.master.RoleParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    RoleDto create(RoleParam param) throws Exception;

    RoleDto getById(UUID id);

    Page<RoleDto> getAll(Pageable pageable);

    List<RoleDto> getAll();

    RoleDto update(RoleParam param) throws Exception;

    RoleDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
