package com.example.songservice.Controller;

import com.example.songservice.POJO.Audio;
import com.example.songservice.POJO.Song;
import com.example.songservice.POJO.Songs;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class SongController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Song model = new Song( "asdf", "asdf", "asdf", "asdf", "asdf", "asdf", 12, 12, 12);
    private Song modelUpdate = new Song("updated", "susccess", "asdf", "asdf", "asdf", "asdf", 12, 12, 12);
    private Songs models;
    public SongController() {
    }

//    @RequestMapping(value = "/", method = GET)
//    public String test(){
//        return "pass";
//    }





    @RequestMapping(value = "/getSong", method = GET)
    public List<Song> getAllSong(){
//        String songs = (String) rabbitTemplate.convertSendAndReceive("SongExchange", "getAll", "");
        System.out.println("songs1");
//        System.out.println(songs);
        System.out.println("songs2");
        return (List<Song>) rabbitTemplate.convertSendAndReceive("SongExchange", "getAll", "");
    }
    @RequestMapping(value ="/delSong", method = POST)
    public boolean delSong(@RequestBody Song song){
        return (boolean) rabbitTemplate.convertSendAndReceive("SongExchange", "delete", song);
    }

    @RequestMapping(value = "/updateSong", method = POST)
//    public boolean updateSong(@RequestBody Song song){
    public boolean updateSong(){
//        validation id if not have id will return false
//        cuz use save so if no have id will generate new data
        return (boolean) rabbitTemplate.convertSendAndReceive("SongExchange", "update",  modelUpdate);
    }

    @RequestMapping(value = "/addSong", method = POST)
//    public boolean addSong(@RequestBody Song song){
    public boolean addSong(){
        return (boolean) rabbitTemplate.convertSendAndReceive("SongExchange", "add", model);
    }
}
