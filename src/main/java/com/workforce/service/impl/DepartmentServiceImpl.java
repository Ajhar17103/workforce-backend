package com.workforce.service.impl;

import com.workforce.dto.master.DepartmentDto;
import com.workforce.entity.master.Department;
import com.workforce.mapper.DepartmentMapper;
import com.workforce.param.master.DepartmentParam;
import com.workforce.repository.DepartmentRepository;
import com.workforce.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    @Override
    @Transactional
    public DepartmentDto create(DepartmentParam param) throws Exception {
        Department entity = departmentMapper.toEntity(param);
        entity = departmentRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public DepartmentDto getById(UUID id) {
        Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        return entityToDto(entity);
    }

    @Override
    public Page<DepartmentDto> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public List<DepartmentDto> getAll() {
        List<Department> role = departmentRepository.findAll();
        return role.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentDto update(DepartmentParam param) throws Exception {
        Department existingRole = departmentRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + param.getId()));

        departmentMapper.mergeDepartmentInfo(existingRole, param);
        Department updatedMenu = departmentRepository.save(existingRole);
        return entityToDto(updatedMenu);
    }

    @Override
    @Transactional
    public DepartmentDto statusUpdate(UUID id) throws Exception {
        Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        entity.setActive(!entity.getActive());
        entity = departmentRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.delete(entity);
    }

    private DepartmentDto entityToDto(Department entity) {
        return departmentMapper.toDto(entity);
    }
}
