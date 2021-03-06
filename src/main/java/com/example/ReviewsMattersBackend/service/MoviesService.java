package com.example.ReviewsMattersBackend.service;

import com.example.ReviewsMattersBackend.entities.Movie;
import com.example.ReviewsMattersBackend.mapper.MovieMapper;
import com.example.ReviewsMattersBackend.dao.MovieRepository;
import com.example.ReviewsMattersBackend.dto.MoviesTileDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MoviesService {

    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long movieId){
          return movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException("movie with id "+movieId+" not found"));
    }

    public List<MoviesTileDto> getAllMovie(){
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
