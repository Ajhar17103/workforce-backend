package com.workforce.service;


import com.workforce.dto.master.TaskDto;
import com.workforce.dto.master.UserDto;
import com.workforce.param.master.TaskParam;
import com.workforce.param.master.UserParam;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskDto create(TaskParam param) throws Exception;

    TaskDto getById(UUID id);

    List<TaskDto> getAll();

    TaskDto update(TaskParam param) throws Exception;

    TaskDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;

    List<TaskDto> getTasksByUserId(UUID userId);
}