package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.DepartmentApi;
import com.workforce.dto.master.DepartmentDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.DepartmentParam;
import com.workforce.service.DepartmentService;
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
public class DepartmentController extends AbstractController implements DepartmentApi {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {

        this.departmentService = departmentService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<DepartmentDto>> save(DepartmentParam param) throws Exception {
        return generateResponse(
                departmentService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "department")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DepartmentDto>> findById(UUID id) {
        return generateResponse(
                departmentService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "department")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    departmentService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "departments")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DepartmentDto>> update(UUID id, DepartmentParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                departmentService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "department")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DepartmentDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                departmentService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "department")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        departmentService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "department")
        );
    }
}
