package com.workforce.service;

import com.workforce.dto.attendance.AttendanceDto;
import com.workforce.param.attendance.AttendanceParam;
import java.util.List;
import java.util.UUID;


public interface AttendanceService {

    AttendanceDto create(AttendanceParam param) throws Exception;

    AttendanceDto getById(UUID id);

    List<AttendanceDto> getAll();

    AttendanceDto update(AttendanceParam param) throws Exception;

    AttendanceDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;

    List<AttendanceDto> getAttendanceByUserId(UUID userId);

    AttendanceDto getAttendanceByUserIdAndWorkDate(AttendanceParam param);
}