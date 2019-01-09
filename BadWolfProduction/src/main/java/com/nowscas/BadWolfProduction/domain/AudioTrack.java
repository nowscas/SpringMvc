package com.nowscas.BadWolfProduction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AudioTrack {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String trackDescription;
    private String trackSinger;

    public AudioTrack(){
    }

    public AudioTrack(String trackDescription, String trackSinger) {
        this.trackDescription = trackDescription;
        this.trackSinger = trackSinger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackDescription() {
        return trackDescription;
    }

    public void setTrackDescription(String trackDescription) {
        this.trackDescription = trackDescription;
    }

    public String getTrackSinger() {
        return trackSinger;
    }

    public void setTrackSinger(String trackSinger) {
        this.trackSinger = trackSinger;
    }
}
