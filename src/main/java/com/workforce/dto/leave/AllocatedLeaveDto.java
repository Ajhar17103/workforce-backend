package com.workforce.dto.leave;


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
public class AllocatedLeaveDto extends BaseDto {

    private UUID userId;

    private String userName;

    private String fiscalYear;

    private Double totalSickLeave;

    private Double takenSickLeave;

    private Double totalCasualLeave;

    private Double takenCasualLeave;

    private Double totalAnnualLeave;

    private Double takenAnnualLeave;

    private Double paidLeave;
}
