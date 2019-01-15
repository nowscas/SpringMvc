package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для работы с записями пользователей в БД.
 */
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Метод возвращает пользователя из БД по имени.
     * @param username
     * @return
     */
    User findByUsername(String username);
}
