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
        dto.setDesignationId(entity.getDesignation() != null ? entity.getDesignation().getId() : null);
        dto.setRoleId(entity.getRole() != null ? entity.getRole().getId() : null);
        dto.setDob(entity.getDob());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCurrentAddress(entity.getCurrentAddress());
        dto.setPresentAddress(entity.getPresentAddress());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setProfileIcon(entity.getProfileIcon());
        return dto;
    }
}

