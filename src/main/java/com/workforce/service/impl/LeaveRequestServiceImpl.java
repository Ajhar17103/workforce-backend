package com.workforce.service.impl;

import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.dto.leave.LeaveRequestDto;
import com.workforce.entity.leave.AllocatedLeave;
import com.workforce.entity.leave.LeaveRequest;
import com.workforce.entity.master.User;
import com.workforce.enums.LeaveStatus;
import com.workforce.enums.LeaveType;
import com.workforce.exception.DataAlreadyExistsException;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.LeaveRequestMapper;
import com.workforce.param.leave.LeaveRequestParam;
import com.workforce.repository.LeaveRequestRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final UserRepository userRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestMapper leaveRequestMapper;
    private final AllocatedLeaveServiceImpl allocatedLeaveServiceImpl;

    @Override
    @Transactional
    public LeaveRequestDto create(LeaveRequestParam param) {
        return entityToDto(createReturnEntity(param));
    }


    @Override
    public List<LeaveRequestDto> getAll() {
        return leaveRequestRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<LeaveRequestDto> getByUserId(UUID id) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByUserId(id);

        if (leaveRequests == null || leaveRequests.isEmpty()) {
            throw new DataNotFoundException("No leave requests found for user with id: " + id);
        }
        return leaveRequests.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LeaveRequestDto update(LeaveRequestParam param) {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public LeaveRequestDto statusUpdate(UUID id) {
        LeaveRequest entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = leaveRequestRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        LeaveRequest entity = getEntityById(id);
        leaveRequestRepository.delete(entity);
    }

    private LeaveRequest getEntityById(UUID id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Allocated Leave not found with id: " + id));
    }

    private LeaveRequest createReturnEntity(LeaveRequestParam param) {
        LeaveRequest entity = new LeaveRequest();
        entity = paramToEntity(param, entity);
        entity.setLeaveStatus(LeaveStatus.PENDING);
        entity.setActive(true);
        updateAllocatedLeave(entity);
        return leaveRequestRepository.save(entity);
    }

    private LeaveRequest updateReturnEntity(LeaveRequestParam param) {
        LeaveRequest entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity);
        if(entity.getLeaveStatus() != null && entity.getLeaveStatus().equals(LeaveStatus.REJECTED) || entity.getLeaveStatus().equals(LeaveStatus.CANCELLED)) {
            reverseAllocatedLeave(entity);
        }
        return leaveRequestRepository.save(entity);
    }

    private LeaveRequestDto entityToDto(LeaveRequest entity) {
        return leaveRequestMapper.entityToDto(entity);
    }

    private LeaveRequest paramToEntity(LeaveRequestParam param, LeaveRequest entity) {
        entity = leaveRequestMapper.paramToEntity(param, entity);

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + param.getUserId()));
            entity.setUser(user);
        }

        AllocatedLeaveDto allocatedLeave = allocatedLeaveServiceImpl.getByUserId(param.getUserId())
                .orElseThrow(() -> new DataNotFoundException("No allocated leave found for user: " + param.getUserId()));

        double requestedDays = entity.getTotalDay();
        String leaveType = String.valueOf(entity.getLeaveType());

        double remaining = switch (leaveType) {
            case "SICK" -> allocatedLeave.getTotalSickLeave() - allocatedLeave.getTakenSickLeave();
            case "CASUAL" -> allocatedLeave.getTotalCasualLeave() - allocatedLeave.getTakenCasualLeave();
            case "ANNUAL" -> allocatedLeave.getTotalAnnualLeave() - allocatedLeave.getTakenAnnualLeave();
            default -> throw new IllegalArgumentException("Invalid leave type: " + leaveType);
        };

        if (requestedDays > remaining) {
            throw new DataAlreadyExistsException("Not enough allocated leave available for type: " + leaveType);
        }

        return entity;
    }


    private void updateAllocatedLeave(LeaveRequest entity) {
        try{
            allocatedLeaveServiceImpl.partialUpdate(entity.getUser().getId(), entity.getLeaveType(), entity.getTotalDay());
        } catch (DataNotFoundException e) {
            log.warn("Data not found while updating allocated leave information", e);
            throw new DataNotFoundException("not found allocated leave");
        } catch (Exception e) {
            log.error("Unexpected error while updating allocated leave information for entity", e);
            throw new DataNotFoundException("not found allocated leave");
        }
    }

    private void reverseAllocatedLeave(LeaveRequest entity) {
        try{
            allocatedLeaveServiceImpl.partialReduce(entity.getUser().getId(), entity.getLeaveType(), entity.getTotalDay());
        } catch (DataNotFoundException e) {
            log.warn("Data not found while updating allocated leave information", e);
            throw new DataNotFoundException("not found allocated leave");
        } catch (Exception e) {
            log.error("Unexpected error while updating allocated leave information for entity", e);
            throw new DataNotFoundException("not found allocated leave");
        }
    }
}
