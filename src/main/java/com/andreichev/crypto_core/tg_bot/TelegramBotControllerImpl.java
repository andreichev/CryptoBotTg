package com.andreichev.crypto_core.tg_bot;

import com.andreichev.crypto_core.config.BotConfig;
import com.andreichev.crypto_core.dto.UserDto;
import com.andreichev.crypto_core.services.CurrencyGrowthChecker;
import com.andreichev.crypto_core.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramBotControllerImpl extends TelegramLongPollingBot implements TelegramBotController {

    private final UsersService usersService;
    private final CurrencyGrowthChecker currencyChecker;
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            var chat = update.getMessage().getChat();
            String name = chat.getUserName();
            UserDto userDto = UserDto.builder().name(name).n(100).chatId(chatId).build();
            usersService.saveIfNotExists(userDto);
            changeOperation(messageText, userDto, chatId);
        }
    }

    @Override
    public void sendMessage(long chatId, String messageToSend) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);
        execute(message);
    }

    @SneakyThrows
    private void changeOperation(String messageText, UserDto userDto, long chatId) {
        switch (messageText) {
            case "/start":
                startCommandReceived(chatId, userDto);
            case "/reset":
                currencyChecker.reset(userDto);
            default:

        }
    }

    @SneakyThrows
    private void startCommandReceived(long chatId, UserDto userDto) {
        usersService.saveIfNotExists(userDto);
        System.out.println("CHECKED IF EXISTS");
        currencyChecker.start(userDto);
        sendMessage(chatId, "TI KRISA");
    }


}
