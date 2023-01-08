package com.latkin.crypto_bot_tg.services;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface TelegramBotService {
    void sendMessage(long chatId, String text) throws TelegramApiException;
}
