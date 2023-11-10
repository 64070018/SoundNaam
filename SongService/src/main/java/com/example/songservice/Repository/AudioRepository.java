package com.example.songservice.Repository;

import com.example.songservice.POJO.Audio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends MongoRepository<Audio, String> {
}
