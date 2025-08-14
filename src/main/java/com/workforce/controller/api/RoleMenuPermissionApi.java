package com.workforce.controller.api;

import com.workforce.constant.ApiPath;
import com.workforce.param.master.RoleMenuPermissionParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(ApiPath.RoleMenuPermission.ROOT_PATH)
public interface RoleMenuPermissionApi {

    @GetMapping(value = ApiPath.RoleMenuPermission.ROLE_MENU_PERMISSION_IDENTIFIER)
    ResponseEntity<?> getAll(@PathVariable UUID id);

    @PatchMapping(value = ApiPath.RoleMenuPermission.ROLE_MENU_PERMISSION_IDENTIFIER)
    ResponseEntity<?> save(@PathVariable UUID id, @RequestBody List<RoleMenuPermissionParam> params);
}

