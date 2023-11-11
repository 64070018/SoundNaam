package com.example.contentmembership.service;

import com.example.contentmembership.POJO.ContentMembership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMembershipRepository extends MongoRepository<ContentMembership, String> {
    @Query(value="{title:'?0'}")
    public ContentMembership findByTitle(String title);
    @Query(value="{artist:'?0'}")
    public ContentMembership findByArtist(String artist);

}
