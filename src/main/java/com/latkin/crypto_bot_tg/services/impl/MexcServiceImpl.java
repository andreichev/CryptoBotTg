package com.latkin.crypto_bot_tg.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latkin.crypto_bot_tg.dto.CurrencyDto;
import com.latkin.crypto_bot_tg.services.CryptoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MexcServiceImpl implements CryptoService {
    @SneakyThrows
    @Override
    public List<CurrencyDto> getCurrencies() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.mexc.com/api/v3/ticker/24hr") // https://api.mexc.com/api/v3/exchangeInfo
                .build();

        Call call = client.newCall(request);
        String response = call.execute().body().string();
        return separateAnswer(response);
    }

    private List<CurrencyDto> separateAnswer(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        return new ArrayList<>();
    }
}
