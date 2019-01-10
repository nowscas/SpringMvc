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
public class NewTrackController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;

    @GetMapping("/addNewTrack")
    public String getNewTrackTemplate(){
        return "addNewTrack";
    }

    @PostMapping("/addNewTrack")
    public String addTrack(@RequestParam String description, @RequestParam String singer, Map<String, Object> model){
        AudioTrack audioTrack = new AudioTrack(description, singer, true);

        audioTrackRepo.save(audioTrack);

        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        model.put("tracks", tracks);
        return "redirect:/main";
    }

}
