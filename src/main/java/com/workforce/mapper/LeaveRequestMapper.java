package com.workforce.mapper;

import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.dto.leave.LeaveRequestDto;
import com.workforce.entity.leave.AllocatedLeave;
import com.workforce.entity.leave.LeaveRequest;
import com.workforce.param.leave.AllocatedLeaveParam;
import com.workforce.param.leave.LeaveRequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class LeaveRequestMapper {

    public LeaveRequest paramToEntity(LeaveRequestParam param, LeaveRequest entity) {
        if (param == null) return null;

        if (param.getFiscalYear() != null) entity.setFiscalYear(param.getFiscalYear());
        if (param.getLeaveType() != null) entity.setLeaveType(param.getLeaveType());
        if (param.getLeaveFor() != null) entity.setLeaveFor(param.getLeaveFor());
        if (param.getFromDate() != null) entity.setFromDate(param.getFromDate());
        if (param.getToDate() != null) entity.setToDate(param.getToDate());

        double totalDays = 0.0;
        if ("HALF_DAY".equalsIgnoreCase(String.valueOf(entity.getLeaveFor()))) {
            totalDays = 0.5;
        } else if (entity.getFromDate() != null && entity.getToDate() != null) {
            totalDays = ChronoUnit.DAYS.between(entity.getFromDate(), entity.getToDate()) + 1;
        }

        if (totalDays > 0.0) {entity.setTotalDay(totalDays);}
        if (param.getReason() != null) entity.setReason(param.getReason());
        if (param.getAttchmentPath() != null) entity.setAttchmentPath(param.getAttchmentPath());
        if (param.getLeaveStatus() != null) entity.setLeaveStatus(param.getLeaveStatus());
        if(param.getRemarks() != null) entity.setRemarks(param.getRemarks());


        return entity;
    }

    public LeaveRequestDto entityToDto(LeaveRequest entity) {
        if (entity == null) return null;

        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUserName(entity.getUser().getName());
        dto.setFiscalYear(entity.getFiscalYear());
        dto.setLeaveType(entity.getLeaveType());
        dto.setLeaveFor(entity.getLeaveFor());
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        dto.setTotalDay(entity.getTotalDay());
        dto.setReason(entity.getReason());
        dto.setAttchmentPath(entity.getAttchmentPath());
        dto.setLeaveStatus(entity.getLeaveStatus());
        dto.setRemarks(entity.getRemarks());
        dto.setActive(entity.getActive());
        dto.setDeleted(entity.getDeleted());

        return dto;
    }
}

