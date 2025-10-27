package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
import com.workforce.enums.Status;
import com.workforce.enums.TaskTracker;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectOverviewReportDto extends BaseDto {

    private String name;

    private Integer total;

    private Integer open;

    private Integer closed;

    private Integer hold;


}