package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.DesignationApi;
import com.workforce.dto.master.DesignationDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.DesignationParam;
import com.workforce.service.DesignationService;
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
public class DesignationController extends AbstractController implements DesignationApi {

    private final DesignationService designationService;

    @Autowired
    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<DesignationDto>> save(DesignationParam param) throws Exception {
        return generateResponse(
                designationService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "designation")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DesignationDto>> findById(UUID id) {
        return generateResponse(
                designationService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "designation")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    designationService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "designations")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DesignationDto>> update(UUID id, DesignationParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                designationService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "designation")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<DesignationDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                designationService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "designation")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        designationService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "designation")
        );
    }

    @Override
    public ResponseEntity<?> findDesignationsByDepartmentId(UUID id) {
        return generateResponse(
                designationService.getDesignationsByDepartmentId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "designations")
        );
    }
}
