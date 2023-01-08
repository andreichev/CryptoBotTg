package com.latkin.crypto_bot_tg.repository;

import com.latkin.crypto_bot_tg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {}
