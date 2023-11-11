package com.example.songservice.View;

import com.example.songservice.POJO.Music;
import com.example.songservice.POJO.Song;
import com.example.songservice.Repository.AudioRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Route("createSong")
public class CreateSong extends VerticalLayout {
    private AudioRepository audioRepository;
    private String audioId;
    private Text audioFile, coverFile;
    private TextField titile, description, lyrics, album;
    private Button submit;
    private Upload uploadAudio, uploadCover;

    @Autowired
    public CreateSong(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
        titile = new TextField("Title");
        description = new TextField("Description");
        lyrics = new TextField("Lyrics");
        album = new TextField("Album");
        submit = new Button("Upload New Song");
        audioFile = new Text("Audio");
        coverFile = new Text("Cover Image");


        // Set up the Upload component with FileBuffer as the receiver
        uploadAudio = new Upload(new FileBuffer());
        uploadAudio.setAcceptedFileTypes("audio/*");
        uploadAudio.addSucceededListener(event -> {
            InputStream inputStream = ((FileBuffer) uploadAudio.getReceiver()).getInputStream();
            saveAudioToMongoDB(event.getFileName(), event.getMIMEType(), inputStream);
        });

        uploadCover = new Upload(new FileBuffer());
        uploadCover.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
        uploadCover.addSucceededListener(event -> {
            InputStream inputStream = ((FileBuffer) uploadCover.getReceiver()).getInputStream();
            saveImageToMongoDB(event.getFileName(), event.getMIMEType(), inputStream);
        });


        submit.addClickListener(event -> {
            System.out.println("submit");
            Song data = new Song(titile.getValue(), lyrics.getValue(), "getUserArtist", audioId, "https://pbs.twimg.com/media/F-fuiIZbgAEgCV-?format=jpg", String.valueOf(new Date()), album.getValue());


            Boolean output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addSong")
                    .bodyValue(data)
                    .retrieve().bodyToMono(Boolean.class).block();

//            new Notification(output, 10000).open();
        });









        add(titile, description, lyrics, album, audioFile, uploadAudio, coverFile, uploadCover, submit);

    }

    private void saveImageToMongoDB(String fileName, String mimeType, InputStream inputStream){


    }
    private String saveAudioToMongoDB(String fileName, String mimeType, InputStream inputStream) {
        byte[] data;
        try {
            data = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        Music audio = new Music();
        // Set other metadata fields
        audio.setData(data);
        audio.setFileName(fileName);
        audio.setMimeType(mimeType);

        audioRepository.save(audio);
        System.out.println(audio.getId());
        this.audioId = audio.getId();

        return audio.getId();
    }
}
