package com.workforce.service;


import com.workforce.dto.master.SprintDto;
import com.workforce.param.master.SprintParam;

import java.util.List;
import java.util.UUID;

public interface SprintService {

    SprintDto create(SprintParam param) throws Exception;

    SprintDto getById(UUID id);

    List<SprintDto> getByProjectId(UUID id);

    List<SprintDto> getAll();

    SprintDto update(SprintParam param) throws Exception;

    SprintDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}