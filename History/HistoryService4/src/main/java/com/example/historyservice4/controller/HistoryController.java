package com.example.historyservice4.controller;

import com.example.historyservice4.pojo.HistoryEntity;
import com.example.historyservice4.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("allHistory")
    public ResponseEntity getAllHistory() {
        return new ResponseEntity(historyService.allHistory(), HttpStatus.OK);
    }

    @GetMapping("history/{userId}")
    public ResponseEntity getHistoryById(@PathVariable("userId") int userId) {
        return new ResponseEntity(historyService.historyByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("addHistory")
    public ResponseEntity addHistory(@RequestBody HistoryEntity history) {
        if (historyService.addHistory(history)) {
            return new ResponseEntity("Add successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
