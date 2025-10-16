package com.workforce.entity.task_board;

import com.workforce.entity.BaseEntity;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.Sprint;
import com.workforce.entity.master.User;
import com.workforce.enums.Priority;
import com.workforce.enums.TaskStatus;
import com.workforce.enums.TaskTracker;
import com.workforce.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_holds")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE task_holds SET is_deleted=true WHERE id=?")
public class TaskOnHold extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}