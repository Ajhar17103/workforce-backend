package com.workforce.param.master;



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
public class DesignationParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;

    @NotNull(message = "x0.is.required")
    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Description of the project")
    private UUID departmentId;

    @NotNull(message = "x0.is.required")
    @Schema(example = "Software Engineer(FullStack)", description = "Name of the designation")
    private String name;

}