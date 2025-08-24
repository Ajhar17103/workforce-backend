package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.TaskApi;
import com.workforce.dto.master.TaskDto;
import com.workforce.param.PageableParam;
import com.workforce.param.master.TaskParam;
import com.workforce.service.TaskService;
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
public class TaskController extends AbstractController implements TaskApi {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<TaskDto>> save(TaskParam param) throws Exception {
        return generateResponse(
                taskService.create(param),
                HttpStatus.CREATED,
                i18n("x0.has.been.saved.successfully", "task")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<TaskDto>> findById(UUID id) {
        return generateResponse(
                taskService.getById(id),
                HttpStatus.OK,
                i18n("x0.get.successfully", "task")
        );
    }

    @Override
    public ResponseEntity<ListResponseDto<TaskDto>> findAllByUserId(UUID userId) {
        return generateResponse(
                taskService.getTasksByUserId(userId),
                HttpStatus.OK,
                i18n("x0.get.successfully", "tasks")
        );
    }

    @Override
    public ResponseEntity<?> findAll(PageableParam pageableParam) {
        return generateResponse(
                taskService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "tasks")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<TaskDto>> update(UUID id, TaskParam param) throws Exception {
        param.setId(id);
        return generateResponse(
                taskService.update(param),
                HttpStatus.OK,
                i18n("x0.has.been.updated.successfully", "task")
        );
    }

    @Override
    public ResponseEntity<ApiResponseDto<TaskDto>> statusUpdate(UUID id) throws Exception {
        return generateResponse(
                taskService.statusUpdate(id),
                HttpStatus.OK,
                i18n("x0.status.has.been.updated.successfully", "task")
        );
    }

    @Override
    public ResponseEntity<DeleteResponseDto> deleteById(UUID id) throws Exception {
        taskService.delete(id);
        return generateResponse(
                HttpStatus.NO_CONTENT,
                i18n("x0.has.been.deleted.successfully", "task")
        );
    }
}
