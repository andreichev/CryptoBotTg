package com.andreichev.crypto_core.services.impl;

import com.andreichev.crypto_core.dto.CurrencyDto;
import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.model.CurrencyUserData;
import com.andreichev.crypto_core.model.User;
import com.andreichev.crypto_core.repository.UsersRepository;
import com.andreichev.crypto_core.services.CryptoService;
import com.andreichev.crypto_core.services.CurrencyGrowthChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyGrowthCheckerImpl implements CurrencyGrowthChecker {
    private final CryptoService cryptoService;
    private final UsersRepository usersRepository;

    private void setCurrenciesAsUsed(User user, List<CurrencyDto> currencies) {
        List<CurrencyUserData> userData = user.getCurrencyUserData();
        for(CurrencyUserData currency: userData) {
            currency.setUsed(currencies.stream().anyMatch(item -> item.getName().equals(currency.getName())));
        }
        usersRepository.save(user);
    }

    @Override
    public void start(UserDto userDto) {
        reset(userDto);
        System.out.println("RESET FINISHED");
        User user = usersRepository.getReferenceById(userDto.getChatId());
        List<CurrencyDto> currencies = cryptoService.getCurrencies();
        List<CurrencyUserData> userInitialData = currencies.stream().map(
                item -> CurrencyUserData.builder()
                        .name(item.getName())
                        .price(item.getCurrentPrice())
                        .user(user)
                        .used(false)
                        .build()
        ).collect(Collectors.toList());
        user.setCurrencyUserData(userInitialData);
        System.out.println("CURRENCY DATA UPDATED");
        usersRepository.save(user);
        System.out.println("USER SAVED");
    }

    @Override
    public List<CurrencyDto> getSuitableCurrencies(UserDto userDto) {
        List<CurrencyDto> currencies = cryptoService.getCurrencies();
        User user = usersRepository.getReferenceById(userDto.getChatId());
        List<CurrencyUserData> userData = user.getCurrencyUserData();
        Map<String, Float> initialData = new HashMap<>();
        for (CurrencyUserData userCurrency: userData) {
            if(userCurrency.isUsed()) { continue; }
            initialData.put(userCurrency.getName(), userCurrency.getPrice());
        }
        List<CurrencyDto> result = currencies.stream()
                .filter(item -> {
                    Float initialPrice = initialData.get(item.getName());
                    if (initialPrice == null) { return false; }
                    return isGrownNPercent(initialPrice, item.getCurrentPrice(), userDto.getN());
                })
                .collect(Collectors.toList());
        setCurrenciesAsUsed(user, result);
        return result;
    }

    @Override
    public void reset(UserDto userDto) {
        User user = usersRepository.getReferenceById(userDto.getChatId());
        user.getCurrencyUserData().clear();
        usersRepository.save(user);
    }

    private boolean isGrownNPercent(float initial, float current, int n) {
        float p = n / 100.f;
        float grown = initial * (1.f + p);
        return current >= grown;
    }
}
