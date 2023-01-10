package com.andreichev.crypto_core.services.impl;

import com.andreichev.crypto_core.dto.CurrencyDto;
import com.andreichev.crypto_core.dto.MexcCurrencyDto;
import com.andreichev.crypto_core.services.CryptoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MexcServiceImpl implements CryptoService {
    @SneakyThrows
    @Override
    public List<CurrencyDto> getCurrencies() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.mexc.com/api/v3/ticker/price") // https://api.mexc.com/api/v3/exchangeInfo
                .build();

        Call call = client.newCall(request);
        ResponseBody responseBody = call.execute().body();
        if(responseBody == null) { return new ArrayList<>(); }
        return parse(responseBody.string());
    }

    private List<CurrencyDto> parse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MexcCurrencyDto[] result = objectMapper.readValue(response, MexcCurrencyDto[].class);
            return Arrays.stream(result)
                    .map(item -> new CurrencyDto(
                            item.getSymbol(),
                            Float.parseFloat(item.getPrice()),
                            Float.parseFloat(item.getPrice())
                    ))
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
