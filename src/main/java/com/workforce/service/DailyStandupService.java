package com.workforce.service;



import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.param.daily_standup.DailyStandupParam;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DailyStandupService {

    DailyStandupDto create(DailyStandupParam param) throws Exception;

    List<DailyStandupDto> getAll();

    DailyStandupDto getById(UUID id);

    List<DailyStandupDto> getByUserId(UUID id);

    List<DailyStandupDto> getByToDate(LocalDate date);

    DailyStandupDto update(DailyStandupParam param) throws Exception;

    DailyStandupDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}