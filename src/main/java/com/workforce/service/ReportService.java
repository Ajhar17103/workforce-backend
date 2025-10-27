package com.workforce.service;


import com.workforce.dto.report.DailyAttendanceReportDto;
import com.workforce.dto.report.ProjectOverviewReportDto;
import com.workforce.dto.report.ProjectReportDto;
import com.workforce.dto.report.UserTaskReportDto;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<ProjectReportDto> getProjectReport();

    List<UserTaskReportDto> getTodayUserTaskReport();

    List<UserTaskReportDto> getUserTaskReport();

    List<DailyAttendanceReportDto> getDailyAttendanceReport();

    List<ProjectOverviewReportDto> getProjectOverviewReport(UUID projectId);
}