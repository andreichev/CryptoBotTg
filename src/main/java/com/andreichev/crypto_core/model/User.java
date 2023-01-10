package com.andreichev.crypto_core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long chatId;
    @Column
    private String name;
    @Column
    private Instant startTime;
    // Настройка пользователя - процент, выше которого валюты должны удовлетворять условию
    @Column
    private int n;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CurrencyUserData> currencyUserData;
}
