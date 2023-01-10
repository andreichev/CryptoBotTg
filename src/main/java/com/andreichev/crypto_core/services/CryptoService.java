package com.andreichev.crypto_core.services;

import com.andreichev.crypto_core.dto.CurrencyDto;

import java.util.List;

public interface CryptoService {
    List<CurrencyDto> getCurrencies();
}
