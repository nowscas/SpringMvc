package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс возвращает данные пользователя.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    /**
     * Метод возвращает пользователя из БД по имени текущего пользователя.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
