package com.workforce.service.impl;

import com.workforce.dto.master.UserRoleDto;
import com.workforce.entity.master.UserRole;
import com.workforce.mapper.UserRoleMapper;
import com.workforce.param.master.UserRoleParam;
import com.workforce.repository.UserRoleRepository;
import com.workforce.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;


    @Override
    @Transactional
    public UserRoleDto create(UserRoleParam param) throws Exception {
        UserRole entity = userRoleMapper.toEntity(param);
        entity = userRoleRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public UserRoleDto getById(UUID id) {
        UserRole entity = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        return entityToDto(entity);
    }

    @Override
    public Page<UserRoleDto> getAll(Pageable pageable) {
        return userRoleRepository.findAll(pageable).map(this::entityToDto);
    }

    @Override
    public List<UserRoleDto> getAll(Sort sort) {
        List<UserRole> role = userRoleRepository.findAll(sort);
        return role.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserRoleDto update(UserRoleParam param) throws Exception {
        UserRole existingRole = userRoleRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + param.getId()));

        userRoleMapper.mergeMenuInfo(existingRole, param);
        UserRole updatedMenu = userRoleRepository.save(existingRole);
        return entityToDto(updatedMenu);
    }

    @Override
    @Transactional
    public UserRoleDto statusUpdate(UUID id) throws Exception {
        UserRole entity = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        entity.setActive(!entity.getActive());
        entity = userRoleRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        UserRole entity = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Role not found with id: " + id));
        userRoleRepository.delete(entity);
    }

    private UserRoleDto entityToDto(UserRole entity) {
        return userRoleMapper.toDto(entity);
    }
}
