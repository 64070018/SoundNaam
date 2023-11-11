package com.example.contentmembership.controller;

import com.example.contentmembership.POJO.ContentMembership;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ContentMembershipController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ContentMembershipController() {
    }

    @RequestMapping(value = "/getContent", method = GET)
    public List<ContentMembership> getAllContent(){
        return (List<ContentMembership>) rabbitTemplate.convertSendAndReceive("ContentMemExchange", "getAll", "");
    }
    @RequestMapping(value = "/addContent", method = POST)
    public boolean addContent(@RequestBody ContentMembership content){
        return (boolean) rabbitTemplate.convertSendAndReceive("ContentMemExchange", "add", content);
    }

    @RequestMapping(value ="/delContent", method = POST)
    public boolean delContent(@RequestBody ContentMembership content){
        return (boolean) rabbitTemplate.convertSendAndReceive("ContentMemExchange", "delete", content);
    }
    @RequestMapping(value ="/updateContent", method = POST)
    public boolean updateContent(@RequestBody ContentMembership content){
        return (boolean) rabbitTemplate.convertSendAndReceive("ContentMemExchange", "update", content);
    }
}
