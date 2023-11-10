package com.example.playlist.service;

import com.example.playlist.POJO.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    @Query(value = "{playlistName: '?0'}")

    public Playlist findByName(String playlistName);
}
