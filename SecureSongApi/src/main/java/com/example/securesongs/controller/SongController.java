package com.example.securesongs.controller;

import com.example.securesongs.dto.SongRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SongController {

    // If validation fails, Spring throws MethodArgumentNotValidException before
    // this method body runs; GlobalExceptionHandler turns it into a 400 response.
    @PostMapping("/addSong")
    public ResponseEntity<Map<String, Object>> addSong(@Valid @RequestBody SongRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Song added successfully");
        response.put("title", request.getTitle());
        response.put("artist", request.getArtist());
        response.put("duration", request.getDuration());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
