package com.workforce.service.impl;

import com.workforce.entity.master.Project;
import com.workforce.entity.master.Sprint;
import com.workforce.entity.master.Task;
import com.workforce.dto.master.TaskDto;
import com.workforce.entity.master.User;
import com.workforce.exception.DataNotFoundException;
import com.workforce.param.master.TaskParam;
import com.workforce.repository.ProjectRepository;
import com.workforce.repository.SprintRepository;
import com.workforce.repository.TaskRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.TaskService;
import com.workforce.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
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

    @Override
    @Transactional
    public TaskDto create(TaskParam param) throws Exception {
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
    public TaskDto update(TaskParam param) throws Exception {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public TaskDto statusUpdate(UUID id) throws Exception {
        Task entity = getEntityById(id);
        entity.setActive(!entity.getActive()); // ✅ toggle active status (like UserServiceImpl)
        entity = taskRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
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

    private Task createReturnEntity(TaskParam param) throws Exception {
        Task entity = new Task();
        if (param.getProjectId() != null) {
            Project project = projectRepository.findById(param.getProjectId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getProjectId()));
            entity.setProject(project);
        }

        if (param.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(param.getSprintId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getSprintId()));
            entity.setSprint(sprint);
        }

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getUserId()));
            entity.setUser(user);
        }


        entity = taskMapper.paramToEntity(param, entity);
        entity.setActive(true);
        return taskRepository.save(entity);
    }

    private Task updateReturnEntity(TaskParam param) throws Exception {
        Task entity = getEntityById(param.getId());
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

        entity = taskMapper.paramToEntity(param, entity);
        return taskRepository.save(entity);
    }

    private TaskDto entityToDto(Task entity) {
        return taskMapper.entityToDto(entity);
    }
}

