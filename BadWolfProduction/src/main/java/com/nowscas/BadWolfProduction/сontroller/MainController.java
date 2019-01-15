package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;
    @Autowired
    MainPagePostRepo mainPagePostRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
        Iterable<AudioTrack> tracks;
        Iterable<MainPagePost> posts = mainPagePostRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            tracks = audioTrackRepo.findByTrackSinger(filter);
        }
        else {
            tracks = audioTrackRepo.findAll();
        }

        model.addAttribute("tracks", tracks);
        model.addAttribute("filter", filter);
        model.addAttribute("posts", posts);
        return "main";
    }
}