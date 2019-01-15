package com.nowscas.BadWolfProduction.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Список ролей для пользователей.
 */
public enum Role implements GrantedAuthority {
    USER, MODERATOR, ADMIN, ;

    @Override
    public String getAuthority() {
        return name();
    }
}
