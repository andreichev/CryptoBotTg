package com.latkin.crypto_bot_tg.mappers;

import com.latkin.crypto_bot_tg.dto.UserDto;
import com.latkin.crypto_bot_tg.model.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
