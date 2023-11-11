package com.example.soundnaam.controller;


import com.example.soundnaam.POJO.Playlist;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaylistController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/getAllPlaylist", method = RequestMethod.GET)
    public List<Playlist> getAllPlaylist(){
        return (List<Playlist>) rabbitTemplate.convertSendAndReceive("PlaylistExchange", "getall", "");
    }

    @RequestMapping(value = "/getNamePlaylist/{name}", method = RequestMethod.GET)
    public Playlist getNamePlaylist(@PathVariable String name){
        return (Playlist) rabbitTemplate.convertSendAndReceive("PlaylistExchange", "name", name);
    }

    @RequestMapping(value = "/addPlaylist", method = RequestMethod.POST)
    public boolean addProduct(@RequestBody Playlist playlist){
        return (boolean) rabbitTemplate.convertSendAndReceive("PlaylistExchange", "add", playlist);
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public boolean updateProduct(@RequestBody Playlist playlist){
        return (boolean) rabbitTemplate.convertSendAndReceive("PlaylistExchange", "update", playlist);
    }
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public boolean deleteProduct(@RequestBody Playlist playlist){
        return (boolean) rabbitTemplate.convertSendAndReceive("PlaylistExchange", "delete", playlist);
    }
}
