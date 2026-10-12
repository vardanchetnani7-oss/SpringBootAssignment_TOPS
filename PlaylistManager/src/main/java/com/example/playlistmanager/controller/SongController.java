package com.example.playlistmanager.controller;

import com.example.playlistmanager.model.Song;
import com.example.playlistmanager.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // POST /songs
    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(song));
    }

    // GET /songs            -> all songs
    // GET /songs?artist=X   -> songs by artist (uses the custom repository method)
    @GetMapping
    public List<Song> getSongs(@RequestParam(required = false) String artist) {
        if (artist != null && !artist.isBlank()) {
            return songService.getSongsByArtist(artist);
        }
        return songService.getAllSongs();
    }

    // DELETE /songs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSong(@PathVariable Long id) {
        if (songService.deleteSong(id)) {
            return ResponseEntity.ok(Map.of("message", "Song " + id + " deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Song with id " + id + " not found"));
    }
}
