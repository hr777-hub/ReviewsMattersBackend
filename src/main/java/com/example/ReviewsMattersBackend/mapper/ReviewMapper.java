package com.example.ReviewsMattersBackend.mapper;

import com.example.ReviewsMattersBackend.dto.ReviewDto;
import com.example.ReviewsMattersBackend.entities.Movie;
import com.example.ReviewsMattersBackend.entities.Review;
import com.example.ReviewsMattersBackend.entities.User;
import com.example.ReviewsMattersBackend.dto.ReviewResponseDto;
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
