package com.example.sop_week6_ver_final.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;

@Route("Podcast")
public class HomePodcast extends AppLayout {
    private TextField searchbar;
    private VerticalLayout layoutcol, podcastlist;
    private HorizontalLayout searchcol;
    private Tabs tabs;
    private Avatar user;
    private H3 podcast, series;
    private List<Podcast> allpodcast;

    public HomePodcast() {
        searchbar = new TextField();
        layoutcol = new VerticalLayout();
        tabs = new Tabs();
        user = new Avatar("Anpanprang");
        searchcol = new HorizontalLayout();
        podcast = new H3("PODCASTS");
        series = new H3("SERIES");
        podcastlist = new VerticalLayout();
        allpodcast = new ArrayList<>();

        tabs.add(createTab(VaadinIcon.HOME, "Home", HomeSong.class),
                createTab(VaadinIcon.BOOKMARK, "Subscriptions", HomeSong.class),
                createTab(VaadinIcon.HEADPHONES, "Podcast", HomePodcast.class),
                createTab(VaadinIcon.PLAY_CIRCLE, "Playlist", HomeSong.class),
                createTab(VaadinIcon.TIME_BACKWARD, "History", HomeSong.class));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setSelectedTab(tabs.getTabAt(2));
        addToDrawer(tabs);

        user.addThemeVariants(AvatarVariant.LUMO_XLARGE);

        searchbar.setPlaceholder("Search");
        searchbar.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchbar.setWidth("90%");

        searchcol.setWidthFull();
        searchcol.setAlignItems(FlexComponent.Alignment.CENTER);
        searchcol.setSpacing(true);
        searchcol.add(searchbar, user);


        podcastlist.add(createPodcastlist(new Podcast("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 1", "Artist 1", "3.59")));
        layoutcol.setWidthFull();
        layoutcol.add(searchcol, podcast, podcastlist, series);
        setContent(layoutcol);
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class content) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        // Demo has no routes
        link.setRoute(content);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    private HorizontalLayout createPodcastlist(Podcast podcast) {
        Image img = new Image(podcast.getImageUrl(), "Album Cover");
        img.setWidth("150px");

        H5 nameText = new H5(podcast.getName());
        H6 artistText = new H6(podcast.getArtist());
        H6 DateText = new H6(podcast.getDate());

        // Create a vertical layout to hold the components
        HorizontalLayout cardLayout = new HorizontalLayout();
        VerticalLayout textLayout = new VerticalLayout();
        textLayout.add(nameText, artistText);

        Div spacer = new Div();
        spacer.setWidth("90%");
        
        cardLayout.add(img, textLayout, spacer, DateText);
        cardLayout.setSpacing(true);
        cardLayout.setWidth("90%"); // Set the width as needed
        cardLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return cardLayout;
    }

    private static class Podcast {
        private String imageUrl;
        private String name;
        private String artist;
        private String date;

        public Podcast(String imageUrl, String name, String artist, String date) {
            this.imageUrl = imageUrl;
            this.name = name;
            this.artist = artist;
            this.date = date;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;
        }

        public String getArtist() {
            return artist;
        }

        public String getDate() {
            return date;
        }
    }

}
