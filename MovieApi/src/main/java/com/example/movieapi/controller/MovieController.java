package com.example.movieapi.controller;

import com.example.movieapi.dto.MovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Handles both GET and POST on /movies.
 *
 * @CrossOrigin lets a React app on http://localhost:3000 call this controller.
 * Spring answers the browser's preflight (OPTIONS) request automatically.
 */
@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    // In-memory storage so movies added with POST show up in later GET calls
    private final List<String> movies = new CopyOnWriteArrayList<>(
            List.of("Inception", "The Dark Knight", "Interstellar"));

    // GET /movies -> ["Inception","The Dark Knight","Interstellar", ...]
    @GetMapping
    public List<String> getMovies() {
        log.info("GET /movies -> returning {} movies", movies.size());
        return movies;
    }

    // POST /movies  body: {"title":"Parasite"}
    @PostMapping
    public ResponseEntity<Map<String, Object>> addMovie(@RequestBody MovieRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (request == null || request.title() == null || request.title().isBlank()) {
            log.warn("POST /movies rejected: title is missing or blank");
            response.put("error", "Movie title is required");
            return ResponseEntity.badRequest().body(response);
        }

        movies.add(request.title());
        log.info("POST /movies -> received new movie: '{}' (total movies: {})",
                request.title(), movies.size());

        response.put("message", "Movie added successfully");
        response.put("title", request.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
