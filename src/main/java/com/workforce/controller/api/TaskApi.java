package com.workforce.controller.api;

import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.task_board.TaskDto;
import com.workforce.entity.task_board.Task;
import com.workforce.param.PageableParam;
import com.workforce.param.task_board.TaskParam;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
import com.workforce.support.ListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(ApiPath.Task.ROOT_PATH)
public interface TaskApi extends
        GetApi<TaskDto>,
        GetAllApi<Task>,
        CreateApi<TaskDto, TaskParam>,
        UpdateApi<TaskDto, TaskParam>,
        StatusUpdateApi<TaskDto>,
        DeleteApi {

    @Operation(summary = "Create Task")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<TaskDto>> save(@RequestBody @Valid TaskParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find Task by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskDto.class)))
    })
    @GetMapping(value = ApiPath.Task.TASK_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<TaskDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all tasks assigned to a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No tasks found", content = @Content(schema = @Schema(implementation = TaskDto.class)))
    })
    @GetMapping(value = ApiPath.Task.TASK_BY_USER_IDENTIFIER)
    default ResponseEntity<ListResponseDto<TaskDto>> findAllByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No tasks found", content = @Content(schema = @Schema(implementation = TaskDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskDto.class)))
    })
    @PutMapping(value = ApiPath.Task.TASK_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<TaskDto>> update(@PathVariable UUID id, @RequestBody @Valid TaskParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Task status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = TaskDto.class)))
    })
    @PatchMapping(value = ApiPath.Task.TASK_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<TaskDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Task")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping(value = ApiPath.Task.TASK_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
