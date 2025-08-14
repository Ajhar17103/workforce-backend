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
public class RoleMenuPermissionParam extends BaseParam {

    @NotNull
    private UUID roleId;

    @NotNull
    private UUID menuId;

    private Boolean view = Boolean.FALSE;
    private Boolean add = Boolean.FALSE;
    private Boolean update = Boolean.FALSE;
    private Boolean delete = Boolean.FALSE;
}

