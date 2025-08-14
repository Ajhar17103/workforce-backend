package com.workforce.mapper;


import com.workforce.dto.master.UserRoleDto;
import com.workforce.entity.master.UserRole;
import com.workforce.param.master.UserRoleParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoleMapper {

    public UserRole toEntity(final UserRoleParam request) {
        return UserRole.builder()
                .name(request.getName())
                .active(true)
                .build();
    }

    public UserRoleDto toDto(final UserRole entity) {
        if (entity == null) {
            return null;
        }
        UserRoleDto dto = new UserRoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public void mergeMenuInfo(final UserRole entity, final UserRoleParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !entity.getName().equals(request.getName())) {
            entity.setName(request.getName());
        }
    }


}
