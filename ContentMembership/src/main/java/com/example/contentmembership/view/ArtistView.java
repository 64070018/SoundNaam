package com.example.contentmembership.view;

import com.example.contentmembership.POJO.ContentMembership;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PageTitle("Artist Song")
@Route(value = "artist")
public class ArtistView extends VerticalLayout {
    // attribute
    private ContentMembership content;
    private List<ContentMembership> contents;

    // ui
    Icon lumoIcon = LumoIcon.SEARCH.create();
    Icon like = VaadinIcon.HEART_O.create();
    Icon heart = VaadinIcon.HEART_O.create();
    Icon textcomment = VaadinIcon.COMMENT_ELLIPSIS_O.create();
    Icon left = VaadinIcon.CHEVRON_CIRCLE_LEFT.create();
    Icon right = VaadinIcon.CHEVRON_CIRCLE_RIGHT.create();
    Icon play = VaadinIcon.PLAY_CIRCLE.create();
    Icon stop = VaadinIcon.STOP.create();

    private Div divPost, mysong, myalbum, mymember;
    private TextArea mypost;
    private VerticalLayout Third, mepost, Sidebar, Main, Artist, verisong, verialbum, createpost, space, textinfobottom;
    private HorizontalLayout Mymepost, secondmain, SearchandAvatar, iconlike, iconcomment, icon, Secornd, tab, horisong,
            horialbum, Me, mainbottom, iconbottom;
    private Text date, mename2, numberlike, numbercomment, album, timesong, artistalbum, countalbum, mename;
    private H6 textpost, artistbottom;
    private Avatar avatar, avatar3, avatar2;
    private TextField search;
    private H1 name;
    private Button follow, subscription, Postpost;
    private Image coverSong, coverAlbum, imagebottom;
    private H4 songname, albumname, namebottom;
    private FlexLayout mybuttonpost;
    TabSheet tabSheet;

    // Div MySong = new Div();

