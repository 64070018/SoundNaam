package com.example.songservice.View;


import com.example.songservice.POJO.Music;
import com.example.songservice.POJO.Song;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Route("/song")
public class SongPlayView extends VerticalLayout {
//    attribute
    private List<Song> songs;
    private Music music;
//    private List<Comment> comments;

    private byte[] dataMusic;




//    ui component
    private TextField search;
    private Button ad, update, delete, clear, playPauseButton;
    Icon lumoIcon = LumoIcon.SEARCH.create();

    Icon heartIcon;
    TabSheet tabSheet;
    VerticalLayout leftColumn, rightColumn;
    HorizontalLayout h1, h2, listSong;
    Image cover;
    Div upNext = new Div();
    Div lyrics = new Div();
    Div comment = new Div();
    Div player = new Div();



    private AdvancedPlayer playerAudio;


    private Button playButton;
    private Button stopButton;
    private boolean isPlaying = false;
//    private Button pauseButton;
//    private boolean isPaused = false;
//    private int pausedFrame;


    public SongPlayView() {
//        data
        songs = new ArrayList<>();




//        set layout
        h1 = new HorizontalLayout();
        h2 = new HorizontalLayout();
        playButton = new Button("play");
        stopButton = new Button("sop");
//        h1.setWidth("100%");

        leftColumn = new VerticalLayout();
        leftColumn.setWidth("250px");
        leftColumn.add(playButton, stopButton, new Button("Button 1"));

        rightColumn = new VerticalLayout();
//        rightColumn.setWidth("80%");

//        footer
        Footer footer = new Footer(player);
        footer.getStyle().set("position", "absolute");
        footer.getStyle().set("bottom", "0");
        footer.getStyle().set("left", "0");
        footer.getStyle().set("background-color", "#ccc");
//        footer.getStyle().set("padding-vertical", "100px"); // Use your preferred color code
        footer.getStyle().set("padding-top", "10px");
        footer.getStyle().set("padding-bottom", "10px");
        footer.setHeight("80px");
        footer.setWidthFull();



        search = new TextField();
        search.setPlaceholder("search");
        search.setSuffixComponent(lumoIcon);
        search.setWidth("1000px");



        cover = new Image("https://pbs.twimg.com/media/F-FWz5gbMAAuPP_?format=jpg", "Alternative text");
        cover.setWidth("720px");
        cover.setHeight("500px");

        heartIcon = new Icon(VaadinIcon.HEART);
        playPauseButton = new Button(new Icon(VaadinIcon.PLAY));



        loadSong();
        loadComment();
        updateUpNextTabContent();

        TabSheet tabSheet = new TabSheet();
        tabSheet.add("UP NEXT", upNext);
        tabSheet.add("LYRICS", lyrics);
        tabSheet.add("COMMENTS", comment);


        tabSheet.setHeight("500px");
        tabSheet.setWidth("320px");
        h2.add(cover, tabSheet);
        rightColumn.add(search, h2);


//        pauseButton = new Button("Pause");
        playButton.addClickListener(buttonClickEvent -> {
            if (!isPlaying) {
                isPlaying = true;
                CompletableFuture.runAsync(() -> playAudio(dataMusic));
            }
        });
        stopButton.addClickListener(event -> {
            stopAudio();
            System.out.println("stop" + this.playerAudio);
        });
//        pauseButton.addClickListener(event -> {
//            pauseAudio();
//        });




        System.out.println(this.songs.size());
        h1.add(leftColumn, rightColumn);
        add(h1, footer);

    }
//    public void pauseAudio() {
//        if (playerAudio != null && !isPaused) {
//            int currentFrame = playerAudio.getPlayBackListener();
//            playerAudio.close();
//            playerAudio = null;
//            pausedFrame = currentFrame;
//            isPaused = true;
//        }else {
//            try {
//                // Create a new player starting from the remembered frame
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(dataMusic);
//                Bitstream bitstream = new Bitstream(inputStream);
//                bitstream.readFrame(); // Read one frame to advance the stream
//                playerAudio = new AdvancedPlayer(inputStream);
//                playerAudio.play(pausedFrame, Integer.MAX_VALUE); // Resume from the paused position
//                isPaused = false;
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void playAudio(byte[] audioData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(audioData);
            playerAudio = new AdvancedPlayer(inputStream);

            // Add a listener to handle events (optional)
            playerAudio.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    // Handle playback finished event if needed
                }
            });

            // Start playing
            playerAudio.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
    public void stopAudio() {
        if (playerAudio != null) {
            playerAudio.close();
            playerAudio = null;
        }
    }

    public void loadComment(){
//        this.comments = WebClient.create()
//                .get()
//                .uri("http://localhost:8080/getComment")
//                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Comment>>() {})
//                .block();
        Image imageProfile = new Image("https://f.ptcdn.info/237/077/000/rbpkfh1n811fuK3R1MbUf-o.jpg", "profile user");
        imageProfile.getStyle().set("border-radius", "50%");
        imageProfile.setHeight("50px");
        imageProfile.setWidth("50px");


        VerticalLayout content = new VerticalLayout();
        Text usernameText = new Text("username - 20-10-2010\n");
        Text descriptionText = new Text("\nNeque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit");
//        descriptionText.getStyle().set("font-weight", "bold");
        content.add(usernameText, descriptionText);

        HorizontalLayout h = new HorizontalLayout();
        h.getStyle().set("border-bottom", "1px solid #ccc");
        h.setPadding(false);
        h.add(imageProfile, content);


        comment.add(h);
    }
    public void loadSong(){
        this.songs = WebClient.create()
                .get()
                .uri("http://localhost:8080/getSong")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Song>>() {})
                .block();

        System.out.println(this.songs.get(0).getDataAudio());
        String DataAudioId = this.songs.get(0).getDataAudio();
