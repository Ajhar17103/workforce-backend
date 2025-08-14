package com.workforce.service;

import com.workforce.dto.master.UserRoleDto;
import com.workforce.param.master.UserRoleParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {

    UserRoleDto create(UserRoleParam param) throws Exception;

    UserRoleDto getById(UUID id);

    Page<UserRoleDto> getAll(Pageable pageable);

    List<UserRoleDto> getAll(Sort sort);

    UserRoleDto update(UserRoleParam param) throws Exception;

    UserRoleDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
