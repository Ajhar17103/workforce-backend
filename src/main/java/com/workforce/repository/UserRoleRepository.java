package com.workforce.repository;


import com.workforce.entity.master.Menu;
import com.workforce.entity.master.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID>, JpaSpecificationExecutor<Menu> {
}

