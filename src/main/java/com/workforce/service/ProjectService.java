package com.workforce.service;

import com.workforce.dto.master.ProjectDto;
import com.workforce.param.master.ProjectParam;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectDto create(ProjectParam param) throws Exception;

    ProjectDto getById(UUID id);

    List<ProjectDto> getAll();

    ProjectDto update(ProjectParam param) throws Exception;

    ProjectDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
