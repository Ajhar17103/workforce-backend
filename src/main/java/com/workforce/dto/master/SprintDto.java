package com.workforce.dto.master;


import com.workforce.dto.BaseDto;
import com.workforce.entity.master.Project;
import com.workforce.enums.SprintType;
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
public class SprintDto extends BaseDto {

    private UUID ProjectId;

    private String ProjectName;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer workingDays;

    private Integer dailyWorkingHrs;

    private Integer totalSprintHrs;

    private SprintType sprintType;
}
