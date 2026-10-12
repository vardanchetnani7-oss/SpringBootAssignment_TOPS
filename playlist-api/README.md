# Playlist API

Spring Boot 3.3 REST API (Java 17, Maven) with in-memory playlist storage.

## Run
    mvn spring-boot:run

## Endpoints
| Method | Path             | Body               |
|--------|------------------|--------------------|
| GET    | /playlists       | -                  |
| POST   | /playlists       | {"name":"Focus"}   |
| PUT    | /playlists/{id}  | {"name":"New"}     |
| DELETE | /playlists/{id}  | -                  |

## Try it
    curl localhost:8080/playlists
    curl -X POST localhost:8080/playlists -H "Content-Type: application/json" -d '{"name":"Focus"}'
    curl -X PUT localhost:8080/playlists/1 -H "Content-Type: application/json" -d '{"name":"Calm Vibes"}'
    curl -X DELETE localhost:8080/playlists/1
