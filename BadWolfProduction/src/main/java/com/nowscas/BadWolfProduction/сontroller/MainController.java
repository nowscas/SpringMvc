package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Класс отвечает за отображение главной страницы.
 */
@Controller
public class MainController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;
    @Autowired
    MainPagePostRepo mainPagePostRepo;

    /**
     * Метод возвращает главную страницу приложения.
     * @param model
     * @return
     */
    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        Iterable<MainPagePost> posts = mainPagePostRepo.findAll();

        model.put("tracks", tracks);
        model.put("posts", posts);
        return "main";
    }

    /**
     * Метод возврадает страницу с вопросами и ответами.
     */
    @GetMapping("/FAQ")
    public String getFAQ() {
        return "FAQ";
    }

    /**
     * Метод возврадает страницу с ценами.
     */
    @GetMapping("/prices")
    public String getPrices() {
        return "prices";
    }

    /**
     * Метод возврадает страницу с контактами.
     */
    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }
}