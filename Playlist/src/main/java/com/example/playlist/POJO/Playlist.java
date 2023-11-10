package com.example.playlist.POJO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.songservice.POJO.Song;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Data
@Document("Playlist") //ชื่อเดียวกับ Collection ใน MongoDB
public class Playlist implements Serializable {
    @Id
    private String _id;
    private String userId;
    private String playlistName;
    private String cover;
    private String status;
    private Date date;
    private List<Song> song;

    public Playlist(){}

    public Playlist(String _id, String userId, String playlistName, String cover, String status, Date date, List<Song> song){
        this._id = _id;
        this.userId = userId;
        this.playlistName = playlistName;
        this.cover = cover;
        this.status = status;
        this.date =date;
        this.song =song;
    }


    //    public class Song{
//        public String id;
//        public String name;
//        public String artist;
//        public String time;
//        public String coverSong;
//
//        public void Song(String id, String name, String artist, String time, String coverSong){
//            this.id = id;
//            this.name = name;
//            this.artist = artist;
//            this.time = time;
//            this.coverSong = coverSong;
//        }
//    }
//    public List<Playlist.Song> getSong() {
//        return this.song;
//    }
}
