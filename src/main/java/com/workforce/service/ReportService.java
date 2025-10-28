package com.workforce.service;


import com.workforce.dto.report.*;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<ProjectReportDto> getProjectReport();

    List<UserTaskReportDto> getTodayUserTaskReport();

    List<UserTaskReportDto> getUserTaskReport();

    List<DailyAttendanceReportDto> getDailyAttendanceReport();

    List<ProjectOverviewReportDto> getProjectOverviewReport(UUID projectId);

    List<ProjectRoadMapReportDto> getProjectRoadMapReport(UUID projectId);

    List<ProjectTimeSpentReportDto> getProjectTimeSpentReport(UUID projectId);
}