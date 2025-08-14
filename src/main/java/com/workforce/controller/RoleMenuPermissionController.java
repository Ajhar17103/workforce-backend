package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.RoleMenuPermissionApi;
import com.workforce.dto.master.RoleMenuPermissionDto;
import com.workforce.param.master.RoleMenuPermissionParam;
import com.workforce.service.RoleMenuPermissionService;
import com.workforce.support.ListResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class RoleMenuPermissionController extends AbstractController implements RoleMenuPermissionApi {

    private final RoleMenuPermissionService roleMenuPermissionService;

    public RoleMenuPermissionController(RoleMenuPermissionService roleMenuPermissionService) {
        this.roleMenuPermissionService = roleMenuPermissionService;
    }

    @Override
    public ResponseEntity<ListResponseDto<RoleMenuPermissionDto>> getAll(UUID id) {
        return generateResponse(
                roleMenuPermissionService.getAll(id),
                HttpStatus.OK,
                i18n("x0.records.found", "permissions")
        );
    }

    @Override
    public ResponseEntity<ListResponseDto<RoleMenuPermissionDto>> save(UUID id, List<RoleMenuPermissionParam> params) {
        List<RoleMenuPermissionDto> savedPermissions = roleMenuPermissionService.create(id, params);

        return generateResponse(
                savedPermissions,
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "permission")
        );
    }
}



