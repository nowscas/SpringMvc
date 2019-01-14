package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class NewTrackController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;

    @Value("${upload.musicPath}")
    private String uploadPath;

    @GetMapping("/addNewTrack")
    public String getNewTrackTemplate(){
        return "addNewTrack";
    }

    @PostMapping("/addNewTrack")
    public String addTrack(
            @RequestParam("file") MultipartFile file,
            @RequestParam String description,
            @RequestParam String singer, Map<String, Object> model) throws IOException {
        AudioTrack audioTrack = new AudioTrack(description, singer, true);

        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {

            if (!file.getContentType().contains("audio")) {
                model.put("message", "Выбран не подходящий файл!");
                return "addNewTrack";
            }

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath +  "/" + resultFilename));
            audioTrack.setFilename(resultFilename);

            audioTrackRepo.save(audioTrack);

            Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
            model.put("tracks", tracks);
            return "redirect:/main";

        }
        else {
            model.put("message", "Укажите загружаемый файл!");
            return "addNewTrack";
        }
    }

}
