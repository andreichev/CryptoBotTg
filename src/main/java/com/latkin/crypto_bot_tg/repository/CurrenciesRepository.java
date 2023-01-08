package com.latkin.crypto_bot_tg.repository;

import com.latkin.crypto_bot_tg.model.CurrencyUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<CurrencyUserData, Long> {}
