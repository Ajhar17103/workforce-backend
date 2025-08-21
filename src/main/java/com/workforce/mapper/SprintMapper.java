package com.workforce.mapper;

import com.workforce.dto.master.SprintDto;
import com.workforce.entity.master.Sprint;
import com.workforce.param.master.SprintParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SprintMapper {


    public Sprint paramToEntity(SprintParam param, Sprint entity) {

        if (param.getName() != null) entity.setName(param.getName());
        if (param.getStartDate() != null) entity.setStartDate(param.getStartDate());
        if (param.getEndDate() != null) entity.setEndDate(param.getEndDate());
        if (param.getWorkingDays() != null) entity.setWorkingDays(param.getWorkingDays());
        if (param.getDailyWorkingHrs() != null) entity.setDailyWorkingHrs(param.getDailyWorkingHrs());
        if (param.getTotalSprintHrs() != null) entity.setTotalSprintHrs(param.getTotalSprintHrs());
        if (param.getSprintType() != null) entity.setSprintType(param.getSprintType());
        return entity;
    }

    public SprintDto entityToDto(Sprint entity) {
        if (entity == null) return null;

        SprintDto dto = new SprintDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProjectId(entity.getProject() != null ? entity.getProject().getId(): null);
        dto.setProjectName(entity.getProject() != null ? entity.getProject().getName(): null);
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setWorkingDays(entity.getWorkingDays());
        dto.setDailyWorkingHrs(entity.getDailyWorkingHrs());
        dto.setTotalSprintHrs(entity.getTotalSprintHrs());
        dto.setSprintType(entity.getSprintType());
        dto.setActive(entity.getActive());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }
}

