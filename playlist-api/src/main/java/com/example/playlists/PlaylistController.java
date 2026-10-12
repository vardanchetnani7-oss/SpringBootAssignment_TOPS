package com.example.playlists;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example requests:
 *   curl localhost:8080/playlists
 *   curl -X POST   localhost:8080/playlists   -H "Content-Type: application/json" -d '{"name":"Focus"}'
 *   curl -X PUT    localhost:8080/playlists/1 -H "Content-Type: application/json" -d '{"name":"Calm Vibes"}'
 *   curl -X DELETE localhost:8080/playlists/1
 */
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final Map<Integer, String> playlists = new ConcurrentSkipListMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public PlaylistController() {
        playlists.put(idGenerator.incrementAndGet(), "Chill Vibes");
        playlists.put(idGenerator.incrementAndGet(), "Top Hits");
        playlists.put(idGenerator.incrementAndGet(), "Workout Mix");
    }

    // 1. GET /playlists -> ["Chill Vibes","Top Hits","Workout Mix"]
    @GetMapping
    public List<String> getPlaylists() {
        return new ArrayList<>(playlists.values());
    }

    // 2. POST /playlists  body: {"name":"Focus"}
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlaylist(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        if (name == null || name.isBlank()) {
            return error(HttpStatus.BAD_REQUEST, "Playlist name is required");
        }

        int id = idGenerator.incrementAndGet();
        playlists.put(id, name);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", id);
        response.put("name", name);
        response.put("message", "Playlist created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 3. PUT /playlists/{id}  body: {"name":"New Name"}
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePlaylist(@PathVariable int id,
                                                              @RequestBody Map<String, String> body) {
        if (!playlists.containsKey(id)) {
            return error(HttpStatus.NOT_FOUND, "Playlist with id " + id + " not found");
        }
        String name = body.get("name");
        if (name == null || name.isBlank()) {
            return error(HttpStatus.BAD_REQUEST, "Playlist name is required");
        }

        playlists.put(id, name);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", id);
        response.put("name", name);
        return ResponseEntity.ok(response);
    }

    // 4. DELETE /playlists/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePlaylist(@PathVariable int id) {
        if (playlists.remove(id) == null) {
            return error(HttpStatus.NOT_FOUND, "Playlist with id " + id + " not found");
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", id);
        response.put("message", "Playlist " + id + " deleted successfully");
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", message);
        return ResponseEntity.status(status).body(response);
    }
}
