package com.example.historyservice4.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "History")
public class HistoryEntity {
    @Id
    private String _id;
    private int historyId;
    private int userId;
    private int songId;
    private String timestamp;

    public HistoryEntity(){}

    public HistoryEntity(String _id){
        this._id = _id;
    }

    public HistoryEntity(String _id, int historyId, int userId, int songId, String timestamp){
        this._id = _id;
        this.historyId = historyId;
        this.userId = userId;
        this.songId = songId;
        this.timestamp = timestamp;
    }

    public HistoryEntity(int historyId, int userId, int songId, String timestamp){
        this.historyId = historyId;
        this.userId = userId;
        this.songId = songId;
        this.timestamp = timestamp;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
