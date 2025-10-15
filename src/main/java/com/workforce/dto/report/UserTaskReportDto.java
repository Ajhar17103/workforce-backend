package com.workforce.dto.report;


import com.workforce.dto.BaseDto;
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
public class UserTaskReportDto extends BaseDto {

    private String userId;

    private String userName;

    private UUID designationId;

    private String designationName;

    private Integer totalTasks;

    private Integer completedTasks;

    private Integer inProgressTasks;

    private Integer pendingTasks;

    private Double allocatedTime;

    private Double spentTime;
}
