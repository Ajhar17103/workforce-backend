package com.workforce.service;


import com.workforce.dto.task_board.TaskDto;
import com.workforce.param.task_board.TaskParam;

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