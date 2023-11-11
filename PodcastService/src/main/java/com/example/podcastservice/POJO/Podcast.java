package com.example.podcastservice.POJO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Podcast")
public class Podcast implements Serializable {
    @Id
    private String _id;
    private String title;
    private String Description;
    private String artist;
    private String dataAudio;
    private String image;
    private String date;
    private String series;
    private double like;
    private double dislike;
    private double view;

    public Podcast() {
    }

    public Podcast(String title, String Description, String artist, String dataAudio, String image, String date, String series) {
        this.title = title;
        this.Description = Description;
        this.artist = artist;
        this.dataAudio = dataAudio;
        this.image = image;
        this.date = date;
        this.series = series;
    }

    public Podcast(String _id, String title, String Description, String artist, String dataAudio, String image, String date, String series, double like, double dislike, double view) {
        this._id = _id;
        this.title = title;
        this.Description = Description;
        this.artist = artist;
        this.dataAudio = dataAudio;
        this.image = image;
        this.date = date;
        this.series = series;
        this.like = like;
        this.dislike = dislike;
        this.view = view;
    }
}
