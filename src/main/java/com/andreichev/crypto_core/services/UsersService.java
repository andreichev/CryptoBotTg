package com.andreichev.crypto_core.services;

import com.andreichev.crypto_core.dto.UserDto;

public interface UsersService {
    void saveIfNotExists(UserDto userDto);
}
