package com.workforce.service.impl;

import com.workforce.dto.master.UserDto;
import com.workforce.entity.master.Designation;
import com.workforce.entity.master.Role;
import com.workforce.entity.master.User;
import com.workforce.exception.DataAlreadyExistsException;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.UserMapper;
import com.workforce.param.master.UserParam;
import com.workforce.repository.DesignationRepository;
import com.workforce.repository.RoleRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DesignationRepository designationRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto create(UserParam param) {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    public UserDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto update(UserParam param) {
        return entityToDto(updateReturnEntity(param));
    }

    @Override
    @Transactional
    public UserDto statusUpdate(UUID id) {
        User entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = userRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        User entity = getEntityById(id);
        userRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        User entity = userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (entity.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + entity.getRole().getName().toUpperCase()));
        }

        return new org.springframework.security.core.userdetails.User(
                entity.getEmail(),
                entity.getPassword(),
                entity.getActive(),
                true,
                true,
                true,
                authorities
        );
    }

    private User getEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));
    }

    private User createReturnEntity(UserParam param) {
        User entity = new User();
        entity = paramToEntity(param, entity, true);
        entity.setActive(true);
        return userRepository.save(entity);
    }

    private User updateReturnEntity(UserParam param) {
        User entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity, false);
        return userRepository.save(entity);
    }

    private UserDto entityToDto(User entity) {
        return userMapper.entityToDto(entity);
    }

    private User paramToEntity(UserParam param, User entity, boolean isCreate) {
        // Check unique email
        if (param.getEmail() != null) {
            userRepository.findByEmailIgnoreCase(param.getEmail()).ifPresent(u -> {
                if (isCreate || !u.getId().equals(param.getId())) {
                    throw new DataAlreadyExistsException("Email already exists: " + param.getEmail());
                }
            });
            entity.setEmail(param.getEmail());
        }

        // Check unique phone
        if (isCreate && param.getPhone() != null) {
            userRepository.findByPhone(param.getPhone()).ifPresent(u -> {
                throw new DataAlreadyExistsException("Phone already exists: " + param.getPhone());
            });
            entity.setPhone(param.getPhone());
        }

        // Map basic fields
        entity = userMapper.paramToEntity(param, entity);

        // Set relations
        if (param.getDesignationId() != null) {
            Designation designation = designationRepository.findById(param.getDesignationId())
                    .orElseThrow(() -> new DataNotFoundException("Designation not found with id: " + param.getDesignationId()));
            entity.setDesignation(designation);
        }

        if (param.getRoleId() != null) {
            Role role = roleRepository.findById(param.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Role not found with id: " + param.getRoleId()));
            entity.setRole(role);
        }

        return entity;
    }
}
