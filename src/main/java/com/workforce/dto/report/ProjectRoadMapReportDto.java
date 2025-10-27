package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
import com.workforce.entity.master.User;
import com.workforce.enums.SprintType;
import com.workforce.enums.TaskTracker;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.internal.LoadingCache;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectRoadMapReportDto extends BaseDto {

    private UUID sprintId;

    private String sprintName;

    private UUID projectId;

    private String projectName;

    private LocalDate startDate;

    private LocalDate endDate;

    private SprintType sprintType;

    private List<ProjectTaskListReportDto> taskList;
}