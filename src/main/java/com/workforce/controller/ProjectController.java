package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.ProjectApi;
import com.workforce.dto.master.ProjectDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.ProjectParam;
import com.workforce.service.ProjectService;
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
public class ProjectController extends AbstractController implements ProjectApi {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProjectDto>> save(ProjectParam param) throws Exception {
        return generateResponse(
                projectService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "project")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProjectDto>> findById(UUID id) {
        return generateResponse(
                projectService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "project")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
            return generateResponse(
                    projectService.getAll(),
                    HttpStatus.OK,
                    i18n("x0.get.successfully", "projects")
            );
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProjectDto>> update(UUID id, ProjectParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                projectService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "project")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<ProjectDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                projectService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "project")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        projectService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "project")
        );
    }
}
