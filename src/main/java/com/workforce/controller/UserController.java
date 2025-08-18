package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.UserApi;
import com.workforce.dto.master.UserDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.UserParam;
import com.workforce.service.UserService;
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
public class UserController extends AbstractController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserDto>> save(UserParam param) throws Exception {
        return generateResponse(
                userService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "user")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserDto>> findById(UUID id) {
        return generateResponse(
                userService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "user")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    userService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "users")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserDto>> update(UUID id, UserParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                userService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "user")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<UserDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                userService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "user")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        userService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "user")
        );
    }
}
