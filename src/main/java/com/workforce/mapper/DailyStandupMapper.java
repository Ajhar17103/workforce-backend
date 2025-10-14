package com.workforce.mapper;

import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.entity.daily_standup.DailyStandup;
import com.workforce.param.daily_standup.DailyStandupParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyStandupMapper {

    public DailyStandup paramToEntity(DailyStandupParam param, DailyStandup entity) {
        if (param == null) return null;

        if (param.getDate() != null) entity.setDate(param.getDate());
        if (param.getDescription() != null) entity.setDescription(param.getDescription());
        if (param.getChallenge() != null) entity.setChallenge(param.getChallenge());

        return entity;
    }

    public DailyStandupDto entityToDto(DailyStandup entity) {
        if (entity == null) return null;

        DailyStandupDto dto = new DailyStandupDto();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUserName(entity.getUser().getName());
        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setChallenge(entity.getChallenge());
        dto.setActive(entity.getActive());
        dto.setDeleted(entity.getDeleted());

        return dto;
    }
}

