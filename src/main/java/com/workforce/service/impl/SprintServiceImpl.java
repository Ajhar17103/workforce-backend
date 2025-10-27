package com.workforce.service.impl;


import com.workforce.dto.master.SprintDto;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.Sprint;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.SprintMapper;
import com.workforce.param.master.SprintParam;
import com.workforce.repository.ProjectRepository;
import com.workforce.repository.SprintRepository;
import com.workforce.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SprintServiceImpl implements SprintService {

    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;

    @Override
    @Transactional
    public SprintDto create(SprintParam param) throws Exception {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    public SprintDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<SprintDto> getByProjectId(UUID id) {
        projectRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + id));

        return sprintRepository.findByProjectIdOrderByCreatedAtDesc(id)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private Sprint getEntityById(UUID id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Sprint not found with id: " + id));
    }


    @Override
    public List<SprintDto> getAll() {
        return sprintRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SprintDto update(SprintParam param) throws Exception {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public SprintDto statusUpdate(UUID id) throws Exception {
        Sprint entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = sprintRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Sprint entity = getEntityById(id);
        sprintRepository.delete(entity);
    }


    private Sprint createReturnEntity(SprintParam param) throws Exception {
        Sprint entity = new Sprint();

        if (param.getProjectId() != null) {
            Project project = projectRepository.findById(param.getProjectId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getProjectId()));
            entity.setProject(project);
        }

        entity = sprintMapper.paramToEntity(param, entity);

        entity.setActive(true);
        return sprintRepository.save(entity);
    }

    private Sprint updateReturnEntity(SprintParam param) throws Exception {
        Sprint entity = getEntityById(param.getId());

        if (param.getProjectId() != null) {
            Project project = projectRepository.findById(param.getProjectId())
                    .orElseThrow(() -> new DataNotFoundException("Project not found with id: " + param.getProjectId()));
            entity.setProject(project);
        }

        entity = sprintMapper.paramToEntity(param, entity);
        return sprintRepository.save(entity);
    }

    private SprintDto entityToDto(Sprint entity) {
        return sprintMapper.entityToDto(entity);
    }
}
