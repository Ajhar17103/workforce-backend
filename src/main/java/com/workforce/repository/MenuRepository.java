package com.workforce.repository;


import com.workforce.entity.master.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID>, JpaSpecificationExecutor<Menu> {

    Optional<Menu> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}

