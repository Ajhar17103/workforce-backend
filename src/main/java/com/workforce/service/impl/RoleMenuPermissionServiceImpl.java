package com.workforce.service.impl;

import com.workforce.dto.master.RoleMenuPermissionDto;
import com.workforce.entity.master.Menu;
import com.workforce.entity.master.RoleMenuPermission;
import com.workforce.entity.master.Role;
import com.workforce.mapper.RoleMenuPermissionMapper;
import com.workforce.param.master.RoleMenuPermissionParam;
import com.workforce.repository.MenuRepository;
import com.workforce.repository.RoleMenuPermissionRepository;
import com.workforce.repository.RoleRepository;
import com.workforce.service.RoleMenuPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleMenuPermissionServiceImpl implements RoleMenuPermissionService {

    private final RoleMenuPermissionRepository roleMenuPermissionRepository;
    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;
    private final RoleMenuPermissionMapper roleMenuPermissionMapper;

    @Override
    public List<RoleMenuPermissionDto> getAll(UUID roleId) {
        return roleMenuPermissionRepository.findByRoleId(roleId)
                .stream()
                .map(roleMenuPermissionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<RoleMenuPermissionDto> create(UUID roleId, List<RoleMenuPermissionParam> params) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        for (RoleMenuPermissionParam param : params) {
            Menu menu = menuRepository.findById(param.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));

            RoleMenuPermission existing = roleMenuPermissionRepository.findByRoleId(roleId)
                    .stream()
                    .filter(r -> r.getMenu().getId().equals(menu.getId()))
                    .findFirst()
                    .orElse(null);

            if (existing == null) {
                roleMenuPermissionRepository.save(roleMenuPermissionMapper.toEntity(param, role, menu));
            } else {
                existing.setViewPermission(param.getView());
                existing.setAddPermission(param.getAdd());
                existing.setUpdatePermission(param.getUpdate());
                existing.setDeletePermission(param.getDelete());
                roleMenuPermissionRepository.save(existing);
            }
        }
        return null;
    }
}

