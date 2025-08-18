package com.workforce.service;


import com.workforce.dto.master.UserDto;
import com.workforce.param.master.UserParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    UserDto create(UserParam param) throws Exception;

    UserDto getById(UUID id);

    Page<UserDto> getAll(Pageable pageable);

    List<UserDto> getAll();

    UserDto update(UserParam param) throws Exception;

    UserDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}