    public ArtistView() {
        loading();
        secondmain = new HorizontalLayout();
        secondmain.setSizeFull();
        secondmain.setPadding(false);
        secondmain.setMargin(false);

        this.setSizeFull();
        this.setPadding(false);
        this.setMargin(false);

        // nav ข้าง ๆ
        Sidebar = new VerticalLayout();
        Sidebar.setPadding(false);
        Sidebar.setMargin(false);

        Sidebar.setWidth(20, Unit.PERCENTAGE);
        Sidebar.setHeightFull();
        Sidebar.getStyle().set("background-color", "#5F9DB2");

        // main
        Main = new VerticalLayout();
        Main.setPadding(false);
        Main.setSpacing(false);

        // cover and name artist
        Artist = new VerticalLayout();
        Artist.setWidthFull();
        Artist.setHeight(100, Unit.PERCENTAGE);
        Artist.getElement().getStyle().set("background-image",
                "url('https://centaur-wp.s3.eu-central-1.amazonaws.com/creativereview/prod/content/uploads/2018/10/13.jpg?auto=compress,format&q=60&w=1200&h=1217')");

        Main.add(Artist);

        // Search and Avatar
        SearchandAvatar = new HorizontalLayout();
        SearchandAvatar.setWidthFull();

        avatar = new Avatar();
        avatar.setImage("https://th.bing.com/th/id/OIP.Z4UUr7rXPvOvoALaQfeEnAHaFj?pid=ImgDet&rs=1");

        search = new TextField();
        search.setPlaceholder("search");
        search.setSuffixComponent(lumoIcon);
        search.setWidth("1000px");
        search.getStyle().set("background-color", "white");
        SearchandAvatar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        SearchandAvatar.add(search, avatar);

        // Text and Sub
        Secornd = new HorizontalLayout();
        Secornd.setSizeFull();
        name = new H1("Artist Name");

        follow = new Button("follow");
        subscription = new Button("subscription");

        Secornd.add(name, follow, subscription);
        Secornd.setAlignSelf(Alignment.END, name, follow, subscription);

        Artist.add(SearchandAvatar, Secornd);

        // song album
        Third = new VerticalLayout();
        Third.setSizeFull();
        Third.getStyle().set("background-color", "#ECE7E3");
        Main.add(Third);

        // Tabsheet
        tab = new HorizontalLayout();
        tab.setSizeFull();

        // mock song
        horisong = new HorizontalLayout();
        horisong.setSizeFull();
        horisong.setAlignItems(Alignment.CENTER);
        coverSong = new Image(
                "https://cdn.discordapp.com/attachments/979676845228318740/1163524636257030254/Awesome_Heaven_Officials_Blessing_Wallpapers_-_WallpaperAccess.png?ex=655b9342&is=65491e42&hm=06cdf1826b3dae56d43b59f478413c03080ce1c8331551e9e087c85f4bd990cb&",
                "coverSong");
        coverSong.setHeight(100, Unit.PIXELS);
        coverSong.setWidth(100, Unit.PIXELS);

        verisong = new VerticalLayout();
        songname = new H4("name");
        album = new Text("album");
        verisong.add(songname, album);

        timesong = new Text("3:40");

        horisong.add(coverSong, verisong, timesong);

        mysong = new Div();
        mysong.add(horisong);

        // mock album
        myalbum = new Div();
        horialbum = new HorizontalLayout();
        horialbum.setSizeFull();
        horialbum.setAlignItems(Alignment.CENTER);
        coverAlbum = new Image(
                "https://cdn.discordapp.com/attachments/979676845228318740/1163524636257030254/Awesome_Heaven_Officials_Blessing_Wallpapers_-_WallpaperAccess.png?ex=655b9342&is=65491e42&hm=06cdf1826b3dae56d43b59f478413c03080ce1c8331551e9e087c85f4bd990cb&",
                "coverSong");
        coverAlbum.setHeight(100, Unit.PIXELS);
        coverAlbum.setWidth(100, Unit.PIXELS);

        verialbum = new VerticalLayout();
        albumname = new H4("name");
        artistalbum = new Text("artist");
        verialbum.add(albumname, artistalbum);

        countalbum = new Text("50 Songs");

        horialbum.add(coverAlbum, verialbum, countalbum);
        myalbum.add(horialbum);

        // membership
        mymember = new Div();
        // createpost
        createpost = new VerticalLayout();
        createpost.setWidthFull();
        createpost.setHeightFull();
        createpost.getStyle().set("background-color", "white");

        Me = new HorizontalLayout();
        mename = new Text("namae");
        mypost = new TextArea();
        avatar2 = new Avatar();
        avatar2.setImage("https://th.bing.com/th/id/OIP.Z4UUr7rXPvOvoALaQfeEnAHaFj?pid=ImgDet&rs=1");

        Me.add(avatar2, mename);
        Me.setAlignItems(Alignment.CENTER);

        mypost.setPlaceholder("อยากโพสอะไร");
        mypost.setSizeFull();

        Postpost = new Button("POST");
        Postpost.getStyle().set("background-color", "#F2AA49");
        Postpost.addClickListener(event -> submitPost());
        mybuttonpost = new FlexLayout(Postpost);
        mybuttonpost.setJustifyContentMode(JustifyContentMode.END);
        mybuttonpost.setSizeFull();
        createpost.add(Me, mypost, mybuttonpost);

        // Space
        space = new VerticalLayout();

        // post
        mepost = new VerticalLayout();
        mepost.setSizeFull();
        mepost.getStyle().set("background-color", "white");

        Mymepost = new HorizontalLayout();
        date = new Text("110 ปีที่แล้ว");

        mename2 = new Text("namae");
        avatar3 = new Avatar();
        avatar3.setImage("https://th.bing.com/th/id/OIP.Z4UUr7rXPvOvoALaQfeEnAHaFj?pid=ImgDet&rs=1");

        Mymepost.add(avatar3, mename2, date);
        Mymepost.setAlignItems(Alignment.CENTER);

        textpost = new H6("มีเรื่องอยากจะบอกแต่นึกไม่ออกจะบอกอะไร");

        iconlike = new HorizontalLayout();
        numberlike = new Text("1.1k");
        iconlike.add(like, numberlike);

        iconcomment = new HorizontalLayout();
        numbercomment = new Text("1k");
        iconcomment.add(textcomment, numbercomment);

        icon = new HorizontalLayout();
        icon.add(iconlike, iconcomment);

        post();
        mepost.add(Mymepost, textpost, icon);
        mymember.add(createpost, space, divPost);

        tabSheet = new TabSheet();
        tabSheet.add("SONG", mysong);
        tabSheet.add("ALBUM", myalbum);
        tabSheet.add("MEMBERSHIP", mymember);
        tabSheet.setWidthFull();

        tab.add(tabSheet);

        Third.add(tab);

//        // tab bottom
//        mainbottom = new HorizontalLayout();
//        mainbottom.setWidthFull();
//        mainbottom.setHeight(8, Unit.PERCENTAGE);
//        mainbottom.getStyle().set("background-color", "#82C0CC");
//
//        imagebottom = new Image("https://th.bing.com/th/id/OIP.Z4UUr7rXPvOvoALaQfeEnAHaFj?pid=ImgDet&rs=1","cover");
//        imagebottom.setWidth(60, Unit.PIXELS);
//        imagebottom.setHeight(60, Unit.PIXELS);
//        imagebottom.getStyle().set("margin-left", "50px");
//        imagebottom.getStyle().set("margin-top", "10px");
//        imagebottom.getStyle().set("border-radius", "5px");
//
//        textinfobottom = new VerticalLayout();
//        namebottom = new H4("name");
//        artistbottom = new H6("artist");
//        textinfobottom.add(namebottom, artistbottom);
//        textinfobottom.setWidth("15%");
//
//        iconbottom = new HorizontalLayout();
//        iconbottom.setWidth("40%");
//
//        iconbottom.add(left, stop, right);
//        iconbottom.setJustifyContentMode(JustifyContentMode.CENTER);
//
//        heart.getStyle().set("margin-top", "25px");
//        iconbottom.getStyle().set("margin-top", "25px");
//        iconbottom.getStyle().set("margin-left", "50px");
//
//        mainbottom.add(imagebottom, textinfobottom, heart, iconbottom);
//
        secondmain.add(Sidebar, Main);

        // main add
        this.add(secondmain);
    }

