package com.workforce.service.impl;

import com.workforce.dto.master.DesignationDto;
import com.workforce.entity.master.Designation;
import com.workforce.mapper.DesignationMapper;
import com.workforce.param.master.DesignationParam;
import com.workforce.repository.DesignationRepository;
import com.workforce.service.DesignationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository designationRepository;
    private final DesignationMapper designationMapper;

    @Override
    @Transactional
    public DesignationDto create(DesignationParam param) throws Exception {
        Designation entity = designationMapper.toEntity(param);
        entity = designationRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public DesignationDto getById(UUID id) {
        Designation entity = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + id));
        return entityToDto(entity);
    }

    @Override
    public Page<DesignationDto> getAll(Pageable pageable) {
        return designationRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public List<DesignationDto> getAll() {
        List<Designation> designations = designationRepository.findAll();
        return designations.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<DesignationDto> getDesignationsByDepartmentId(UUID departmentId) {
        List<Designation> designations = designationRepository.findByDepartmentId(departmentId);
        return designations.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DesignationDto update(DesignationParam param) throws Exception {
        Designation existingDesignation = designationRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + param.getId()));

        designationMapper.mergeDepartmentInfo(existingDesignation, param);
        Designation updatedDesignation = designationRepository.save(existingDesignation);
        return entityToDto(updatedDesignation);
    }

    @Override
    @Transactional
    public DesignationDto statusUpdate(UUID id) throws Exception {
        Designation entity = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + id));
        entity.setActive(!entity.getActive());
        entity = designationRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Designation entity = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + id));
        designationRepository.delete(entity);
    }

    private DesignationDto entityToDto(Designation entity) {
        return designationMapper.toDto(entity);
    }
}

