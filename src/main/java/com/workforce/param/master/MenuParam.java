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
public class MenuParam extends BaseParam {

    @Schema(hidden = true)
    private UUID id;


    @NotNull(message = "x0.is.required")
    @Schema(example = "Dashboard", description = "Name of the menu")
    private String name;


    @Schema(example = "Dashboard", description = "Icon of the menu")
    private String icon;

    @Schema(example = "/dashboard", description = "Path of the menu")
    private String path;

    @Schema(example = "MAIN", description = "Menu Type of the menu", allowableValues = {"MAIN", "SUB"})
    private String menuType;

    @Schema(example = "e8b5a51a-4c3a-11ec-81d3-0242ac130003", description = "Main Menu  for the sub menu")
    private String parentId;
}