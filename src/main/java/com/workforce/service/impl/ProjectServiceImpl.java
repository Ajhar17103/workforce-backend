package com.workforce.service.impl;

import com.workforce.dto.master.ProjectDto;
import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.User;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.ProjectMapper;
import com.workforce.param.master.ProjectParam;
import com.workforce.repository.ProjectRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectDto create(ProjectParam param) throws Exception {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    public ProjectDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    private Project getEntityById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + id));
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectDto update(ProjectParam param) throws Exception {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public ProjectDto statusUpdate(UUID id) throws Exception {
        Project entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = projectRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Project entity = getEntityById(id);
        projectRepository.delete(entity);
    }

    private Project createReturnEntity(ProjectParam param) throws Exception {
        Project entity = new Project();

        entity = projectMapper.paramToEntity(param, entity);

        return projectRepository.save(entity);
    }

    private Project updateReturnEntity(ProjectParam param) throws Exception {
        Project entity = getEntityById(param.getId());

        entity = projectMapper.paramToEntity(param, entity);

        if (entity.getAssignUser() != null) {
            entity.setAssignUser(new ArrayList<>(entity.getAssignUser()));
        } else {
            entity.setAssignUser(new ArrayList<>());
        }

        return projectRepository.save(entity);
    }

    private ProjectDto entityToDto(Project entity) {
        return projectMapper.entityToDto(entity);
    }
}

