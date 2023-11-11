package com.example.podcastservice.View;

import com.example.podcastservice.POJO.Music;
import com.example.podcastservice.POJO.Podcast;
import com.example.podcastservice.Repository.AudioRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Route("createPodcast")
public class CreatePodcast extends VerticalLayout {
    private AudioRepository audioRepository;
    private String audioId;
    private Text audioFile, coverFile;
    private TextField title, series;
    private TextArea description;
    private Button submit;
    private Upload uploadAudio, uploadCover;
    private H1 textmain;

    @Autowired
    public CreatePodcast(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;

        textmain = new H1("Upload Podcast");
        title = new TextField("Title");
        title.setWidth("30%");
        title.getStyle().set("--vaadin-input-field-border-width", "1px");

        description = new TextArea("Description");
        description.setWidth("30%");
        description.getStyle().set("--vaadin-input-field-border-width", "1px");

        series = new TextField("Series");
        series.setWidth("30%");
        series.getStyle().set("--vaadin-input-field-border-width", "1px");

        submit = new Button("Upload New Podcast");
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
            Podcast data = new Podcast(title.getValue(), description.getValue(), "getUserArtist", audioId, "https://fiu-original.b-cdn.net/fontsinuse.com/use-images/N178/178385/178385.jpeg?filename=newjeans.jpeg", String.valueOf(new Date()), series.getValue());


            Boolean output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addPodcast")
                    .bodyValue(data)
                    .retrieve().bodyToMono(Boolean.class).block();

//            new Notification(output, 10000).open();
        });









        add(title, description, series, audioFile, uploadAudio, coverFile, uploadCover, submit);
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
