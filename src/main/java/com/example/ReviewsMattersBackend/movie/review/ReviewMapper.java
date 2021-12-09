package com.example.ReviewsMattersBackend.movie.review;

import com.example.ReviewsMattersBackend.movie.Movie;
import com.example.ReviewsMattersBackend.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "review",source = "reviewDto.review")
    @Mapping(target = "reviewDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "movie", source = "movie")
    Review map(ReviewDto reviewDto, Movie movie, User user);



    @Mapping(target = "username", expression = "java(review.getUser().getUsername())")
    ReviewResponseDto mapToDto(Review review);
}
