package com.workforce.service.impl;


import com.workforce.dto.daily_standup.DailyStandupDto;
import com.workforce.entity.daily_standup.DailyStandup;
import com.workforce.entity.master.User;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.DailyStandupMapper;
import com.workforce.param.daily_standup.DailyStandupParam;
import com.workforce.repository.DailyStandupRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.DailyStandupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyStandupServiceImpl implements DailyStandupService {

    private final UserRepository userRepository;
    private final DailyStandupRepository dailyStandupRepository;
    private final DailyStandupMapper dailyStandupMapper;

    @Override
    @Transactional
    public DailyStandupDto create(DailyStandupParam param) {
        return entityToDto(createReturnEntity(param));
    }


    @Override
    public List<DailyStandupDto> getAll() {
        return dailyStandupRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DailyStandupDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<DailyStandupDto> getByUserId(UUID id) {
        List<DailyStandup> leaveRequests = dailyStandupRepository.findByUserId(id);

        if (leaveRequests == null || leaveRequests.isEmpty()) {
            throw new DataNotFoundException("No standup found for user with id: " + id);
        }
        return leaveRequests.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DailyStandupDto> getByToDate(LocalDate date) {
        List<DailyStandup> leaveRequests = dailyStandupRepository.findByDate(date);

        if (leaveRequests == null || leaveRequests.isEmpty()) {
            throw new DataNotFoundException("No standup found for the date: " + date);
        }
        return leaveRequests.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DailyStandupDto update(DailyStandupParam param) {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public DailyStandupDto statusUpdate(UUID id) {
        DailyStandup entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = dailyStandupRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        DailyStandup entity = getEntityById(id);
        dailyStandupRepository.delete(entity);
    }

    private DailyStandup getEntityById(UUID id) {
        return dailyStandupRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Allocated Leave not found with id: " + id));
    }

    private DailyStandup createReturnEntity(DailyStandupParam param) {
        DailyStandup entity = new DailyStandup();
        entity = paramToEntity(param, entity);
        entity.setActive(true);
        return dailyStandupRepository.save(entity);
    }

    private DailyStandup updateReturnEntity(DailyStandupParam param) {
        DailyStandup entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity);
        return dailyStandupRepository.save(entity);
    }

    private DailyStandupDto entityToDto(DailyStandup entity) {
        return dailyStandupMapper.entityToDto(entity);
    }

    private DailyStandup paramToEntity(DailyStandupParam param, DailyStandup entity) {
        entity = dailyStandupMapper.paramToEntity(param, entity);

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + param.getUserId()));
            entity.setUser(user);
        }
        return entity;
    }

}
