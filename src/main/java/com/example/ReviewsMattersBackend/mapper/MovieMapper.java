package com.example.ReviewsMattersBackend.mapper;

import com.example.ReviewsMattersBackend.entities.Movie;
import com.example.ReviewsMattersBackend.dto.MoviesTileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "movieId", expression = "java(movie.getMovieId())")
    @Mapping(target = "movieName", expression = "java(movie.getMovieName())")
    @Mapping(target = "image", expression = "java(movie.getImage())")
    MoviesTileDto mapToDto(Movie movie);
}
