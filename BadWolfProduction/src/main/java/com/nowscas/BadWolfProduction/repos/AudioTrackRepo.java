package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Интерфейс для работы с записями аудиотреков в БД.
 */
public interface AudioTrackRepo extends CrudRepository<AudioTrack, Integer> {

    /**
     * Метод возвращает песни из БД по имени исполнителя.
     * @param author
     * @return
     */
    List<AudioTrack> findByTrackSinger(String author);
}
