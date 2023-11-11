package com.example.podcastservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PodcastServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PodcastServiceApplication.class, args);
        System.out.println("PodcastService");
    }

}
