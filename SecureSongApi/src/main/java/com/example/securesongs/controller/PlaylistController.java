package com.example.securesongs.controller;

import com.example.securesongs.dto.PlaylistRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PlaylistController {

    // Protected by Basic Auth (see SecurityConfig)
    @PostMapping("/addPlaylist")
    public ResponseEntity<Map<String, Object>> addPlaylist(@Valid @RequestBody PlaylistRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Playlist added successfully");
        response.put("name", request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
