package com.latkin.crypto_bot_tg.services;

import com.latkin.crypto_bot_tg.dto.CurrencyDto;
import com.latkin.crypto_bot_tg.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyGrowthChecker {
    void start(UserDto userDto);
    // Выдает валюты, которые выросли более чем на N процентов от начала. Валюты выдаются только один раз
    List<CurrencyDto> getSuitableCurrencies(UserDto userDto);
    void reset(UserDto userDto);
}
