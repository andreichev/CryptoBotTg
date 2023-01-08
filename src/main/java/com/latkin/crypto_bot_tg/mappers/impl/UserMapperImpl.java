package com.latkin.crypto_bot_tg.mappers.impl;

import com.latkin.crypto_bot_tg.dto.UserDto;
import com.latkin.crypto_bot_tg.mappers.UserMapper;
import com.latkin.crypto_bot_tg.model.User;
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
