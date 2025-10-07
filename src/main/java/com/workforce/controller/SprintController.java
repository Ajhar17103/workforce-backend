package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.SprintApi;
import com.workforce.dto.master.SprintDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.SprintParam;
import com.workforce.service.SprintService;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import com.workforce.support.ListResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
public class SprintController extends AbstractController implements SprintApi {

    private final SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<SprintDto>> save(SprintParam param) throws Exception {
        return generateResponse(
                sprintService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "sprint")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<SprintDto>> findById(UUID id) {
        return generateResponse(
                sprintService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "sprint")
        );
    }

    @Override
    public ResponseEntity<ListResponseDto<SprintDto>> findByProjectId(UUID id) {
        return generateResponse(
                sprintService.getByProjectId(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "sprints")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    sprintService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "sprints")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<SprintDto>> update(UUID id, SprintParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                sprintService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "sprint")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<SprintDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                sprintService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "sprint")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        sprintService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "sprint")
        );
    }
}
