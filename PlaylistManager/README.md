# PlaylistManager

Spring Boot 3.3 (Java 17, Maven) + Spring Data JPA + MySQL.

## 1. Set up MySQL
Make sure MySQL is running locally, then edit
`src/main/resources/application.properties`:

    spring.datasource.username=root
    spring.datasource.password=your_password_here

The `playlistmanager` database is created automatically
(`createDatabaseIfNotExist=true`), and the `songs` table is created by
Hibernate (`ddl-auto=update`). To create the database manually instead:

    CREATE DATABASE playlistmanager;

## 2. Run
    mvn spring-boot:run

## 3. Test with curl
    # add songs
    curl -X POST localhost:8080/songs -H "Content-Type: application/json" \
         -d '{"title":"Blinding Lights","artist":"The Weeknd","duration":200}'
    curl -X POST localhost:8080/songs -H "Content-Type: application/json" \
         -d '{"title":"Starboy","artist":"The Weeknd","duration":230}'

    # get all songs
    curl localhost:8080/songs

    # get songs by artist (custom repository method)
    curl "localhost:8080/songs?artist=The%20Weeknd"

    # delete song with id 1
    curl -X DELETE localhost:8080/songs/1

## Structure
    model/Song.java                  JPA entity -> songs table
    repository/SongRepository.java   CrudRepository + findByArtist
    service/SongService.java         add / fetch all / delete
    controller/SongController.java   POST, GET, DELETE /songs
