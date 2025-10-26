package com.workforce.controller.api;


import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.master.SprintDto;
import com.workforce.entity.master.Sprint;
import com.workforce.param.PageableParam;
import com.workforce.param.master.SprintParam;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(ApiPath.Sprint.ROOT_PATH)
public interface SprintApi extends GetApi<SprintDto>, GetAllApi<Sprint>, CreateApi<SprintDto, SprintParam>, UpdateApi<SprintDto, SprintParam>, StatusUpdateApi<SprintDto>, DeleteApi {

    @Operation(summary = "Create Sprint")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sprint created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<SprintDto>> save(@RequestBody @Valid SprintParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find sprint by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sprint found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Sprint not found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @GetMapping(value = ApiPath.Sprint.SPRINT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<SprintDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Find sprint by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sprint found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Sprint not found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @GetMapping(value = ApiPath.Sprint.SPRINT_BY_PROJECT_IDENTIFIER)
    default ResponseEntity<ListResponseDto<SprintDto>> findByProjectId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all sprints")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Menus retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No sprints found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update sprint")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sprint updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Sprint not found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @PutMapping(value = ApiPath.Sprint.SPRINT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<SprintDto>> update(@PathVariable UUID id, @RequestBody @Valid SprintParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update sprint status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sprint  status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Sprint  not found", content = @Content(schema = @Schema(implementation = SprintDto.class)))
    })
    @PatchMapping(value = ApiPath.Sprint.SPRINT_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<SprintDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete sprint")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sprint deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sprint not found")
    })
    @DeleteMapping(value = ApiPath.Sprint.SPRINT_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

