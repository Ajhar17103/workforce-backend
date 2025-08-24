package com.workforce.entity.master;

import com.workforce.entity.BaseEntity;
import com.workforce.enums.Priority;
import com.workforce.enums.TaskTracker;
import com.workforce.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE tasks SET is_deleted=true WHERE id=?")
public class Task extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Convert(converter = TaskTracker.TaskTrackerConverter.class)
    private TaskTracker  taskTracker = TaskTracker.UNKNOWN;

    @Convert(converter = Priority.PriorityConverter.class)
    private Priority priority=Priority.UNKNOWN;

    @Convert(converter = TaskType.TaskTypeConverter.class)
    private TaskType taskType=TaskType.UNKNOWN;

    private LocalDate startDate;

    private String estimatedTime;

    @Lob
    @Column(columnDefinition = "TEXT")
    private File file;
}