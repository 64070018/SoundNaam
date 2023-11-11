package com.example.podcastservice.View;

import com.example.podcastservice.POJO.Music;
import com.example.podcastservice.POJO.Podcast;
import com.example.podcastservice.Repository.AudioRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    private TextField title, description, series;
    private Button submit;
    private Upload uploadAudio, uploadCover;

    @Autowired
    public CreatePodcast(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
        title = new TextField("Title");
        description = new TextField("Description");

        series = new TextField("Series");
        submit = new Button("Upload New Podcast");
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
            Podcast data = new Podcast(title.getValue(), description.getValue(), "getUserArtist", audioId, "https://fiu-original.b-cdn.net/fontsinuse.com/use-images/N178/178385/178385.jpeg?filename=newjeans.jpeg", String.valueOf(new Date()), series.getValue());


            Boolean output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addPodcast")
                    .bodyValue(data)
                    .retrieve().bodyToMono(Boolean.class).block();

//            new Notification(output, 10000).open();
        });









        add(title, description, series, audioFile, uploadAudio, coverFile, uploadCover, submit);

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
