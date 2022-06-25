package com.example.ReviewsMattersBackend.service;

import com.example.ReviewsMattersBackend.dao.ReviewRepository;
import com.example.ReviewsMattersBackend.dto.ReviewDto;
import com.example.ReviewsMattersBackend.entities.Movie;
import com.example.ReviewsMattersBackend.dao.MovieRepository;
import com.example.ReviewsMattersBackend.entities.Review;
import com.example.ReviewsMattersBackend.mapper.ReviewMapper;
import com.example.ReviewsMattersBackend.dto.ReviewResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;
    private ReviewMapper reviewMapper;
    private AuthService authService;


   public void save(ReviewDto reviewDto){
       Movie movie = movieRepository.findById(reviewDto.getMovieId())
               .orElseThrow(() -> new IllegalStateException("movie with id "+reviewDto.getMovieId()+" not found"));
       Review review = reviewMapper.map(reviewDto, movie, authService.getCurrentUser());
       reviewRepository.save(review);
   }

    public List<ReviewResponseDto> getAllReviewsForMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(reviewMapper::mapToDto)
                .collect(toList());
    }
}
