package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        model.put("tracks", tracks);
        return "main";
    }

    @GetMapping("/addNewTrack")
    public String addNewTrack(){
        return "addNewTrack";
    }

    @PostMapping("/addNewTrack")
    public String addTrack(@RequestParam String description, @RequestParam String singer, Map<String, Object> model){
        AudioTrack audioTrack = new AudioTrack(description, singer);

        audioTrackRepo.save(audioTrack);

        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        model.put("tracks", tracks);
        return "redirect:/main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<AudioTrack> audioTracks;

        if (filter != null && !filter.isEmpty()) {
            audioTracks = audioTrackRepo.findByTrackSinger(filter);
        }
        else {
            audioTracks = audioTrackRepo.findAll();
        }
        model.put("tracks", audioTracks);
        return "main";
    }
}