package com.azhar.wrokforce.mapper.response;

import com.azhar.wrokforce.dto.ResponseUserDto;
import com.azhar.wrokforce.entity.Role;
import com.azhar.wrokforce.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ResponseUserMapper implements Function<User, ResponseUserDto> {
    @Override
    public ResponseUserDto apply(User user) {
        return user == null ? null : new ResponseUserDto(user.getEmail(), user.getRoles().stream().map(Role::getName).toList());
    }
}
