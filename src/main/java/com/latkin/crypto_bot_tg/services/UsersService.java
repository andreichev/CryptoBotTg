package com.latkin.crypto_bot_tg.services;

import com.latkin.crypto_bot_tg.dto.UserDto;

public interface UsersService {
    void saveIfNotExists(UserDto userDto);
}
