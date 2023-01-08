package com.latkin.crypto_bot_tg.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDto {
    private Long chatId;
    private String name;
    private Instant startTime;
    // Настройка пользователя - процент, выше которого валюты должны удовлетворять условию
    private int n;
}
