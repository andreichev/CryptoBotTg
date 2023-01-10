package com.andreichev.crypto_core.repository;

import com.andreichev.crypto_core.model.CurrencyUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<CurrencyUserData, Long> {}
