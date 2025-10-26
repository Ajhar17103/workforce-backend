package com.workforce.service.impl;

import com.workforce.entity.master.Project;
import com.workforce.entity.master.Sprint;
import com.workforce.entity.task_board.Task;
import com.workforce.dto.task_board.TaskDto;
import com.workforce.entity.master.User;
import com.workforce.entity.task_board.TaskOnHold;
import com.workforce.entity.task_board.TaskOnprogress;
import com.workforce.enums.TaskStatus;
import com.workforce.exception.DataNotFoundException;
import com.workforce.param.task_board.TaskParam;
import com.workforce.repository.*;
import com.workforce.service.TaskService;
import com.workforce.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final SprintRepository sprintRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskOnHoldRepository taskOnHoldRepository;
    private final TaskOnProgressRepository taskOnProgressRepository;

    @Override
    @Transactional
    public TaskDto create(TaskParam param) {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    public TaskDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDto update(TaskParam param) {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public TaskDto statusUpdate(UUID id) {
        Task entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = taskRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Task entity = getEntityById(id);
        taskRepository.delete(entity);
    }

    @Override
    public List<TaskDto> getTasksByUserId(UUID userId) {
        return taskRepository.taskByUserId(userId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private Task getEntityById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Task not found with id: " + id));
    }

    private Task createReturnEntity(TaskParam param) {
        Task entity = new Task();
        entity = paramToEntity(param, entity);
        entity.setActive(true);
        return taskRepository.save(entity);
    }

    @Transactional
    private Task updateReturnEntity(TaskParam param) {
        Task entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity);

        TaskStatus status = param.getTaskStatus();
        if (status != null) {
            switch (status) {
                case HOLD -> taskOnHold(param);
                case IN_PROGRESS -> taskOnProgress(param);
                case COMPLETED -> taskCompleted(param);
            }
            entity.setTaskStatus(status);
        }

        return taskRepository.save(entity);
    }




    private TaskDto entityToDto(Task entity) {
        return taskMapper.entityToDto(entity);
    }

    private Task paramToEntity(TaskParam param, Task entity) {

        entity = taskMapper.paramToEntity(param, entity);

        if (param.getProjectId() != null) {
            Project project = projectRepository.findById(param.getProjectId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getProjectId()));
            entity.setProject(project);
        }

        if (param.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(param.getSprintId())
                    .orElseThrow(() -> new DataNotFoundException("Sprint not found with id: " + param.getSprintId()));
            entity.setSprint(sprint);
        }

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + param.getUserId()));
            entity.setUser(user);
        }

        return entity;
    }

    @Transactional
    private void taskOnHold(TaskParam param) {
        Task task = getEntityById(param.getId());

        // ✅ Close any open progress record
        taskOnProgressRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .ifPresent(activeProgress -> {
                    activeProgress.setEndDateTime(LocalDateTime.now());
                    taskOnProgressRepository.save(activeProgress);
                });

        // ✅ Prevent duplicate open hold record
        if (taskOnHoldRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .isPresent()) {
            throw new IllegalStateException("Task is already on hold");
        }

        // ✅ Create new on-hold record
        TaskOnHold taskOnHold = new TaskOnHold();
        taskOnHold.setTask(task);
        taskOnHold.setStartDateTime(LocalDateTime.now());
        taskOnHold.setEndDateTime(null);

        taskOnHoldRepository.save(taskOnHold);
    }

    @Transactional
    private void taskOnProgress(TaskParam param) {
        Task task = getEntityById(param.getId());

        // ✅ Close any open hold record
        taskOnHoldRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .ifPresent(activeHold -> {
                    activeHold.setEndDateTime(LocalDateTime.now());
                    taskOnHoldRepository.save(activeHold);
                });

        // ✅ Prevent duplicate open progress record
        if (taskOnProgressRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .isPresent()) {
            throw new IllegalStateException("Task is already in progress");
        }

        // ✅ Create new progress record
        TaskOnprogress progress = new TaskOnprogress();
        progress.setTask(task);
        progress.setStartDateTime(LocalDateTime.now());
        progress.setEndDateTime(null);

        taskOnProgressRepository.save(progress);
    }

    @Transactional
    private void taskCompleted(TaskParam param) {
        Task task = getEntityById(param.getId());

        // ✅ Close open progress record
        taskOnProgressRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .ifPresent(activeProgress -> {
                    activeProgress.setEndDateTime(LocalDateTime.now());
                    taskOnProgressRepository.save(activeProgress);
                });

        // ✅ Close open hold record (if any)
        taskOnHoldRepository
                .findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(task.getId())
                .ifPresent(activeHold -> {
                    activeHold.setEndDateTime(LocalDateTime.now());
                    taskOnHoldRepository.save(activeHold);
                });
    }

}
