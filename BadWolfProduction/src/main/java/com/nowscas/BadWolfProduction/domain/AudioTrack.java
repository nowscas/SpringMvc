package com.nowscas.BadWolfProduction.domain;

import javax.persistence.*;

@Entity
public class AudioTrack {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String trackDescription;
    private String trackSinger;
    private boolean newTrack;
    private String filename;

    public AudioTrack(){
    }

    public AudioTrack(String trackDescription, String trackSinger, boolean newTrack) {
        this.trackDescription = trackDescription;
        this.trackSinger = trackSinger;
        this.newTrack = newTrack;
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

    public boolean isNewTrack() {
        return newTrack;
    }

    public void setNewTrack(boolean newTrack) {
        this.newTrack = newTrack;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