// พัง!!!!
//        this.music = WebClient.create()
//                .get()
//                .uri("http://localhost:8080/getAudio/" + DataAudioId)
//                .retrieve().bodyToMono(Music.class)
//                .block();
//        this.dataMusic = this.music.getData();
//        System.out.println("music data" + this.music);
        WebClient.Builder webClientBuilder = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs()
                                .maxInMemorySize(5 * 1024 * 1024)) // Set the buffer limit to 5 MB
                        .build());

        byte[] audioData = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/getAudio/" + DataAudioId)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();


        this.dataMusic = audioData;

// Now 'audioData' contains the raw audio data
//        System.out.println("music data: " + Arrays.toString(audioData));
//        System.out.println(this.music);
        System.out.println(this.songs.get(0).getTitle());

//        Footer
        HorizontalLayout playerContent = new HorizontalLayout();
        Image coverPlayer = new Image(this.songs.get(0).getImage(), "cover player");
        cover.setSrc(this.songs.get(0).getImage());
        coverPlayer.setWidth("80px");
        coverPlayer.setHeight("80px");
        coverPlayer.getStyle().set("border-radius", "10%");

        VerticalLayout description = new VerticalLayout();
        Text titleText = new Text(this.songs.get(0).getTitle() + "\n");
        Text artistText = new Text("\n" + this.songs.get(0).getArtist());
        description.setWidth("30%");
        description.add(titleText, artistText);

        playerContent.add(coverPlayer, description, heartIcon, playPauseButton);
//        playerContent.addComponent(textField, 0, 1); // Add to the second row




        player.add(playerContent);
    }

    private void updateUpNextTabContent() {
        listSong = new HorizontalLayout();
        Image cover = new Image(songs.get(0).getImage(), "cover image");
        cover.setWidth("100px");
        listSong.add(cover,
                new Text(songs.get(0).getTitle() + '\n' + songs.get(0).getArtist()),
                new Text("Time")
        );
        listSong.setWidth("350px");
        if (songs != null && !songs.isEmpty()) {
            VerticalLayout upNextContent = new VerticalLayout();

            for (Song song : songs) {
                Image coverImage = new Image(song.getImage(), "cover image");
                coverImage.setWidth("80px");

                HorizontalLayout songInfo = new HorizontalLayout(
                        coverImage,
                        new Text(song.getTitle() + '\n' + song.getArtist()),
                        new Text("Time")
                );
                HorizontalLayout songEntry = new HorizontalLayout(songInfo);
                songEntry.setWidth("100%");
                upNextContent.add(songEntry);
            }
            upNext.removeAll();
            upNext.add(upNextContent);
            lyrics.add(songs.get(0).getLyrics());
        } else {
            upNext.removeAll();
            upNext.add(new Text("No data"));
        }
    }

}
