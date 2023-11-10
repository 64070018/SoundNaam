package com.example.songservice.Repository;

import com.example.songservice.POJO.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {

    @Query(value="{title:'?0'}")
    public Song findByName(String title);
}
