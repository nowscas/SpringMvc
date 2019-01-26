package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import com.nowscas.BadWolfProduction.service.StringRedactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Класс отвечает за работу с аудиотреками.
 */
@Controller
@RequestMapping("/tracks")
public class TrackController {
    @Autowired
    private AudioTrackRepo audioTrackRepo;
    @Autowired
    private StringRedactor fileNameRedactor;

    @Value("${upload.musicPath}")
    private String uploadPath;

    /**
     * Метод возвращающий страницу добавления трека.
     * @return
     */
    @GetMapping("/addNewTrack")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getNewTrackTemplate(){
        return "addNewTrack";
    }

    /**
     * Метод сохраняет новый трек в БД и возвращает главную страницу.
     * @param file
     * @param description
     * @param singer
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/addNewTrack")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String addTrack(
            @RequestParam("file") MultipartFile file,
            @RequestParam String description,
            @RequestParam String singer, Map<String, Object> model
    ) throws IOException {
        AudioTrack audioTrack = new AudioTrack(description, singer, true);

        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("audio")) {
                model.put("message", "Выбран не подходящий файл!");
                return "addNewTrack";
            }

            String filename = fileNameRedactor.replaceChar(file.getOriginalFilename(), " ", "_");
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + filename;

            file.transferTo(new File(uploadPath +  "/" + resultFilename));
            audioTrack.setFilename(resultFilename);
            audioTrackRepo.save(audioTrack);
            return "redirect:/";
        }
        else {
            model.put("message", "Укажите загружаемый файл!");
            return "addNewTrack";
        }
    }

    /**
     * Метод сохраняет трек после изменения.
     * @param trackDescription
     * @param trackSinger
     * @param form
     * @param audioTrack
     * @param filename
     * @return
     */
    @PostMapping
    public String trackSave(
            @RequestParam String trackDescription,
            @RequestParam String trackSinger,
            @RequestParam Map<String, String> form,
            @RequestParam("id") AudioTrack audioTrack,
            @RequestParam("filename") String filename
    ) {
        audioTrack.setTrackDescription(trackDescription);
        audioTrack.setTrackSinger(trackSinger);
        audioTrack.setFilename(filename);

        if (form.keySet().contains("isNew")){
            audioTrack.setNewTrack(true);
        }
        else {
            audioTrack.setNewTrack(false);
        }

        audioTrackRepo.save(audioTrack);
        return "redirect:/tracks/allTracks";
    }

    /**
     * Метод удаляет трек.
     * @param audioTrack
     * @param model
     * @return
     */
    @GetMapping("/deleteTrack/{audioTrack}")
    public String deleteTrack(
            @PathVariable AudioTrack audioTrack,
            Map<String, Object> model
    ) {
        File file = new File(uploadPath + "/" + audioTrack.getFilename());
        if (file.delete()) {
            audioTrackRepo.delete(audioTrack);
            return "redirect:/tracks/allTracks";
        }
        else {
            model.put("message", "Удаляемый файл не найден");
            return "redirect:/tracks/allTracks";
        }
    }

    /**
     * Метод возвращает все треки.
     * @param model
     * @return
     */
    @GetMapping("/allTracks")
    public String getAllTracks(Model model) {
        Iterable<AudioTrack> tracks;
        tracks = audioTrackRepo.findAll();
        model.addAttribute("tracks", tracks);
        return "allTracks";
    }

    /**
     * Метод возвращает все треки переданного исполнителя.
     * @param singer
     * @param model
     * @return
     */
    @GetMapping("{singer}")
    public String getSingerTracks(@PathVariable String singer, Model model
    ) {
        Iterable<AudioTrack> tracks;
        if (singer != null && !singer.isEmpty()) {
            tracks = audioTrackRepo.findByTrackSinger(singer);
        }
        else {
            tracks = audioTrackRepo.findAll();
        }
        model.addAttribute("tracks", tracks);
        return "allTracks";
    }

    /**
     * Метод возвращает страницу редактирования указанного трека.
     * @param audioTrack
     * @param model
     * @return
     */
    @GetMapping("/edit/{audioTrack}")
    public String getTrackEditPage(@PathVariable AudioTrack audioTrack, Model model) {
        model.addAttribute("track", audioTrack);
        return "trackEdit";
    }
}
