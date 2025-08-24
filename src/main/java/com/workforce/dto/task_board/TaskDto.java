package com.workforce.dto.task_board;


import com.workforce.dto.BaseDto;
import com.workforce.enums.TaskStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TaskDto extends BaseDto {

    private UUID id;

    private UUID projectId;

    private String projectName;

    private UUID sprintId;

    private String sprintName;

    private UUID userId;

    private String userName;

    private String name;

    private String description;

    private String taskTracker;

    private String priority;

    private String taskType;

    private LocalDate startDate;

    private String estimatedTime;

    private String fileName;

    private String fileUrl;

    private TaskStatus taskStatus;

    private String challenges;

    private String remarks;
}


