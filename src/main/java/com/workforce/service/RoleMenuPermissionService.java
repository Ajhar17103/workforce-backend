package com.workforce.service;

import com.workforce.dto.master.RoleMenuPermissionDto;
import com.workforce.param.master.RoleMenuPermissionParam;

import java.util.List;
import java.util.UUID;


public interface RoleMenuPermissionService {

    List<RoleMenuPermissionDto> create(UUID id, List<RoleMenuPermissionParam> params);

    List<RoleMenuPermissionDto> getAll(UUID id);
}
