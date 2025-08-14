package com.workforce.repository;


import com.workforce.entity.master.RoleMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleMenuPermissionRepository extends JpaRepository<RoleMenuPermission, UUID> {
    List<RoleMenuPermission> findByRoleId(UUID roleId);
}
