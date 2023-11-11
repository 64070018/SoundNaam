package com.example.podcastservice.Repository;

import com.example.podcastservice.POJO.Music;
import com.example.podcastservice.POJO.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends MongoRepository<Music, String> {
    @Query(value="{fileName:'?0'}")
    public Podcast findByName(String fileName);
}
