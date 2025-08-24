package com.workforce.param.task_board;


import com.workforce.enums.Priority;
import com.workforce.enums.TaskStatus;
import com.workforce.enums.TaskTracker;
import com.workforce.enums.TaskType;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TaskParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", description = "Project Id associated with the task")
    private UUID projectId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "f290f1ee-6c54-4b01-90e6-d701748f0851", description = "Sprint Id associated with the task")
    private UUID sprintId;


    @Schema(example = "u290f1ee-6c54-4b01-90e6-d701748f0851", description = "User Id assigned to the task")
    private UUID userId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "Implement Login API", description = "Name of the task")
    private String name;

    @NotNull(message = "description.is.required")
    @Schema(example = "Create REST API for user login with JWT", description = "Detailed description of the task")
    private String description;

    @NotNull(message = "x0.is.required")
    @Schema(example = "SPRINT", description = "Current tracker status of the task")
    private TaskTracker taskTracker;

    @NotNull(message = "x0.is.required")
    @Schema(example = "HIGH", description = "Priority of the task")
    private Priority priority;

    @NotNull(message = "x0.is.required")
    @Schema(example = "PLANNED", description = "Type of task")
    private TaskType taskType;

    @Schema(example = "2025-08-21", description = "Planned start date of the task")
    private LocalDate startDate;

    @Schema(example = "02:30:00", description = "Estimated time to complete the task (HH:mm:ss)")
    private String estimatedTime;

    @Schema(type = "string", format = "binary", description = "Optional file attachment for the task")
    private MultipartFile file;

    @NotNull(message = "x0.is.required")
    @Schema(example = "To_DO", description = "Task Status of the task")
    private TaskStatus taskStatus;

    @Schema(example = "Need more clarity on requirements", description = "Challenges faced while working on the task",hidden = true)
    private String challenges;

    @Schema(example = "Task completed successfully within deadline", description = "Additional remarks or notes about the task",hidden = true)
    private String remarks;
}

