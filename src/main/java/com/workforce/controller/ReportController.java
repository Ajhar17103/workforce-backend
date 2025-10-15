package com.workforce.controller;

import com.workforce.common.AbstractController;
import com.workforce.controller.api.ReportApi;
import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.dto.report.ProjectReportDto;
import com.workforce.dto.report.UserTaskReportDto;
import com.workforce.param.PageableParam;
import com.workforce.service.DailyStandupService;
import com.workforce.service.ReportService;
import com.workforce.support.ListResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
public class ReportController extends AbstractController implements ReportApi {

    private final ReportService reportService;
    private final DailyStandupService dailyStandupService;

    @Autowired
    public ReportController(ReportService reportService, DailyStandupService dailyStandupService) {
        this.reportService = reportService;
        this.dailyStandupService = dailyStandupService;
    }

    @Override
    public ResponseEntity<ListResponseDto<ProjectReportDto>> findProjectReport() {
        return generateResponse(
                reportService.getProjectReport(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "project.report")
        );
    }

    @Override
    public ResponseEntity<ListResponseDto<UserTaskReportDto>> findUserTaskReport() {
        return generateResponse(
                reportService.getUserTaskReport(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "user.task.report")
        );
    }

    @Override
    public ResponseEntity<?>  findStandupReport(PageableParam pageableParam) {
        return generateResponse(
                dailyStandupService.getAll(),
                HttpStatus.OK,
                i18n("x0.get.successfully", "all.standup.report")
        );
    }

    @Override
    public ResponseEntity<?> findAllByToDate(LocalDate date) {
        return generateResponse(
                dailyStandupService.getByToDate(date),
                HttpStatus.OK,
                i18n("x0.get.successfully", "today.standup.report")
        );
    }

}