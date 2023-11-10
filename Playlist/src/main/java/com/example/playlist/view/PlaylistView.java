package com.example.playlist.view;

import com.example.playlist.POJO.Playlist;
import com.example.songservice.POJO.Song;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Route(value = "playlist")
public class PlaylistView extends VerticalLayout {
    private List<Playlist> playlists;
    private List<Song> songs;
    Div playlistSong = new Div();

    public PlaylistView() {
        playlists = new ArrayList<>();
        songs = new ArrayList<>();

        loadplaylist();
    }
    public void loadplaylist(){
        this.playlists = WebClient.create()
                .get()
                .uri("http://localhost:8080/getAllPlaylist")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Playlist>>() {})
                .block();
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println(this.playlists);
        this.songs = this.playlists.get(0).getSong();
        System.out.println(this.songs);

        //headPlaylist
        HorizontalLayout hori1 = new HorizontalLayout();
        Image cover = new Image(playlists.get(0).getCover(), "coverPlaylist");
        cover.setHeight(200, Unit.PIXELS);
        cover.setWidth(200, Unit.PIXELS);

        VerticalLayout veri = new VerticalLayout();
        H1 title = new H1(playlists.get(0).getPlaylistName());
        H4 status = new H4((playlists.get(0).getStatus()));

        HorizontalLayout hori2 =new HorizontalLayout();
        Text numSong = new Text("50 Songs");
        Text time = new Text("50 minutes");
        hori2.add(numSong, new Text("  "), time);

        HorizontalLayout hori3 =new HorizontalLayout();
        Button play = new Button("play");
        Button editPlay = new Button("editPlaylist");
        hori3.add(play, editPlay);

        veri.add(title, status, hori2, hori3);
        hori1.add(cover, veri);
//        this.add(hori1);

        //songPlaylist
        HorizontalLayout listSong = new HorizontalLayout();
        Image coverSong = new Image(songs.get(0).getImage(), "cover image");
        coverSong.setWidth("100px");

        VerticalLayout veriSong = new VerticalLayout();
        H4 songName = new H4(songs.get(0).getTitle());
        Text artist = new Text(songs.get(0).getArtist());
        veriSong.add(songName, artist);

        Text timeSong = new Text("3:40");

        listSong.setSizeFull();
        listSong.setAlignItems(Alignment.CENTER);
        listSong.add(coverSong, veriSong, timeSong);

        if (songs != null && !songs.isEmpty()) {
            VerticalLayout allSongs = new VerticalLayout();
            for (Song song : songs) {
                Image coverImage = new Image(song.getImage(), "cover image");
                coverImage.setWidth("80px");

                HorizontalLayout listSongs = new HorizontalLayout();
                coverImage.setWidth("100px");

                VerticalLayout veriSongs = new VerticalLayout();
                H4 songNames = new H4(songs.get(0).getTitle());
                Text artists = new Text(songs.get(0).getArtist());
                veriSongs.add(songNames, artists);

                Text timeSongs = new Text("3:40");

                listSongs.setSizeFull();
                listSongs.setAlignItems(Alignment.CENTER);
                listSongs.add(coverImage, veriSongs, timeSongs);

                allSongs.add(listSongs);
            }
            playlistSong.add(allSongs);
            playlistSong.setSizeFull();
        }
        else {
            playlistSong.removeAll();
            playlistSong.add(new Text("No data"));
        }
            this.add(hori1, playlistSong);
    }
}
