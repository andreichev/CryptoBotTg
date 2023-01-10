package com.andreichev.crypto_core.services.impl;

import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.mappers.UserMapper;
import com.andreichev.crypto_core.model.User;
import com.andreichev.crypto_core.repository.UsersRepository;
import com.andreichev.crypto_core.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public void saveIfNotExists(UserDto userDto) {
        if(usersRepository.findById(userDto.getChatId()).isPresent()) { return; }
        User user = userMapper.toUser(userDto);
        usersRepository.save(user);
    }
}
