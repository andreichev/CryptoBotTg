package com.latkin.crypto_bot_tg.services;

import com.latkin.crypto_bot_tg.dto.CurrencyDto;

import java.util.List;

public interface CryptoService {
    List<CurrencyDto> getCurrencies();
}
