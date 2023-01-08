package com.latkin.crypto_bot_tg.services.impl;

import com.latkin.crypto_bot_tg.dto.UserDto;
import com.latkin.crypto_bot_tg.mappers.UserMapper;
import com.latkin.crypto_bot_tg.model.User;
import com.latkin.crypto_bot_tg.repository.UsersRepository;
import com.latkin.crypto_bot_tg.services.UsersService;
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
