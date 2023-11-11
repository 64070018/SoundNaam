package com.example.historyservice4.repository;

import com.example.historyservice4.pojo.HistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, String> {
    List<HistoryEntity> findByUserId(int userId);
}
