package com.workforce.mapper;

import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.entity.leave.AllocatedLeave;
import com.workforce.param.leave.AllocatedLeaveParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllocatedLeaveMapper {

    public AllocatedLeave paramToEntity(AllocatedLeaveParam param, AllocatedLeave entity) {
        if (param == null) return null;

        if (param.getFiscalYear() != null) entity.setFiscalYear(param.getFiscalYear());
        if (param.getTotalSickLeave() != null) entity.setTotalSickLeave(param.getTotalSickLeave());
        if (param.getTakenSickLeave() != null) entity.setTakenSickLeave(param.getTakenSickLeave());
        if (param.getTotalCasualLeave() != null) entity.setTotalCasualLeave(param.getTotalCasualLeave());
        if (param.getTakenCasualLeave() != null) entity.setTakenCasualLeave(param.getTakenCasualLeave());
        if (param.getTotalAnnualLeave() != null) entity.setTotalAnnualLeave(param.getTotalAnnualLeave());
        if (param.getTakenAnnualLeave() != null) entity.setTakenAnnualLeave(param.getTakenAnnualLeave());
        if (param.getPaidLeave() != null) entity.setPaidLeave(param.getTakenAnnualLeave());

        return entity;
    }

    public AllocatedLeaveDto entityToDto(AllocatedLeave entity) {
        if (entity == null) return null;

        AllocatedLeaveDto dto = new AllocatedLeaveDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUserName(entity.getUser().getName());
        dto.setFiscalYear(entity.getFiscalYear());
        dto.setTotalSickLeave(entity.getTotalSickLeave());
        dto.setTakenSickLeave(entity.getTakenSickLeave());
        dto.setTotalCasualLeave(entity.getTotalCasualLeave());
        dto.setTakenCasualLeave(entity.getTakenCasualLeave());
        dto.setTotalAnnualLeave(entity.getTotalAnnualLeave());
        dto.setTakenAnnualLeave(entity.getTakenAnnualLeave());
        dto.setPaidLeave(entity.getPaidLeave());
        dto.setActive(entity.getActive());
        dto.setDeleted(entity.getDeleted());

        return dto;
    }
}

