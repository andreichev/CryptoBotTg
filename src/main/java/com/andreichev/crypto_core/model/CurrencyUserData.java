package com.andreichev.crypto_core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Начальная стоимость для пользователя
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyUserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private float price;
    @Column
    private boolean used;
}
