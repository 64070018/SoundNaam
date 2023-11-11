package com.example.podcastservice.POJO;

import com.vaadin.flow.server.StreamResource;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "audio")
public class Audio {
    @Id
    private String id;
    private byte[] data;

    public Audio() {
    }

    public Audio(String id) {
        this.id = id;
    }

    public Audio(String id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public Audio(StreamResource resource) {
    }
}
