package com.workforce.repository;


import com.workforce.entity.master.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DesignationRepository extends JpaRepository<Designation, UUID>{
    List<Designation> findByDepartmentId(UUID departmentId);
}

