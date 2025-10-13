package com.workforce.controller.api;

import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.leave.LeaveRequestDto;
import com.workforce.entity.leave.LeaveRequest;
import com.workforce.param.PageableParam;
import com.workforce.param.leave.LeaveRequestParam;
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

@RequestMapping(ApiPath.LeaveRequest.ROOT_PATH)
public interface LeaveRequestApi extends GetApi<LeaveRequestDto>, GetAllApi<LeaveRequest>, CreateApi<LeaveRequestDto, LeaveRequestParam>, UpdateApi<LeaveRequestDto, LeaveRequestParam>, StatusUpdateApi<LeaveRequestDto>, DeleteApi {

    @Operation(summary = "Create Leave Request")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Leave Request created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<LeaveRequestDto>> save(@RequestBody @Valid LeaveRequestParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all allocated leave records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave Request retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = LeaveRequestDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Find Leave Request by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave Request found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Leave Request not found", content = @Content(schema = @Schema(implementation = LeaveRequestDto.class)))
    })
    @GetMapping(value = ApiPath.LeaveRequest.LEAVE_REQUEST_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<LeaveRequestDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all allocated leave records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave Request retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = LeaveRequestDto.class)))
    })
    @GetMapping(value = ApiPath.LeaveRequest.LEAVE_REQUEST_BY_USER_IDENTIFIER)
    default ResponseEntity<?> findAllByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update allocated leave")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave Request updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Leave Request not found", content = @Content(schema = @Schema(implementation = LeaveRequestDto.class)))
    })
    @PutMapping(value = ApiPath.LeaveRequest.LEAVE_REQUEST_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<LeaveRequestDto>> update(@PathVariable UUID id, @RequestBody @Valid LeaveRequestParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Leave Request status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave Request status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Leave Request not found", content = @Content(schema = @Schema(implementation = LeaveRequestDto.class)))
    })
    @PatchMapping(value = ApiPath.LeaveRequest.LEAVE_REQUEST_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<LeaveRequestDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Leave Request")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Leave Request deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Leave Request not found")
    })
    @DeleteMapping(value = ApiPath.LeaveRequest.LEAVE_REQUEST_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}