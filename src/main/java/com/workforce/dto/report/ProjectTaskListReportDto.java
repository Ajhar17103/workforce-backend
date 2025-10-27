package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
import com.workforce.enums.TaskStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectTaskListReportDto extends BaseDto {

    private UUID taskId;

    private String taskName;

    private TaskStatus taskStatus;

    private UUID userId;

    private String userName;
}