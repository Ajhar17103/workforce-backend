package com.workforce.controller.api;

import com.workforce.common.api.*;
import com.workforce.constant.ApiPath;
import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.entity.daily_standup.DailyStandup;
import com.workforce.param.PageableParam;
import com.workforce.param.daily_standup.DailyStandupParam;
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

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(ApiPath.DailyStandup.ROOT_PATH)
public interface DailyStandupApi extends GetApi<DailyStandupDto>, GetAllApi<DailyStandup>, CreateApi<DailyStandupDto, DailyStandupParam>, UpdateApi<DailyStandupDto, DailyStandupParam>, StatusUpdateApi<DailyStandupDto>, DeleteApi {

    @Operation(summary = "Create Daily Standup")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Daily Standup created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    default ResponseEntity<ApiResponseDto<DailyStandupDto>> save(@RequestBody @Valid DailyStandupParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all allocated leave records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @GetMapping
    @Override
    default ResponseEntity<?> findAll(@Schema(hidden = true) PageableParam pageable) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Find Daily Standup by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Daily Standup not found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @GetMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DailyStandupDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all allocated leave records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @GetMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_BY_USER_IDENTIFIER)
    default ResponseEntity<?> findAllByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all allocated leave records for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup retrieved successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @GetMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_BY_TO_DATE_IDENTIFIER)
    default ResponseEntity<?> findAllByToDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update allocated leave")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Daily Standup not found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @PutMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DailyStandupDto>> update(@PathVariable UUID id, @RequestBody @Valid DailyStandupParam param) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update Daily Standup status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Daily Standup status updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Daily Standup not found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))
    })
    @PatchMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_IDENTIFIER)
    @Override
    default ResponseEntity<ApiResponseDto<DailyStandupDto>> statusUpdate(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Daily Standup")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Daily Standup deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Daily Standup not found")
    })
    @DeleteMapping(value = ApiPath.DailyStandup.DAILY_STANDUP_IDENTIFIER)
    @Override
    default ResponseEntity<DeleteResponseDto> deleteById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}