package com.workforce.mapper;

import com.workforce.dto.master.RoleMenuPermissionDto;
import com.workforce.entity.master.Menu;
import com.workforce.entity.master.RoleMenuPermission;
import com.workforce.entity.master.UserRole;
import com.workforce.param.master.RoleMenuPermissionParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMenuPermissionMapper {

    public RoleMenuPermission toEntity(RoleMenuPermissionParam param, UserRole role, Menu menu) {
        return RoleMenuPermission.builder()
                .role(role)
                .menu(menu)
                .viewPermission(param.getView())
                .addPermission(param.getAdd())
                .updatePermission(param.getUpdate())
                .deletePermission(param.getDelete())
                .active(true)
                .build();
    }

    public RoleMenuPermissionDto toDto(RoleMenuPermission entity) {
        return RoleMenuPermissionDto.builder()
                .id(entity.getId())
                .roleId(entity.getRole().getId())
                .menuId(entity.getMenu().getId())
                .view(entity.getViewPermission())
                .add(entity.getAddPermission())
                .update(entity.getUpdatePermission())
                .delete(entity.getDeletePermission())
                .build();
    }
}
