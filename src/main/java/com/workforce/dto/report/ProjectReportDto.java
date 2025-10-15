package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
import com.workforce.dto.master.UserDto;
import com.workforce.enums.Status;
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
public class ProjectReportDto extends BaseDto {

    private String name;

    private String description;

    private Status status;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<UserSummaryDto> assignedUsers;

    private Integer totalTasks;

    private Integer completedTasks;

    private Integer inProgressTasks;

    private Integer onHoldTasks;
}
