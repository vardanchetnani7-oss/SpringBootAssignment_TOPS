# SecureSongApi

Spring Boot 3.3 (Java 17, Maven) with Spring Security Basic Auth and bean validation.

Run:  mvn spring-boot:run

Credentials (hardcoded in SecurityConfig, for learning only):
    username: admin
    password: admin123

## Try it
    # 401 - no credentials
    curl -i -X POST localhost:8080/addPlaylist -H "Content-Type: application/json" -d '{"name":"Chill Vibes"}'

    # 201 - playlist added
    curl -u admin:admin123 -X POST localhost:8080/addPlaylist -H "Content-Type: application/json" -d '{"name":"Chill Vibes"}'

    # 201 - valid song
    curl -u admin:admin123 -X POST localhost:8080/addSong -H "Content-Type: application/json" \
         -d '{"title":"Blinding Lights","artist":"The Weeknd","duration":200}'

    # 400 - every field invalid
    curl -u admin:admin123 -X POST localhost:8080/addSong -H "Content-Type: application/json" \
         -d '{"title":"","duration":10}'

Expected 400 body:
    {
      "status": 400,
      "error": "Validation failed",
      "message": "One or more fields are missing or invalid",
      "errors": {
        "title": "Title is required and cannot be empty",
        "artist": "Artist is required and cannot be empty",
        "duration": "Duration must be at least 30 seconds"
      }
    }

(The order of keys inside "errors" may vary.)

## Security config notes
- Every endpoint requires Basic Auth. To protect only /addPlaylist, see the
  comment at the top of config/SecurityConfig.java.
- CSRF is disabled because the API is stateless and uses Basic Auth.
- Passwords are BCrypt-hashed in memory; move users and secrets out of source
  code (database / environment variables) before any real deployment.
