package com.workforce.dto.master;

import com.workforce.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoleMenuPermissionDto extends BaseDto {
    private UUID roleId;
    private UUID menuId;
    private Boolean view;
    private Boolean add;
    private Boolean update;
    private Boolean delete;
}
