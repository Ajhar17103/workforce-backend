package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.UserRoleApi;
import com.workforce.dto.master.UserRoleDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.UserRoleParam;
import com.workforce.service.UserRoleService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
@CrossOrigin("*")
public class UserRoleController extends AbstractController implements UserRoleApi {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService UserRoleService) {

        this.userRoleService = UserRoleService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserRoleDto>> save(UserRoleParam param) throws Exception {
        return generateResponse(
                userRoleService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "user.role")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserRoleDto>> findById(UUID id) {
        return generateResponse(
                userRoleService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "user.role")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
//        if (pageableParam.isPageable()) {
//            PageRequest pageRequest = PageRequest.of(
//                    pageableParam.getPage(),
//                    pageableParam.getSize(),
//                    Sort.by(pageableParam.getSortDirection(), pageableParam.getSortBy())
//            );
//            return generateResponse(
//                    menuService.getAll(pageRequest),
//                    HttpStatus.OK,
//                    i18n("x0.get.successfully", "user.roles")
//            );
//        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, "name"); // default sorting, adjust as needed
            return generateResponse(
                    userRoleService.getAll(sort),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "user.roles")
            );
//        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserRoleDto>> update(UUID id, UserRoleParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                userRoleService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "user.role")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserRoleDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                userRoleService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "user.role")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        userRoleService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "menu")
        );
    }
}
