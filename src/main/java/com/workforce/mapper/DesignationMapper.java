package com.workforce.mapper;


import com.workforce.dto.master.DesignationDto;
import com.workforce.entity.master.Department;
import com.workforce.entity.master.Designation;
import com.workforce.param.master.DesignationParam;
import com.workforce.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DesignationMapper {

    private final DepartmentRepository departmentRepository;

    public Designation toEntity(final DesignationParam request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid departmentId: " + request.getDepartmentId()));

        return Designation.builder()
                .department(department)
                .name(request.getName())
                .active(true)
                .build();
    }

    public DesignationDto toDto(final Designation entity) {
        if (entity == null) {
            return null;
        }
        DesignationDto dto = new DesignationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDepartmentId(entity.getDepartment() != null ? entity.getDepartment().getId().toString() : null);
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public void mergeDepartmentInfo(final Designation entity, final DesignationParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !entity.getName().equals(request.getName())) {
            entity.setName(request.getName());
        }

        if (request.getDepartmentId() != null &&
                (entity.getDepartment() == null || !entity.getDepartment().getId().equals(request.getDepartmentId()))) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid departmentId: " + request.getDepartmentId()));
            entity.setDepartment(department);
        }
    }
}

