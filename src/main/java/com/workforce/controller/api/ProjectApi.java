package com.workforce.controller.api;


import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.ProjectDto;
import com.workforce.dto.master.SprintDto;
import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.Project;
import com.workforce.param.PageableParam;
import com.workforce.param.master.ProjectParam;
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

@RequestMapping(ApiPath.Project.ROOT_PATH)
public interface ProjectApi extends GetApi<ProjectDto>, GetAllApi<Project>, CreateApi<ProjectDto, ProjectParam>, UpdateApi<ProjectDto, ProjectParam>, StatusUpdateApi<ProjectDto>, DeleteApi {

    @Operation(summary = "Create Project")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<ProjectDto>> save(@RequestBody @Valid ProjectParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find menu by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @GetMapping(value = ApiPath.Project.PROJECT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<ProjectDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Find menu by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sprint found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Sprint not found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @GetMapping(value = ApiPath.Project.PROJECT_BY_USER_IDENTIFIER)
    default ResponseEntity<ListResponseDto<UserDto>> findAllUserByProjectId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all menus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menus retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No menus found", content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @PutMapping(value = ApiPath.Project.PROJECT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<ProjectDto>> update(@PathVariable UUID id, @RequestBody @Valid ProjectParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project  status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Project  not found", content = @Content(schema = @Schema(implementation = ProjectDto.class)))
    })
    @PatchMapping(value = ApiPath.Project.PROJECT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<ProjectDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete menu")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Project deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @DeleteMapping(value = ApiPath.Project.PROJECT_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

