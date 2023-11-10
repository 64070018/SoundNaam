package com.example.songservice.View;


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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Route("/song")
public class SongPlayView extends VerticalLayout {
//    attribute
    private List<Song> songs;




//    ui component
    private TextField search;
    private Button ad, update, delete, clear;
    Icon lumoIcon = LumoIcon.SEARCH.create();

    Icon heartIcon;
    TabSheet tabSheet;
    VerticalLayout leftColumn, rightColumn;
    HorizontalLayout h1, h2, listSong;
    Image cover;
    Div upNext = new Div();
    Div lyrics = new Div();
    Div player = new Div();

    public SongPlayView() {
//        data
        songs = new ArrayList<>();




//        set layout
        h1 = new HorizontalLayout();
        h2 = new HorizontalLayout();
//        h1.setWidth("100%");

        leftColumn = new VerticalLayout();
        leftColumn.setWidth("250px");
        leftColumn.add(new Button("Button 1"));

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



        loadSong();
        updateUpNextTabContent();

        TabSheet tabSheet = new TabSheet();
        tabSheet.add("UP NEXT", upNext);
        tabSheet.add("LYRICS", lyrics);
        tabSheet.add("COMMENTS",
                new Div(new Text("This is the comment tab content")));


        tabSheet.setHeight("500px");
        tabSheet.setWidth("320px");
        h2.add(cover, tabSheet);
        rightColumn.add(search, h2);





        System.out.println(this.songs.size());
        h1.add(leftColumn, rightColumn);
        add(h1, footer);



    }




    public void loadSong(){
        this.songs = WebClient.create()
                .get()
                .uri("http://localhost:8080/getSong")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Song>>() {})
                .block();
        System.out.println(this.songs);
        System.out.println(this.songs.get(0).getTitle());

        Image coverPlayer = new Image(this.songs.get(0).getImage(), "cover player");
        coverPlayer.setWidth("80px");
        coverPlayer.setHeight("80px");
        Text text = new Text(this.songs.get(0).getTitle() + "\n" + this.songs.get(0).getArtist());

        player.add(coverPlayer, text, heartIcon);
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
