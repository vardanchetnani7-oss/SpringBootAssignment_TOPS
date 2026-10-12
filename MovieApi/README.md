# MovieApi

Spring Boot 3.3 (Java 17, Maven). Run:  mvn spring-boot:run   (http://localhost:8080)

## Postman tests

### GET /movies
- Method: GET   URL: http://localhost:8080/movies
- Expected: 200 OK
      ["Inception","The Dark Knight","Interstellar"]

### POST /movies
- Method: POST  URL: http://localhost:8080/movies
- Body tab -> raw -> JSON
      {"title":"Parasite"}
- Expected: 201 Created
      {"message":"Movie added successfully","title":"Parasite"}
- The console running the app logs:
      POST /movies -> received new movie: 'Parasite' (total movies: 4)
- Send GET /movies again: "Parasite" is now in the list.

### Invalid POST
- Body: {"title":""}  -> 400 {"error":"Movie title is required"}

## Verify CORS (curl)
    curl -i -H "Origin: http://localhost:3000" http://localhost:8080/movies
Look for:  Access-Control-Allow-Origin: http://localhost:3000

Preflight (what the browser sends before a JSON POST):
    curl -i -X OPTIONS http://localhost:8080/movies \
      -H "Origin: http://localhost:3000" \
      -H "Access-Control-Request-Method: POST" \
      -H "Access-Control-Request-Headers: content-type"

## React usage (http://localhost:3000)
    fetch("http://localhost:8080/movies")
      .then(res => res.json())
      .then(data => console.log(data));

    fetch("http://localhost:8080/movies", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title: "Parasite" }),
    }).then(res => res.json()).then(console.log);

## Global CORS alternative
Instead of @CrossOrigin on the controller, add this bean to cover every endpoint:

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/movies/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "OPTIONS");
        }
    }

Note: CORS is enforced by browsers only. Postman and curl are never blocked
by it, so use a browser (the React app) or the curl commands above to verify it.
