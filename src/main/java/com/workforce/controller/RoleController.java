package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.RoleApi;
import com.workforce.dto.master.RoleDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.RoleParam;
import com.workforce.service.RoleService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
public class RoleController extends AbstractController implements RoleApi {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService RoleService) {

        this.roleService = RoleService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<RoleDto>> save(RoleParam param) throws Exception {
        return generateResponse(
                roleService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "role")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<RoleDto>> findById(UUID id) {
        return generateResponse(
                roleService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "role")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    roleService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "roles")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<RoleDto>> update(UUID id, RoleParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                roleService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "role")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<RoleDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                roleService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "role")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        roleService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "role")
        );
    }
}
