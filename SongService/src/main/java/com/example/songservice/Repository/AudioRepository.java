package com.example.songservice.Repository;

import com.example.songservice.POJO.Music;
import com.example.songservice.POJO.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends MongoRepository<Music, String> {
    @Query(value="{fileName:'?0'}")
    public Song findByName(String fileName);
}
