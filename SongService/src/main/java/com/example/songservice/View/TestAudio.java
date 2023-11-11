package com.example.songservice.View;

import com.example.songservice.POJO.Music;
import com.example.songservice.Repository.AudioRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.compress.utils.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

@Route("/audio")
public class TestAudio extends VerticalLayout {

    private AudioRepository audioRepository;

    @Autowired
    public TestAudio(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;


        // Set up the Upload component with FileBuffer as the receiver
        Upload upload = new Upload(new FileBuffer());
        upload.setAcceptedFileTypes("audio/*");
        upload.addSucceededListener(event -> {
            // Get the input stream from FileBuffer
            InputStream inputStream = ((FileBuffer) upload.getReceiver()).getInputStream();
            // Save the audio file to MongoDB
            saveAudioToMongoDB(event.getFileName(), event.getMIMEType(), inputStream);
        });


        // Assume base64Data is the Base64 data retrieved from MongoDB
//        String base64Data = "your_base64_data_here";
//
//        // Decode Base64 to binary
//        byte[] binaryData = Base64.getDecoder().decode(base64Data);
//
//        // Create a stream resource from the binary data
//        StreamResource resource = new StreamResource("audio.mp3", (InputStreamFactory) () ->
//                new ByteArrayInputStream(binaryData));

        // Create an Audio component and set the source
//        Audio audio = new Audio(resource);
//        add(upload);
//        add(upload, audio);
    }


    private void saveAudioToMongoDB(String fileName, String mimeType, InputStream inputStream) {
        byte[] data;
        try {
            data = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Create an Audio object and save it to MongoDB using the repository
        Music audio = new Music();
        // Set other metadata fields
        audio.setData(data);
        audioRepository.save(audio);
    }
}
