package com.workforce.service.impl;

import com.workforce.dto.master.RoleDto;
import com.workforce.entity.master.Role;
import com.workforce.mapper.RoleMapper;
import com.workforce.param.master.RoleParam;
import com.workforce.repository.RoleRepository;
import com.workforce.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    @Transactional
    public RoleDto create(RoleParam param) throws Exception {
        Role entity = roleMapper.toEntity(param);
        entity = roleRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public RoleDto getById(UUID id) {
        Role entity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        return entityToDto(entity);
    }

    @Override
    public Page<RoleDto> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> role = roleRepository.findAll();
        return role.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoleDto update(RoleParam param) throws Exception {
        Role existingRole = roleRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + param.getId()));

        roleMapper.mergeRoleInfo(existingRole, param);
        Role updatedMenu = roleRepository.save(existingRole);
        return entityToDto(updatedMenu);
    }

    @Override
    @Transactional
    public RoleDto statusUpdate(UUID id) throws Exception {
        Role entity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        entity.setActive(!entity.getActive());
        entity = roleRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Role entity = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        roleRepository.delete(entity);
    }

    private RoleDto entityToDto(Role entity) {
        return roleMapper.toDto(entity);
    }
}
