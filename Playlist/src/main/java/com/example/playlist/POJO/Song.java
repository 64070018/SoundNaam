package com.example.songservice.POJO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Song")
public class Song implements Serializable {
    @Id
    private String _id;
    private String title;
    private String lyrics;
    private String artist;
    private String image;
    private String date;
    private String album;
    private double like;
    private double dislike;
    private double view;

    public Song() {
    }

    public Song(String title, String lyrics, String artist, String image, String date, String album, int like, int dislike, int view) {
        this.title = title;
        this.lyrics = lyrics;
        this.artist = artist;
        this.image = image;
        this.date = date;
        this.album = album;
        this.like = like;
        this.dislike = dislike;
        this.view = view;
    }

    public Song(String _id, String title, String lyrics, String artist, String image, String date, String album, int like, int dislike, int view) {
        this._id = _id;
        this.title = title;
        this.lyrics = lyrics;
        this.artist = artist;
        this.image = image;
        this.date = date;
        this.album = album;
        this.like = like;
        this.dislike = dislike;
        this.view = view;
    }

}
