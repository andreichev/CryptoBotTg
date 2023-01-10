package com.andreichev.crypto_core.services.impl;

import com.andreichev.crypto_core.dto.CurrencyDto;
import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.mappers.UserMapper;
import com.andreichev.crypto_core.model.User;
import com.andreichev.crypto_core.repository.UsersRepository;
import com.andreichev.crypto_core.services.CurrencyGrowthChecker;
import com.andreichev.crypto_core.services.UserNotifierService;
import com.andreichev.crypto_core.tg_bot.TelegramBotController;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotifierServiceImpl implements UserNotifierService {
    private final TelegramBotController telegramBotService;
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
