package com.example.Artist.service;

import com.example.Artist.POJO.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    @Query(value = "{name: '?0'}")

    public Artist findByName(String name);
}
