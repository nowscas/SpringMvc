package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.Stock;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import com.nowscas.BadWolfProduction.repos.StockRepo;
import com.nowscas.BadWolfProduction.service.IterableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Класс отвечает за отображение главной страницы.
 */
@Controller
public class MainController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;
    @Autowired
    private MainPagePostRepo mainPagePostRepo;
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private IterableService iterableService;

    /**
     * Метод возвращает главную страницу приложения.
     * @param model
     * @return
     */
    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        Iterable<MainPagePost> posts = mainPagePostRepo.findAll();
        Iterable<Stock> stocks = stockRepo.findAll();

        model.put("tracks", iterableService.revertTracks((List)tracks));
        model.put("posts", iterableService.revertPosts((List)posts));
        model.put("stocks", iterableService.revertStocks((List)stocks));
        return "main";
    }

    /**
     * Метод возврадает страницу с контактами.
     */
    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }
}