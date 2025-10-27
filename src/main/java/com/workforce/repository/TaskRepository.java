package com.workforce.repository;


import com.workforce.entity.master.Project;
import com.workforce.entity.master.User;
import com.workforce.entity.task_board.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.deleted = false")
    List<Task> taskByUserId(@Param("userId") UUID userId);

    List<Task> findByProject(Project project);

    List<Task> findByUser(User user);

    List<Task> findByProjectId(UUID projectId);
}
