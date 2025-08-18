package com.workforce.mapper;


import com.workforce.dto.master.RoleDto;
import com.workforce.entity.master.Role;
import com.workforce.param.master.RoleParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Role toEntity(final RoleParam request) {
        return Role.builder()
                .name(request.getName())
                .active(true)
                .build();
    }

    public RoleDto toDto(final Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public void mergeRoleInfo(final Role entity, final RoleParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !entity.getName().equals(request.getName())) {
            entity.setName(request.getName());
        }
    }
}
