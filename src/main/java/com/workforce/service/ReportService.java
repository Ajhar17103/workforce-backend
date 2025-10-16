package com.workforce.service;


import com.workforce.dto.report.DailyAttendanceReportDto;
import com.workforce.dto.report.ProjectReportDto;
import com.workforce.dto.report.UserTaskReportDto;

import java.util.List;

public interface ReportService {
    List<ProjectReportDto> getProjectReport();

    List<UserTaskReportDto> getTodayUserTaskReport();

    List<UserTaskReportDto> getUserTaskReport();

    List<DailyAttendanceReportDto> getDailyAttendanceReport();

}