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
 * Класс возвращает данные пользователя.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ImageRedactor imageRedactor;
    @Autowired
    private StringRedactor fileNameRedactor;

    @Value("${upload.imagePath}")
    private String uploadPath;

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
     * Метод
     */
    public void addUser(User user, MultipartFile file) throws IOException {
        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {

            String filename = fileNameRedactor.replaceChar(file.getOriginalFilename(), " ", "_");
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + filename;

            File output = new File(uploadPath +  "/" + resultFilename);
            ImageIO.write(imageRedactor.resizeImage(file.getBytes(), 40, 40), "png", output);

            user.setFilename(resultFilename);
        }
        else {
            user.setFilename("defaultImage.jpg");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }
}
