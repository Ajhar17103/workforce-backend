package com.workforce.param.leave;


import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AllocatedLeaveParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Description of the leave")
    private UUID userId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025", description = "Fiscal Year of the leave")
    private String fiscalYear;

    @NotNull(message = "x0.is.required")
    @Schema(example = "12", description = "Total Sick Leave of the leave")
    private Double totalSickLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "0", description = "Taken Sick Leave of the leave")
    private Double takenSickLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "12", description = "Total Casual Leave of the leave")
    private Double totalCasualLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "0", description = "Taken Casual Leave of the leave")
    private Double takenCasualLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "10", description = "Total Annual Leave of the leave")
    private Double totalAnnualLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "0", description = "Taken Annual Leave of the leave")
    private Double takenAnnualLeave;

    @NotNull(message = "x0.is.required")
    @Schema(example = "0", description = "Paid Leave of the leave")
    private Double paidLeave;
}