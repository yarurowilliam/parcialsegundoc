package com.example.parcialmusic.entidades;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("duration")
    @Expose
    public String duration;
    @SerializedName("artist")
    @Expose
    public Artist artist;

    public Track(){}



    /*
    public Track(String toString, String s, String s1, String s2, String s3, String s4, int i, String name, Object o) {
    }*/

    public Track(String nombre){
        this.name = nombre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public static class Artist{
        @SerializedName("name")
        @Expose
        public String name;

        public Artist(){}
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}