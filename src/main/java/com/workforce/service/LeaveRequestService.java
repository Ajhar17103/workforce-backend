package com.workforce.service;

import com.workforce.dto.leave.LeaveRequestDto;
import com.workforce.param.leave.LeaveRequestParam;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {

    LeaveRequestDto create(LeaveRequestParam param) throws Exception;

    List<LeaveRequestDto> getAll();

    LeaveRequestDto getById(UUID id);

    List<LeaveRequestDto> getByUserId(UUID id);

    LeaveRequestDto update(LeaveRequestParam param) throws Exception;

    LeaveRequestDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}