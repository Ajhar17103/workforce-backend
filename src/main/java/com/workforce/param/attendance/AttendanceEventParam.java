package com.workforce.param.attendance;

import com.workforce.enums.AttendanceType;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AttendanceEventParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    private AttendanceType type;

    @NotNull(message = "x0.is.required")
    private LocalDateTime timestamp;

    private Double lat;

    private Double lng;

    private String remarks;
}