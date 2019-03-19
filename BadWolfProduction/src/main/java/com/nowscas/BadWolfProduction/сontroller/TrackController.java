package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Класс отвечает за работу с аудиотреками.
 */
@Controller
@RequestMapping("/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;

    /**
     * Метод получает записи треков и возвращает страницу со всеми треками.
     * @param model
     * @return
     */
    @GetMapping("/allTracks")
    public String getAllTracks(Model model) {
        model.addAttribute("tracks", trackService.getAudioTracks());
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
        model.addAttribute("tracks", trackService.getTracksBySinger(singer));
        return "allTracks";
    }

    /**
     * Метод возвращающий страницу добавления трека.
     * @return
     */
    @GetMapping("/addNewTrack")
    public String getNewTrackTemplate(){
        return "addNewTrack";
    }

    /**
     * Метод дает команду на сохранение трека в БД и возвращает главную страницу.
     * @param file
     * @param description
     * @param singer
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/addNewTrack")
    public String addTrack(
            @RequestParam("file") MultipartFile file,
            @RequestParam String description,
            @RequestParam String singer, Map<String, Object> model
    ) throws IOException {
        if (trackService.addAudioTrack(file, description, singer)) {
            return "redirect:/";
        } else {
            model.put("message", "Выбран не подходящий файл!");
            return "addNewTrack";
        }
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

    /**
     * Метод дает команду на сохранение отредактированного трека и возвращает главную страницу.
     * @param trackDescription
     * @param trackSinger
     * @param form
     * @param audioTrack
     * @return
     */
    @PostMapping
    public String trackSave(
            @RequestParam String trackDescription,
            @RequestParam String trackSinger,
            @RequestParam Map<String, String> form,
            @RequestParam("id") AudioTrack audioTrack
    ) {

        trackService.saveChanged(trackDescription, trackSinger, form, audioTrack);
        return "redirect:/tracks/allTracks";
    }

    /**
     * Метод дает команду на удаление переданного трека и возвращает страницу со всеми треками.
     * @param audioTrack
     * @param model
     * @return
     */
    @GetMapping("/deleteTrack/{audioTrack}")
    public String deleteTrack(
            @PathVariable AudioTrack audioTrack,
            Map<String, Object> model
    ) {
        trackService.deleteTrack(audioTrack);
        return "redirect:/tracks/allTracks";
    }
}
