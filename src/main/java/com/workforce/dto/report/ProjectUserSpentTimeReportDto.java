package com.workforce.dto.report;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class ProjectUserSpentTimeReportDto{

    private String name;

    private Double allocatedTime;

    private Double spentTime;
}