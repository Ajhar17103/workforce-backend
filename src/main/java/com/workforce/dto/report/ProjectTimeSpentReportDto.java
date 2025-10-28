package com.workforce.dto.report;

import com.workforce.dto.BaseDto;
import com.workforce.enums.SprintType;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
public class ProjectTimeSpentReportDto extends BaseDto {

    private UUID sprintId;

    private String sprintName;

    private UUID projectId;

    private String projectName;

    private LocalDate startDate;

    private LocalDate endDate;

    private SprintType sprintType;

    private Double allocatedTime;

    private Double spentTime;

    private List<ProjectUserSpentTimeReportDto> users;
}