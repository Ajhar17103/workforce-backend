package com.workforce.controller.api;

import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.attendance.AttendanceDto;
import com.workforce.entity.attendance.Attendance;
import com.workforce.param.PageableParam;
import com.workforce.param.attendance.AttendanceParam;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

@RequestMapping(ApiPath.Attendance.ROOT_PATH)
public interface AttendanceApi extends GetApi<AttendanceDto>, GetAllApi<Attendance>, CreateApi<AttendanceDto, AttendanceParam>, UpdateApi<AttendanceDto, AttendanceParam>, StatusUpdateApi<AttendanceDto>, DeleteApi {

    @Operation(summary = "Create Attendance")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Attendance created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<AttendanceDto>> save(@RequestBody @Valid AttendanceParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find Attendance by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @GetMapping(value = ApiPath.Attendance.ATTENDANCE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AttendanceDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all attendance records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @GetMapping(value = ApiPath.Attendance.ATTENDANCE_BY_USER_IDENTIFIER)
    default ResponseEntity<ListResponseDto<AttendanceDto>> findAllByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "Get all attendance records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @PostMapping(value = ApiPath.Attendance.ATTENDANCE_BY_USER_IDENTIFIER_AND_WORK_DATE)
    default ResponseEntity<ApiResponseDto<AttendanceDto>> findByUserIdAndWorkDate(@RequestBody @Valid AttendanceParam param) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all Attendance records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Attendance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @PutMapping(value = ApiPath.Attendance.ATTENDANCE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AttendanceDto>> update(@PathVariable UUID id, @RequestBody @Valid AttendanceParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Attendance status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content(schema = @Schema(implementation = AttendanceDto.class)))
    })
    @PatchMapping(value = ApiPath.Attendance.ATTENDANCE_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<AttendanceDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Attendance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Attendance deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Attendance not found")
    })
    @DeleteMapping(value = ApiPath.Attendance.ATTENDANCE_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}