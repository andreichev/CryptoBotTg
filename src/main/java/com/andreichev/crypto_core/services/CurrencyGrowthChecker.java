package com.andreichev.crypto_core.services;

import com.andreichev.crypto_core.dto.CurrencyDto;
import com.andreichev.crypto_core.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyGrowthChecker {
    void start(UserDto userDto);
    // Выдает валюты, которые выросли более чем на N процентов от начала. Валюты выдаются только один раз
    List<CurrencyDto> getSuitableCurrencies(UserDto userDto);
    void reset(UserDto userDto);
}
