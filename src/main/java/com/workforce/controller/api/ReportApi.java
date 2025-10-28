package com.workforce.controller.api;

import com.workforce.constant.ApiPath;
import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.dto.report.*;
import com.workforce.support.ListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ReportApi {

    @Operation(summary = "Get project-wise report", description = "Returns all projects with assigned users and task statistics (total, completed, in-progress).")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Project report generated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectReportDto.class))}), @ApiResponse(responseCode = "404", description = "No project report data found", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping(value = ApiPath.Report.PROJECT_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<ProjectReportDto>> findProjectReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get daily user task report", description = "Returns all projects with assigned users and task statistics (total, completed, in-progress).")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Daily User Task report generated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserTaskReportDto.class))}), @ApiResponse(responseCode = "404", description = "No User Task report data found", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping(value = ApiPath.Report.TODAY_USER_TASK_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<UserTaskReportDto>> findTodayUserTaskReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get user task report", description = "Returns all projects with assigned users and task statistics (total, completed, in-progress).")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "User Task report generated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserTaskReportDto.class))}), @ApiResponse(responseCode = "404", description = "No User Task report data found", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping(value = ApiPath.Report.USER_TASK_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<UserTaskReportDto>> findUserTaskReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all daily standup report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Daily Standup report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.STANDUP_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<?> findStandupReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get today daily standup report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Today Daily Standup report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.TODAY_STANDUP_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<?> findAllByToDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get daily attendance report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Daily attendance report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No attendance found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.DAILY_ATTENDANCE_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<?> findDailyAttendanceReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get all sprint report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Sprint report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No Sprint found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.SPRINT_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<?> findAllSprintReport() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get project overview report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Project overview report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No project overview found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.PROJECT_OVERVIEW_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<ProjectOverviewReportDto>> findProjectOverviewReport(UUID projectId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get project roadmap report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Project roadmap report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No project roadmap found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.PROJECT_ROAD_MAP_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<ProjectRoadMapReportDto>> findProjectRoadMapReport(UUID projectId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get project time spent report")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Project time spent report retrieved successfully", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "404", description = "No project roadmap found", content = @Content(schema = @Schema(implementation = DailyStandupDto.class)))})
    @GetMapping(value = ApiPath.Report.PROJECT_TIME_SPENT_REPORT, produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<ListResponseDto<ProjectTimeSpentReportDto>> findProjectTimeSpentReport(UUID projectId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}