package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.Role;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс редактирует список пользователей.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    //По скольку авторизации нет, то решено убрать страницу со всеми пользователями
//    /**
//     * Метод возвращает страницу со всеми пользователями.
//     * @param model
//     * @return
//     */
//    @GetMapping
//    public String userList(Model model) {
//        model.addAttribute("users", userRepo.findAll());
//        return "userList";
//    }

//    /**
//     * Метод возвращает страницу редактирования указанного пользователя.
//     * @param user
//     * @param model
//     * @return
//     */
//    @GetMapping("{user}")
//    public String userEditForm(@PathVariable User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Role.values());
//        return "userEdit";
//    }

//    /**
//     * Метод сохраняет изменения данных пользователя.
//     * @param username
//     * @param form
//     * @param user
//     * @return
//     */
//    @PostMapping
//    public String userSave(
//            @RequestParam String username,
//            @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user
//    ) {
//        user.setUsername(username);
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
//
//        user.getRoles().clear();
//
//        for (String key : form.keySet()) {
//            if (roles.contains(key)) {
//                user.getRoles().add(Role.valueOf(key));
//            }
//        }
//        userRepo.save(user);
//        return "redirect:/user";
//    }
}
