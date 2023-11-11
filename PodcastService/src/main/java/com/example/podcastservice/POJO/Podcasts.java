package com.example.podcastservice.POJO;

import lombok.Data;
import java.util.ArrayList;

@Data
//@Document("Song")
public class Podcasts {
    private ArrayList<Podcast> model;

    public ArrayList<Podcast> getModel() {
        return model;
    }

    public void setModel(ArrayList<Podcast> model) {
        this.model = model;
    }
}
