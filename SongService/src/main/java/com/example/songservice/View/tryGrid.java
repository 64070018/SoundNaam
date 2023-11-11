    package com.example.songservice.View;
    import com.vaadin.flow.component.html.Div;
    import com.vaadin.flow.component.html.Image;
    import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
    import com.vaadin.flow.component.orderedlayout.VerticalLayout;
    import com.vaadin.flow.component.textfield.TextField;
    import com.vaadin.flow.dom.ThemeList;
    import com.vaadin.flow.router.Route;

    import java.util.ArrayList;
    import java.util.List;

    @Route("testGrid")
    public class tryGrid extends VerticalLayout {

        private List<Album> albums;
        private HorizontalLayout albumsLayout;

        public tryGrid() {
            // Sample data for demonstration
            albums = new ArrayList<>();
            albums.add(new Album("Album Name 1", "Artist 1"));
            albums.add(new Album("Album Name 2", "Artist 2"));
            albums.add(new Album("Album Name 3", "Artist 3"));
            albums.add(new Album("Album Name 4", "Artist 4"));
            albums.add(new Album("Album Name 5", "Artist 5"));
            albums.add(new Album("Album Name 6", "Artist 6"));
            albums.add(new Album("Album Name 7", "Artist 7"));
            albums.add(new Album("Album Name 8", "Artist 8"));
            albums.add(new Album("Album Name 9", "Artist 9"));

            // Create a layout to hold the list of albums
            albumsLayout = new HorizontalLayout();
            albumsLayout.setWidthFull();
            add(albumsLayout);

            // Iterate over the albums and create cards for each
            VerticalLayout columnLayout = new VerticalLayout();
            columnLayout.setPadding(false);
            columnLayout.setSpacing(false);

            for (int i = 0; i < albums.size(); i++) {
                VerticalLayout card = createCard(albums.get(i));

                // Add the card to the column
                columnLayout.add(card);

                // Check if four cards have been added to the column
                if ((i + 1) % 4 == 0 || i == albums.size() - 1) {
                    // Add the column to the main layout
                    albumsLayout.add(columnLayout);

                    // Create a new column
                    columnLayout = new VerticalLayout();
                    columnLayout.setPadding(false);
                    columnLayout.setSpacing(false);
                }
            }
        }

        private VerticalLayout createCard(Album album) {
            // Create components for the card
            Image img = new Image(album.getImageUrl(), "Album Cover");
            img.setWidth("150px");

            TextField nameText = new TextField("Name: " + album.getName());
            TextField artistText = new TextField("Artist: " + album.getArtist());

            // Create a vertical layout to hold the components
            VerticalLayout cardLayout = new VerticalLayout();
            cardLayout.add(img, nameText, artistText);
            cardLayout.setWidth("200px"); // Set the width as needed

            return cardLayout;
        }

        // Sample Album class for demonstration
        private static class Album {
            private String imageUrl;
            private String name;
            private String artist;

            public Album(String name, String artist) {
                this.imageUrl = "https://i.pinimg.com/originals/31/c0/0b/31c00b5afd3a9700111c094f8c0169f3.jpg";
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

