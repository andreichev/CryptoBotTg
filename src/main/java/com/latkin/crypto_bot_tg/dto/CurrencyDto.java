package com.latkin.crypto_bot_tg.dto;

import lombok.Data;

@Data
public class CurrencyDto {
    private String name;
    private float startPrice;
    private float currentPrice; //цена на текущее время
}
