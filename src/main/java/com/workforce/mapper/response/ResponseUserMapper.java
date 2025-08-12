package com.workforce.mapper.response;

import com.workforce.dto.auth.ResponseUserDto;
import com.workforce.entity.auth.Role;
import com.workforce.entity.auth.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ResponseUserMapper implements Function<User, ResponseUserDto> {
    @Override
    public ResponseUserDto apply(User user) {
        return user == null ? null : new ResponseUserDto(user.getEmail(), user.getRoles().stream().map(Role::getName).toList());
    }
}
