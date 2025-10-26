package com.workforce.repository;


import com.workforce.entity.task_board.Task;
import com.workforce.entity.task_board.TaskOnHold;
import com.workforce.entity.task_board.TaskOnprogress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskOnProgressRepository extends JpaRepository<TaskOnprogress, UUID> {
    Optional<TaskOnprogress> findTopByTaskIdAndEndDateTimeIsNullOrderByStartDateTimeDesc(UUID taskId);
    List<TaskOnprogress> findAllByTaskId(UUID taskId);
}
