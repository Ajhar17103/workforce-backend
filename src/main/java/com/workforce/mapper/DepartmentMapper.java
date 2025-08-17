package com.workforce.mapper;


import com.workforce.dto.master.DepartmentDto;
import com.workforce.entity.master.Department;
import com.workforce.param.master.DepartmentParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    public Department toEntity(final DepartmentParam request) {
        return Department.builder()
                .name(request.getName())
                .active(true)
                .build();
    }

    public DepartmentDto toDto(final Department entity) {
        if (entity == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public void mergeDepartmentInfo(final Department entity, final DepartmentParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !entity.getName().equals(request.getName())) {
            entity.setName(request.getName());
        }
    }
}
