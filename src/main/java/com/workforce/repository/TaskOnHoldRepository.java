package com.workforce.repository;


import com.workforce.entity.task_board.TaskOnHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskOnHoldRepository extends JpaRepository<TaskOnHold, UUID> {
    Optional<TaskOnHold> findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(UUID taskId);
    List<TaskOnHold> getTaskByTaskId(UUID taskId);
}
