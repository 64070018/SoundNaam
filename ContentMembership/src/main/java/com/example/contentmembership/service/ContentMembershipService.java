package com.example.contentmembership.service;

import com.example.contentmembership.POJO.ContentMembership;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ContentMembershipService {
    @Autowired
    private ContentMembershipRepository repository;

    public ContentMembershipService(ContentMembershipRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "GetContentQueue")
    public List<ContentMembership> getAllContent(){
        try {
            return repository.findAll();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @RabbitListener(queues = "AddContentQueue")
    public boolean addContent(@RequestBody ContentMembership content){
        try {
            repository.insert(content);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @RabbitListener(queues = "DeleteContentQueue")
    public boolean deleteContent(ContentMembership content){
        try {
            repository.delete(content);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @RabbitListener(queues = "UpdateContentQueue")
    public boolean updateContent(ContentMembership content){
        try {
            repository.save(content);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

}
