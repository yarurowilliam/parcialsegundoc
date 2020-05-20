package com.example.parcialmusic.entidades.other;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trackmatches {

    @SerializedName("track")
    @Expose
    private List<TrackString> track = null;

    public List<TrackString> getTrack() {
        return track;
    }

    public void setTrack(List<TrackString> track) {
        this.track = track;
    }

}