package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Класс отвечает за сохранение нового пользователя в БД.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    /**
     * Метод возвращает страницу регистрации.
     * @return
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Метод сохраняет нового пользователя в БД с загруженным изображением или с defaultImage.
     * @param file
     * @param user
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/registration")
    public String addUser(
            @RequestParam("file") MultipartFile file,
            User user, Map<String, Object> model
    ) throws IOException {

        if (!userService.ifUserExist(user)) {
            model.put("message", "Пользователь с указанным логином уже существует!");
            return "registration";
        }
        if (!file.isEmpty() & !file.getContentType().contains("image")) {
            model.put("message", "Выбран не подходящий файл!");
            return "registration";
        }

        try {
            userService.addUser(user, file);
        }
        catch (NullPointerException e) {
            model.put("message", "Не подходящий формат изображения!");
            return "registration";
        }
        return "redirect:/login";
    }
}
