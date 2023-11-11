package com.example.soundnaam.POJO;

import lombok.Data;

import java.util.ArrayList;

@Data
//@Document("Song")
public class Songs {
    private ArrayList<Song> model;

    public ArrayList<Song> getModel() {
        return model;
    }

    public void setModel(ArrayList<Song> model) {
        this.model = model;
    }
}
