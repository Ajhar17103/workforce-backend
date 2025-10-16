package com.workforce.repository;


import com.workforce.entity.task_board.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskOnHoldRepository extends JpaRepository<Task, UUID> {}
