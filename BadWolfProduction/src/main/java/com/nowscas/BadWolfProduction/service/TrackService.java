package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.repos.AudioTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TrackService {
    @Autowired
    private AudioTrackRepo audioTrackRepo;
    @Autowired
    private StringRedactor fileNameRedactor;
    @Autowired
    private IterableService iterableService;

    @Value("${upload.musicPath}")
    private String uploadPath;

    /**
     * Метод возвращает все аудиозаписи.
     * @return
     */
    public Iterable<AudioTrack> getAudioTracks() {
        Iterable<AudioTrack> tracks = audioTrackRepo.findAll();
        return iterableService.revertList((List)tracks);
    }

    public Iterable<AudioTrack> getTracksBySinger(String singer) {
        Iterable<AudioTrack> tracks;
        if (singer != null && !singer.isEmpty()) {
            tracks = audioTrackRepo.findByTrackSinger(singer);
        }
        else {
            tracks = audioTrackRepo.findAll();
        }
        return iterableService.revertList((List)tracks);
    }

    /**
     * Метод сохнаряет новую запись трека.
     * @param file
     * @param description
     * @param singer
     * @return
     * @throws IOException
     */
    public boolean addAudioTrack(MultipartFile file, String description, String singer) throws IOException {
        AudioTrack audioTrack = new AudioTrack(description, singer, true);

        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("audio")) {
                return false;
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
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Метод сохраняет изменения в записи трека.
     * @param trackDescription
     * @param trackSinger
     * @param form
     * @param audioTrack
     */
    public void saveChanged(String trackDescription, String trackSinger, Map<String, String> form, AudioTrack audioTrack) {
        audioTrack.setTrackDescription(trackDescription);
        audioTrack.setTrackSinger(trackSinger);
        if (form.keySet().contains("isNew")){
            audioTrack.setNewTrack(true);
        }
        else {
            audioTrack.setNewTrack(false);
        }
        audioTrackRepo.save(audioTrack);
    }

    /**
     * Метод удаляет трек и файл(если он был).
     * @param audioTrack
     */
    public void deleteTrack(AudioTrack audioTrack) {
        File file = new File(uploadPath + "/" + audioTrack.getFilename());
        audioTrackRepo.delete(audioTrack);
        file.delete();
    }
}
