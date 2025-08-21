package com.workforce.param.master;


import com.workforce.enums.SprintType;
import com.workforce.enums.Status;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SprintParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "", description = "Project Id of the sprint")
    private UUID projectId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "Sprint-1", description = "Name of the sprint")
    private String name;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-08-14", description = "Start date of the sprint")
    private LocalDate startDate;

    @Schema(example = "2025-12-31", description = "End date of the sprint")
    private LocalDate endDate;

    @NotNull(message = "x0.is.required")
    @Schema(example = "10", description = "Total Working Days of the sprint")
    private Integer workingDays;

    @NotNull(message = "x0.is.required")
    @Schema(example = "8", description = "Daily Working Hours of the sprint")
    private Integer dailyWorkingHrs;

    @NotNull(message = "x0.is.required")
    @Schema(example = "180", description = "Total Sprint Working Hours of the sprint")
    private Integer totalSprintHrs;

    @NotNull(message = "x0.is.required")
    @Schema(example = "PLANNED", description = "Sprint Type of the sprint")
    private SprintType sprintType;
}