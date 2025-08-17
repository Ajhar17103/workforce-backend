package com.workforce.controller.api;


import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.DepartmentDto;
import com.workforce.entity.master.Department;
import com.workforce.param.PageableParam;
import com.workforce.param.master.DepartmentParam;
import com.workforce.support.ApiResponseDto;
import com.workforce.support.DeleteResponseDto;
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

@RequestMapping(ApiPath.Department.ROOT_PATH)
public interface DepartmentApi extends GetApi<DepartmentDto>, GetAllApi<Department>, CreateApi<DepartmentDto, DepartmentParam>, UpdateApi<DepartmentDto, DepartmentParam>, StatusUpdateApi<DepartmentDto>, DeleteApi {

    @Operation(summary = "Create menu")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User Role created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<DepartmentDto>> save(@RequestBody @Valid DepartmentParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find menu by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
    })
    @GetMapping(value = ApiPath.Department.DEPARTMENT_IDENTIFIER)  // e.g., "/{id}"
    @Override
    default ResponseEntity<ApiResponseDto<DepartmentDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all menus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menus retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No menus found", content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
    })
    @PutMapping(value = ApiPath.Department.DEPARTMENT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DepartmentDto>> update(@PathVariable UUID id, @RequestBody @Valid DepartmentParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
    })
    @PatchMapping(value = ApiPath.Department.DEPARTMENT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DepartmentDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete menu")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User Role not found")
    })
    @DeleteMapping(value = ApiPath.Department.DEPARTMENT_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

