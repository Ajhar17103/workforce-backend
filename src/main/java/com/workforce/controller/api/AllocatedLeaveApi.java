package com.workforce.controller.api;

import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.entity.leave.AllocatedLeave;
import com.workforce.param.PageableParam;
import com.workforce.param.leave.AllocatedLeaveParam;
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

@RequestMapping(ApiPath.AllocatedLeave.ROOT_PATH)
public interface AllocatedLeaveApi extends GetApi<AllocatedLeaveDto>, GetAllApi<AllocatedLeave>, CreateApi<AllocatedLeaveDto, AllocatedLeaveParam>, UpdateApi<AllocatedLeaveDto, AllocatedLeaveParam>, StatusUpdateApi<AllocatedLeaveDto>, DeleteApi {

    @Operation(summary = "Create Allocated Leave")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Allocated Leave created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> save(@RequestBody @Valid AllocatedLeaveParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create Allocated Leave for all users")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Allocated Leave created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_FOR_ALL_USER_IDENTIFIER)
    default ResponseEntity<ListResponseDto<AllocatedLeaveDto>> saveAllocateLeaveForAllUser(@PathVariable String year) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all allocated leave records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Allocated Leave retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = AllocatedLeaveDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Find Allocated Leave by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Allocated Leave found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Allocated Leave not found", content = @Content(schema = @Schema(implementation = AllocatedLeaveDto.class)))
    })
    @GetMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all allocated leave records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Allocated Leave retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = AllocatedLeaveDto.class)))
    })
    @GetMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_BY_USER_IDENTIFIER)
    default ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> findAllByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update allocated leave")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Allocated Leave updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Allocated Leave not found", content = @Content(schema = @Schema(implementation = AllocatedLeaveDto.class)))
    })
    @PutMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> update(@PathVariable UUID id, @RequestBody @Valid AllocatedLeaveParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Allocated Leave status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Allocated Leave status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Allocated Leave not found", content = @Content(schema = @Schema(implementation = AllocatedLeaveDto.class)))
    })
    @PatchMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AllocatedLeaveDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Allocated Leave")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Allocated Leave deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Allocated Leave not found")
    })
    @DeleteMapping(value = ApiPath.AllocatedLeave.ALLOCATED_LEAVE_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}