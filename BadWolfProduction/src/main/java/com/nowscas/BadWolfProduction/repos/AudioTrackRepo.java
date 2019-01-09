package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AudioTrackRepo extends CrudRepository<AudioTrack, Integer> {

    List<AudioTrack> findByTrackSinger(String author);
}
