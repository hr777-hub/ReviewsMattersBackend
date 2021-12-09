package com.example.ReviewsMattersBackend.movie;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {


    private MoviesService moviesService;

    @GetMapping("/all")
    public ResponseEntity<List<MoviesTileDto>> getAllMovie(){
        return status(HttpStatus.OK).body(moviesService.getAllMovie());
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return status(HttpStatus.CREATED).body(moviesService.addMovie(movie));
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long movieId){
        return status(HttpStatus.OK).body(moviesService.findMovieById(movieId));
    }
}
