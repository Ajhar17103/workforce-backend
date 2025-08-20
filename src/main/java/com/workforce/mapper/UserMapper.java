package com.workforce.mapper;

import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.User;
import com.workforce.param.master.UserParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User paramToEntity(UserParam param, User entity) {

        if (param.getName() != null) entity.setName(param.getName());
        if (param.getDob() != null) entity.setDob(param.getDob());
        if (param.getPhone() != null) entity.setPhone(param.getPhone());
        if (param.getCurrentAddress() != null) entity.setCurrentAddress(param.getCurrentAddress());
        if (param.getPresentAddress() != null) entity.setPresentAddress(param.getPresentAddress());
        if (param.getBloodGroup() != null) entity.setBloodGroup(param.getBloodGroup());
        if (param.getProfileIcon() != null) entity.setProfileIcon(param.getProfileIcon());
        if (param.getPassword() != null) entity.setPassword(passwordEncoder.encode(param.getPassword()));

        return entity;
    }

    public UserDto entityToDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDepartmentId(entity.getDesignation() != null ? entity.getDesignation().getDepartment()!= null ? entity.getDesignation().getDepartment().getId() : null : null);
        dto.setDepartmentName(entity.getDesignation() != null ? entity.getDesignation().getDepartment()!= null ? entity.getDesignation().getDepartment().getName() : null : null);
        dto.setDesignationId(entity.getDesignation() != null ? entity.getDesignation().getId() : null);
        dto.setDesignationName(entity.getDesignation() != null ? entity.getDesignation().getName() : null);
        dto.setRoleId(entity.getRole() != null ? entity.getRole().getId() : null);
        dto.setRoleName(entity.getRole() != null ? entity.getRole().getName() : null);
        dto.setDob(entity.getDob());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCurrentAddress(entity.getCurrentAddress());
        dto.setPresentAddress(entity.getPresentAddress());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setProfileIcon(entity.getProfileIcon());
        dto.setActive(entity.getActive());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }
}

