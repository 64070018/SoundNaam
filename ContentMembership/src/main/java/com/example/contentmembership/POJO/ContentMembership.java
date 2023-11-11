package com.example.contentmembership.POJO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("ContentMember")
public class ContentMembership implements Serializable {
    @Id
    private String _id;
    private String title;
    private String content;
    private String artist;
    private double view;

    public ContentMembership() {
    }

    public ContentMembership(String _id, String title, String content, String artist, double view) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.artist = artist;
        this.view = view;
    }
}