package com.sthev.spotify.controllers;

import com.sthev.spotify.client.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient spotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient spotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.spotifyClient = spotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWord(){
        var request = new LoginRequest(
                "client_credentials",
                "89e2d637ba66442daccbd340e4d92c0b",
                "c42393730629496891a546ab48b99998"
        );

        var token = spotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
