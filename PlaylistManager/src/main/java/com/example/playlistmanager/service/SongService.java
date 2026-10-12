package com.example.playlistmanager.service;

import com.example.playlistmanager.model.Song;
import com.example.playlistmanager.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song addSong(Song song) {
        song.setId(null); // always insert a new row
        return songRepository.save(song);
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(songs::add);
        return songs;
    }

    public List<Song> getSongsByArtist(String artist) {
        return songRepository.findByArtist(artist);
    }

    /** @return true if a song was deleted, false if the id did not exist */
    public boolean deleteSong(Long id) {
        if (!songRepository.existsById(id)) {
            return false;
        }
        songRepository.deleteById(id);
        return true;
    }
}
