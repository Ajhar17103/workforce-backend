package com.workforce.repository;


import com.workforce.entity.master.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID>, JpaSpecificationExecutor<Menu> {

    @Query("SELECT m FROM Menu m ORDER BY m.createdAt ASC")
    List<Menu> findAllBy();

}

