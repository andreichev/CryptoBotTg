package com.latkin.crypto_bot_tg.services.impl;

import com.latkin.crypto_bot_tg.dto.CurrencyDto;
import com.latkin.crypto_bot_tg.dto.UserDto;
import com.latkin.crypto_bot_tg.mappers.UserMapper;
import com.latkin.crypto_bot_tg.model.User;
import com.latkin.crypto_bot_tg.repository.UsersRepository;
import com.latkin.crypto_bot_tg.services.CurrencyGrowthChecker;
import com.latkin.crypto_bot_tg.services.TelegramBotService;
import com.latkin.crypto_bot_tg.services.UserNotifierService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotifierServiceImpl implements UserNotifierService {
    private final TelegramBotService telegramBotService;
    private final UsersRepository usersRepository;
    private final CurrencyGrowthChecker currencyGrowthChecker;
    private final UserMapper userMapper;

    @Override
    @SneakyThrows
    @Scheduled(fixedDelay = 15000)
    public void update() {
        List<User> users = usersRepository.findAll();
        for(User user: users) {
            UserDto userDto = userMapper.toDto(user);
            List<CurrencyDto> currencies = currencyGrowthChecker.getSuitableCurrencies(userDto);
            if(currencies.isEmpty()) { continue; }
            StringBuilder message = new StringBuilder();
            for(CurrencyDto currencyDto: currencies) {
                message.append("Валюта: ");
                message.append(currencyDto.getName());
                message.append(". Стоимость на ");
                message.append(user.getStartTime());
                message.append(": ");
                message.append(currencyDto.getStartPrice());
                message.append(". Текущая стоимость: ");
                message.append(currencyDto.getCurrentPrice());
                message.append("\n");
            }
            telegramBotService.sendMessage(user.getChatId(), message.toString());
        }
    }
}
