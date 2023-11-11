package com.example.songservice.View;

import com.example.songservice.POJO.Music;
import com.example.songservice.POJO.Song;
import com.example.songservice.Repository.AudioRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
    private TextField titile, album;
    private TextArea description, lyrics;
    private Button submit;
    private Upload uploadAudio, uploadCover;
    private H1 textmain;

    @Autowired
    public CreateSong(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;

        textmain = new H1("Upload Audio");
        titile = new TextField("Title");
        titile.setWidth("30%");
        titile.getStyle().set("--vaadin-input-field-border-width", "1px");

        description = new TextArea("Description");
        description.setWidth("30%");
        description.getStyle().set("--vaadin-input-field-border-width", "1px");

        lyrics = new TextArea("Lyrics");
        lyrics.setWidth("30%");
        lyrics.getStyle().set("--vaadin-input-field-border-width", "1px");

        album = new TextField("Album");
        album.setWidth("30%");
        album.getStyle().set("--vaadin-input-field-border-width", "1px");

        submit = new Button("Upload New Song");
        submit.getStyle().set("background-color", "#FFA62B");

        audioFile = new Text("Audio");
        coverFile = new Text("Cover Image");


        // Set up the Upload component with FileBuffer as the receiver
        uploadAudio = new Upload(new FileBuffer());
        uploadAudio.setAcceptedFileTypes("audio/*");
        uploadAudio.addSucceededListener(event -> {
            InputStream inputStream = ((FileBuffer) uploadAudio.getReceiver()).getInputStream();
            saveAudioToMongoDB(event.getFileName(), event.getMIMEType(), inputStream);
        });
        uploadAudio.setWidth("30%");

        uploadCover = new Upload(new FileBuffer());
        uploadCover.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
        uploadCover.addSucceededListener(event -> {
            InputStream inputStream = ((FileBuffer) uploadCover.getReceiver()).getInputStream();
            saveImageToMongoDB(event.getFileName(), event.getMIMEType(), inputStream);
        });
        uploadCover.setWidth("30%");

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









        add(textmain, titile, description, lyrics, album, audioFile, uploadAudio, coverFile, uploadCover, submit);
        this.setAlignItems(Alignment.CENTER);

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
