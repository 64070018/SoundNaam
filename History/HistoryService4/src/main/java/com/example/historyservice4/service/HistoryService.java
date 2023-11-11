package com.example.historyservice4.service;

import com.example.historyservice4.pojo.HistoryEntity;
import com.example.historyservice4.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<HistoryEntity> allHistory() {
        return historyRepository.findAll();
    }

    public boolean addHistory(HistoryEntity historyEntity){
        try {
            historyRepository.save(historyEntity);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<HistoryEntity> historyByUserId(int userId){
        try {
            return historyRepository.findByUserId(userId);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }
}
