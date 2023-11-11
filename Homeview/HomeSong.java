package com.example.sop_week6_ver_final.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;

import java.util.ArrayList;
import java.util.List;

@Route(value = "Song")
public class HomeSong extends AppLayout {
    private TextField searchbar;
    private HorizontalLayout horizontalLayout, albumlist, artistlist;
    private VerticalLayout layoutcol, albumcol;
    private Tabs tabs;
    private Avatar user;
    private H3 albums, musics, artist;
    private Image img;
    private List<Album> displayalbums;
    private HorizontalLayout albumsLayout;

    public HomeSong() {
        searchbar = new TextField();
        horizontalLayout = new HorizontalLayout();
        layoutcol = new VerticalLayout();
        tabs = new Tabs();
        user = new Avatar("Anpanprang");
        albums = new H3("ALBUMS");
        musics = new H3("MUSICS");
        artist = new H3("ARTISTS");
        albumlist = new HorizontalLayout();
        img = new Image();
        albumcol = new VerticalLayout();
        artistlist = new HorizontalLayout();

        // drawer
        tabs.add(createTab(VaadinIcon.HOME, "Home", HomeSong.class),
                createTab(VaadinIcon.BOOKMARK, "Subscriptions", HomeSong.class),
                createTab(VaadinIcon.HEADPHONES, "Podcast", HomePodcast.class),
                createTab(VaadinIcon.PLAY_CIRCLE, "Playlist", HomeSong.class),
                createTab(VaadinIcon.TIME_BACKWARD, "History", HomeSong.class));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setSelectedTab(tabs.getTabAt(0));
        addToDrawer(tabs);

        /*user.setImage();

        Avatar company = new Avatar("Company Inc.");
        StreamResource imageResource = new StreamResource("company-logo.png",
                () -> getClass()
                        .getResourceAsStream("/images/company-logo.png"));
        company.setImageResource(imageResource);*/
        user.addThemeVariants(AvatarVariant.LUMO_XLARGE);

        searchbar.setPlaceholder("Search");
        searchbar.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchbar.setWidth("90%");

        img.setSrc("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg");
        albumlist.add(createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist"),
                createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist"),
                createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist"),
                createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist"),
                createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist"),
                createCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name", "Artist")
        );

        displayalbums = new ArrayList<>();
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 1", "Artist 1"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 2", "Artist 2"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 3", "Artist 3"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 4", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 5", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 6", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 7", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 8", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 9", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 10", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 11", "Artist 4"));
        displayalbums.add(new Album("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg", "Album Name 12", "Artist 4"));

        // Create a vertical layout to hold the list of albums
        albumsLayout = new HorizontalLayout();

        // Create a layout to hold the list of albums
        albumsLayout = new HorizontalLayout();
        albumsLayout.setWidthFull();
        albumcol.add(albumsLayout);

        for (int i = 0; i < displayalbums.size(); i++) {
            HorizontalLayout card = createAlbumCard(displayalbums.get(i));

            // Add the card to the current column layout
            albumsLayout.add(card);

            // Check if three cards have been added to the current column
            if ((i + 1) % 4 == 0) {
                // Add spacing between columns
                albumsLayout.setSpacing(true);

                // Create a new column layout
                albumsLayout = new HorizontalLayout();
                albumsLayout.setWidthFull();
                albumcol.add(albumsLayout);
            }
        }

        artistlist.add(createArtistCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg","Artist"),
                createArtistCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg","Artist"),
                createArtistCard("https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg","Artist"));

        horizontalLayout.setWidthFull();
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        horizontalLayout.setSpacing(true);
        horizontalLayout.add(searchbar, user);
        layoutcol.add(horizontalLayout, albums, albumlist, musics, albumcol, artist, artistlist);
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
/*
        link.setTabIndex(-1);
*/

        return new Tab(link);
    }

    private Div createCard(String imageUrl, String name, String artist) {
        Image img = new Image(imageUrl, "Album Cover");
        img.setWidth("150px");
        H5 displayname = new H5(name);
        H6 displayartist = new H6(artist);
        VerticalLayout col = new VerticalLayout();
        col.add(img);
        col.add(displayname);
        col.add(displayartist);
        col.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return new Div(col);
    }

    private HorizontalLayout createAlbumCard(Album album) {
        // Create components for the card
        Image img = new Image(album.getImageUrl(), "Album Cover");
        img.setWidth("140px");

        H5 nameText = new H5("Name: " + album.getName());
        H6 artistText = new H6("Artist: " + album.getArtist());

        // Create a vertical layout to hold the components
        HorizontalLayout cardLayout = new HorizontalLayout();
        VerticalLayout textLayout = new VerticalLayout();
        textLayout.add(nameText, artistText);
        cardLayout.add(img, textLayout);
        cardLayout.setWidth("280px"); // Set the width as needed

        return cardLayout;
    }

    private Div createArtistCard(String imageUrl, String artist) {
        Image img = new Image(imageUrl, "Album Cover");
        img.setWidth("150px");
        img.getStyle().set("border-radius", "50%");
        H6 displayartist = new H6(artist);
        VerticalLayout col = new VerticalLayout();
        col.add(img);
        col.add(displayartist);
        col.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        return new Div(col);
    }

    private static class Album {
        private String imageUrl;
        private String name;
        private String artist;

        public Album(String imageUrl, String name, String artist) {
            this.imageUrl = imageUrl;
            this.name = name;
            this.artist = artist;
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
    }

}
