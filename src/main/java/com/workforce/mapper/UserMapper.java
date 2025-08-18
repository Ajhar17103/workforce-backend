package com.workforce.mapper;

import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.Designation;
import com.workforce.entity.master.User;
import com.workforce.entity.master.Role;
import com.workforce.param.master.UserParam;
import com.workforce.repository.DesignationRepository;
import com.workforce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final DesignationRepository designationRepository;
    private final RoleRepository roleRepository;

    public User toEntity(final UserParam request) {
        Designation designation = designationRepository.findById(request.getDesignationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid designationId: " + request.getDesignationId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid roleId: " + request.getRoleId()));

        return User.builder()
                .name(request.getName())
                .designation(designation)
                .role(role)
                .dob(request.getDob())
                .phone(request.getPhone())
                .email(request.getEmail())
                .currentAddress(request.getCurrentAddress())
                .presentAddress(request.getPresentAddress())
                .bloodGroup(request.getBloodGroup())
                .profileIcon(request.getProfileIcon())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();
    }

    public UserDto toDto(final User entity) {
        if (entity == null) {
            return null;
        }

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

    public void mergeUserInfo(final User entity, final UserParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !entity.getName().equals(request.getName())) {
            entity.setName(request.getName());
        }

        if (request.getDob() != null) {
            entity.setDob(request.getDob());
        }

        if (StringUtils.isNotBlank(request.getPhone())) {
            entity.setPhone(request.getPhone());
        }

        if (StringUtils.isNotBlank(request.getCurrentAddress())) {
            entity.setCurrentAddress(request.getCurrentAddress());
        }

        if (StringUtils.isNotBlank(request.getPresentAddress())) {
            entity.setPresentAddress(request.getPresentAddress());
        }

        if (request.getBloodGroup() != null) {
            entity.setBloodGroup(request.getBloodGroup());
        }

        if (StringUtils.isNotBlank(request.getProfileIcon())) {
            entity.setProfileIcon(request.getProfileIcon());
        }
    }
}
