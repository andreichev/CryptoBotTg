package com.andreichev.crypto_core.mappers;

import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.model.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