    public void post() {
        divPost = new Div();
        if (contents != null && !contents.isEmpty()) {
            mepost = new VerticalLayout();
            mepost.setSizeFull();
            mepost.getStyle().set("background-color", "white");
            mepost.getStyle().set("margin-top", "10px");
            for (ContentMembership content : contents) {

                Mymepost = new HorizontalLayout();
                date = new Text("");
                date.setText(String.valueOf(content.getDate()));

                mename2 = new Text("");
                mename2.setText(content.getArtist());
                avatar3 = new Avatar();
                avatar3.setImage("https://th.bing.com/th/id/OIP.Z4UUr7rXPvOvoALaQfeEnAHaFj?pid=ImgDet&rs=1");

                Mymepost.add(avatar3, mename2, date);
                Mymepost.setAlignItems(Alignment.CENTER);

                textpost = new H6("");
                textpost.setText(content.getContent());

                iconlike = new HorizontalLayout();
                numberlike = new Text("1.1k");
                numberlike.setText(String.valueOf(content.getLike()));
                iconlike.add(VaadinIcon.HEART_O.create(), numberlike);

                icon = new HorizontalLayout();
                icon.add(iconlike);

                mepost.add(Mymepost, textpost, icon);

            }
        } else {
            mepost.add(new Text("ไม่มีจ้า"));

        }
        divPost.add(mepost);

        // mymember.add(createpost,space, mepost); for add to tab
    }

    public List<ContentMembership> loading() {
        this.contents = WebClient.create()
                .get()
                .uri("http://localhost:8080/getContent")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<ContentMembership>>() {
                })
                .block();
        System.out.println("content is " + this.contents);
        return this.contents;
    }

    public boolean submitPost() {
        System.out.println("submit post");
        content = new ContentMembership(mypost.getValue(), "artistName", "email", new Date(), 0);
        Boolean output = WebClient.create()
                .post()
                .uri("http://localhost:8080/addContent")
                .bodyValue(content)
                .retrieve().bodyToMono(Boolean.class).block();
        mypost.setValue("");

        return output;
    }
    // public boolean updatePost(){
    // System.out.println("submit post");
    // content = new ContentMembership(mypost.getValue(), "artistName", "email", new
    // Date(), 0);
    // Boolean output = WebClient.create()
    // .post()
    // .uri("http://localhost:8080/updateContent")
    // .bodyValue(content)
    // .retrieve().bodyToMono(Boolean.class).block();
    //
    // return output;
    // }

    public boolean deletePost() {
        System.out.println("submit post");
        // this mypost is change get data from loop
        content = new ContentMembership(mypost.getValue(), "artistName", "email", new Date(), 0);
        Boolean output = WebClient.create()
                .post()
                .uri("http://localhost:8080/deleteContent")
                .bodyValue(content)
                .retrieve().bodyToMono(Boolean.class).block();
        return output;
    }
}
