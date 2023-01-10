package com.andreichev.crypto_core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDto {
    private String name;
    private float startPrice;
    private float currentPrice; //цена на текущее время
}
