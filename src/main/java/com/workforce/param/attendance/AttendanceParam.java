package com.workforce.param.attendance;


import com.workforce.enums.*;
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
public class AttendanceParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "61c43006-a31d-4513-a4de-a053fe58a70d", description = "User Id associated with the attendance")
    private UUID userId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-08-25", description = "Date of attendance record (one per user per day)")
    private LocalDate workDate;

    @Schema(example = "OPEN", description = "Attendance status (OPEN / CLOSED)")
    private AttendanceStatus status;

    @Schema(example = "25200", description = "Total staycation time in seconds (excluding breaks/outings)", hidden = true)
    private Long staySeconds;

    private List<AttendanceEventParam> events;
}