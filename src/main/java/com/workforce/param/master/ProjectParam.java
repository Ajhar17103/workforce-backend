package com.workforce.param.master;


import com.workforce.enums.Status;
import com.workforce.param.BaseParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "New Website Development", description = "Name of the project")
    private String name;

    @NotNull(message = "x0.is.required")
    @Schema(example = "This project involves redesigning the company website", description = "Description of the project")
    private String description;

    @NotNull(message = "x0.is.required")
    @Schema(example = "ACTIVE", description = "Status of the project")
    private Status status;

    @NotNull(message = "x0.is.required")
    @Schema(example = "2025-08-14", description = "Start date of the project")
    private LocalDate startDate;

    @Schema(example = "2025-12-31", description = "End date of the project")
    private LocalDate endDate;

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", description = "Assigned Users of the project")
    private List<UUID> assignUser;
}