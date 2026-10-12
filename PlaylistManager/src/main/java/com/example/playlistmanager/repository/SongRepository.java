package com.example.playlistmanager.repository;

import com.example.playlistmanager.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    // Spring Data derives the query from the method name
    List<Song> findByArtist(String artist);
}
