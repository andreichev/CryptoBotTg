package com.andreichev.crypto_core.mappers.impl;

import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.mappers.UserMapper;
import com.andreichev.crypto_core.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .n(user.getN())
                .chatId(user.getChatId())
                .startTime(user.getStartTime())
                .build();
    }

    @Override
    public User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .n(userDto.getN())
                .chatId(userDto.getChatId())
                .startTime(userDto.getStartTime())
                .build();
    }
}
