package com.workforce.controller.api;


import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.DesignationDto;
import com.workforce.entity.master.Designation;
import com.workforce.param.PageableParam;
import com.workforce.param.master.DesignationParam;
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

@RequestMapping(ApiPath.Designation.ROOT_PATH)
public interface DesignationApi extends GetApi<DesignationDto>, GetAllApi<Designation>, CreateApi<DesignationDto, DesignationParam>, UpdateApi<DesignationDto, DesignationParam>, StatusUpdateApi<DesignationDto>, DeleteApi {

    @Operation(summary = "Create menu")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User Role created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<DesignationDto>> save(@RequestBody @Valid DesignationParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find menu by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DesignationDto.class)))
    })
    @GetMapping(value = ApiPath.Designation.DESIGNATION_IDENTIFIER)  // e.g., "/{id}"
    @Override
    default ResponseEntity<ApiResponseDto<DesignationDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all menus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menus retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No menus found", content = @Content(schema = @Schema(implementation = DesignationDto.class)))
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
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DesignationDto.class)))
    })
    @PutMapping(value = ApiPath.Designation.DESIGNATION_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DesignationDto>> update(@PathVariable UUID id, @RequestBody @Valid DesignationParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update menu status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Role status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Role not found", content = @Content(schema = @Schema(implementation = DesignationDto.class)))
    })
    @PatchMapping(value = ApiPath.Designation.DESIGNATION_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DesignationDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete menu")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User Role not found")
    })
    @DeleteMapping(value = ApiPath.Designation.DESIGNATION_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get designations by department ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Designations retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No designations found for the department",
                    content = @Content(schema = @Schema(implementation = DesignationDto.class)))
    })
    @GetMapping(value = ApiPath.Designation.DESIGNATION_IDENTIFIER_DEPARTMENT_ID)
    default ResponseEntity<?> findDesignationsByDepartmentId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

