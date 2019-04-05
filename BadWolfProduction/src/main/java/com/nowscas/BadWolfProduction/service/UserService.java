package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.Role;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

/**
 * Класс для работы с данными пользователя.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ImageRedactor imageRedactor;
    @Autowired
    private StringRedactor fileNameRedactor;

    /**
     * Метод возвращает пользователя из БД по имени.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    /**
     * Метод проверяет наличие позьзователя по имени.
     * @param user
     * @return
     */
    public boolean ifUserExist(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }
        return true;
    }

    /**
     * Метод записи пользователя в БД.
     */
    public void addUser(User user, MultipartFile file) throws IOException {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
    }
}
