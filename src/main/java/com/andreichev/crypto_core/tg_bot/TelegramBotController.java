package com.andreichev.crypto_core.tg_bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface TelegramBotController {
    void sendMessage(long chatId, String text) throws TelegramApiException;
}
