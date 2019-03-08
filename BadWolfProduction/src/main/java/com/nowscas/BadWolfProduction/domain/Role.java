package com.nowscas.BadWolfProduction.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Список ролей для пользователей.
 * Пока что авторизацию убрали и роль нужна одна - админ.
 */
public enum Role implements GrantedAuthority {
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
