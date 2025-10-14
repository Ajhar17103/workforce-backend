package com.workforce.param.daily_standup;

import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DailyStandupParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Description of the daily standup")
    private UUID userId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-10-12", description = "From Date of the daily standup")
    private LocalDate date;

    @NotNull(message = "x0.is.required")
    @Schema(example = "a short, focused meeting, typically 15 minutes", description = "Description of the daily standup")
    private String description;

    @Schema(example = "the challenges teams face in making their daily stand-up meetings effective", description = "Challenge of the daily standup")
    private String challenge;
}