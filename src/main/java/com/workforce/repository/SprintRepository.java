package com.workforce.repository;

import com.workforce.entity.master.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, UUID> {
    Collection<Sprint> findByProjectId(UUID projectId);
}
