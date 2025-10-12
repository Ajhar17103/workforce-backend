package com.workforce.service;

import com.workforce.dto.leave.AllocatedLeaveDto;
import com.workforce.param.leave.AllocatedLeaveParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AllocatedLeaveService{

    AllocatedLeaveDto create(AllocatedLeaveParam param) throws Exception;

    List<AllocatedLeaveDto> allocateLeaveForAllUsers(String fiscalYear) throws Exception;

    List<AllocatedLeaveDto> getAll();

    AllocatedLeaveDto getById(UUID id);

    Optional<AllocatedLeaveDto> getByUserId(UUID id);

    AllocatedLeaveDto update(AllocatedLeaveParam param) throws Exception;

    AllocatedLeaveDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}