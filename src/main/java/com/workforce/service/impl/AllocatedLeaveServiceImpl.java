package com.workforce.service.impl;

import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.entity.leave.AllocatedLeave;
import com.workforce.entity.master.User;
import com.workforce.enums.LeaveFor;
import com.workforce.enums.LeaveType;
import com.workforce.exception.DataAlreadyExistsException;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.AllocatedLeaveMapper;
import com.workforce.param.leave.AllocatedLeaveParam;
import com.workforce.repository.AllocatedLeaveRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.AllocatedLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AllocatedLeaveServiceImpl implements AllocatedLeaveService {

    private final UserRepository userRepository;
    private final AllocatedLeaveRepository allocatedLeaveRepository;
    private final AllocatedLeaveMapper allocatedLeaveMapper;

    @Override
    @Transactional
    public AllocatedLeaveDto create(AllocatedLeaveParam param) {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    @Transactional
    public List<AllocatedLeaveDto> allocateLeaveForAllUsers(String fiscalYear) throws Exception {

        List<User> users = userRepository.findAll()
                .stream()
                .filter(User::getActive)
                .toList();

        List<AllocatedLeaveDto> createdLeaves = new ArrayList<>();

        for (User user : users) {
            AllocatedLeaveParam allocatedLeaveParam = new AllocatedLeaveParam();
            allocatedLeaveParam.setUserId(user.getId());
            allocatedLeaveParam.setFiscalYear(fiscalYear);
            allocatedLeaveParam.setTotalSickLeave(12.0);
            allocatedLeaveParam.setTotalCasualLeave(12.0);
            allocatedLeaveParam.setTotalAnnualLeave(10.0);
            allocatedLeaveParam.setTakenSickLeave(0.0);
            allocatedLeaveParam.setTakenCasualLeave(0.0);
            allocatedLeaveParam.setTakenAnnualLeave(0.0);
            allocatedLeaveParam.setPaidLeave(0.0);
            createdLeaves.add(entityToDto(createReturnEntity(allocatedLeaveParam)));
        }
        return createdLeaves;
    }

    @Override
    public List<AllocatedLeaveDto> getAll() {
        return allocatedLeaveRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AllocatedLeaveDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public Optional<AllocatedLeaveDto> getByUserId(UUID id) {
        Optional<AllocatedLeave> allocatedLeave = allocatedLeaveRepository.findByUserId(id);
        return Optional.ofNullable(entityToDto(allocatedLeave.orElse(null)));
    }

    @Override
    @Transactional
    public AllocatedLeaveDto update(AllocatedLeaveParam param) {
        return entityToDto(updateReturnEntity(param));
    }

    @Transactional
    public void partialUpdate(UUID userId, LeaveType leaveType, Double totalDays) {
        Optional<AllocatedLeave> allocatedLeave = allocatedLeaveRepository.findByUserId(userId);

        if (allocatedLeave.isPresent()) {
            AllocatedLeave entity = allocatedLeave.get();

            switch (leaveType.getType().toUpperCase()) {
                case "SICK" -> {
                    double taken = entity.getTakenSickLeave() == null ? 0.0 : entity.getTakenSickLeave();
                    entity.setTakenSickLeave(taken + totalDays);
                }
                case "CASUAL" -> {
                    double taken = entity.getTakenCasualLeave() == null ? 0.0 : entity.getTakenCasualLeave();
                    entity.setTakenCasualLeave(taken + totalDays);
                }
                case "ANNUAL" -> {
                    double taken = entity.getTakenAnnualLeave() == null ? 0.0 : entity.getTakenAnnualLeave();
                    entity.setTakenAnnualLeave(taken + totalDays);
                }
                case "PAID" -> {
                    double taken = entity.getPaidLeave() == null ? 0.0 : entity.getPaidLeave();
                    entity.setPaidLeave(taken + totalDays);
                }
                default -> throw new IllegalArgumentException("Invalid leave type: " + leaveType.getType());
            }

            entityToDto(entity);
        }
    }

    @Transactional
    public void partialReduce(UUID userId, LeaveType leaveType, Double totalDays) {
        Optional<AllocatedLeave> allocatedLeave = allocatedLeaveRepository.findByUserId(userId);

        if (allocatedLeave.isPresent()) {
            AllocatedLeave entity = allocatedLeave.get();

            switch (leaveType.getType().toUpperCase()) {
                case "SICK" -> {
                    double taken = entity.getTakenSickLeave() == null ? 0.0 : entity.getTakenSickLeave();
                    entity.setTakenSickLeave(taken - totalDays);
                }
                case "CASUAL" -> {
                    double taken = entity.getTakenCasualLeave() == null ? 0.0 : entity.getTakenCasualLeave();
                    entity.setTakenCasualLeave(taken - totalDays);
                }
                case "ANNUAL" -> {
                    double taken = entity.getTakenAnnualLeave() == null ? 0.0 : entity.getTakenAnnualLeave();
                    entity.setTakenAnnualLeave(taken - totalDays);
                }
                case "PAID" -> {
                    double taken = entity.getPaidLeave() == null ? 0.0 : entity.getPaidLeave();
                    entity.setPaidLeave(taken - totalDays);
                }
                default -> throw new IllegalArgumentException("Invalid leave type: " + leaveType.getType());
            }

            entityToDto(entity);
        }
    }

    @Override
    @Transactional
    public AllocatedLeaveDto statusUpdate(UUID id) {
        AllocatedLeave entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = allocatedLeaveRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        AllocatedLeave entity = getEntityById(id);
        allocatedLeaveRepository.delete(entity);
    }

    private AllocatedLeave getEntityById(UUID id) {
        return allocatedLeaveRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Allocated Leave not found with id: " + id));
    }

    private AllocatedLeave createReturnEntity(AllocatedLeaveParam param) {
        AllocatedLeave entity = new AllocatedLeave();
        entity = paramToEntity(param, entity);
        entity.setActive(true);
        return allocatedLeaveRepository.save(entity);
    }

    private AllocatedLeave updateReturnEntity(AllocatedLeaveParam param) {
        AllocatedLeave entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity);
        return allocatedLeaveRepository.save(entity);
    }

    private AllocatedLeaveDto entityToDto(AllocatedLeave entity) {
        return allocatedLeaveMapper.entityToDto(entity);
    }

    private AllocatedLeave paramToEntity(AllocatedLeaveParam param, AllocatedLeave entity) {

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + param.getUserId()));
            entity.setUser(user);
        }
        if(param.getUserId()!=null && param.getFiscalYear() != null) {
            Optional<AllocatedLeave> existingEntity = allocatedLeaveRepository.findByUserIdAndFiscalYear(param.getUserId(), param.getFiscalYear());
            if (existingEntity.isPresent() && (entity.getId() == null || !existingEntity.get().getId().equals(entity.getId()))) {
                    throw new DataAlreadyExistsException("Already allocated leave");
            }
        }

        entity = allocatedLeaveMapper.paramToEntity(param, entity);

        return entity;
    }
}
