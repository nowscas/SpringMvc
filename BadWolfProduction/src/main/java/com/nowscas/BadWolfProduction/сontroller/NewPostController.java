package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Класс отвечает за отображение страницы добавления нового поста и сохранения поста в БД.
 */
@Controller
public class NewPostController {
    @Autowired
    private MainPagePostRepo mainPagePostRepo;

    /**
     * Метод возвращает страницу добавления нового поста.
     * @return
     */
    @GetMapping("/addNewPost")
    public String getNewPostTemplate(){
        return "addNewPost";
    }

    /**
     * Метод сохраняет новый пост в БД и возвращает главную страницу.
     * @param user
     * @param description
     * @param text
     * @param model
     * @return
     */
    @PostMapping("/addNewPost")
    public String addPost(
            @AuthenticationPrincipal User user,
            @RequestParam String description,
            @RequestParam String text, Map<String, Object> model
    ){
        MainPagePost mainPagePost = new MainPagePost(description, text, user);
        mainPagePostRepo.save(mainPagePost);

        Iterable<MainPagePost> tracks = mainPagePostRepo.findAll();
        model.put("tracks", tracks);
        return "redirect:/main";
    }
}
