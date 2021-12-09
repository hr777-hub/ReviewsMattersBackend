package com.example.ReviewsMattersBackend.movie.review;

import com.example.ReviewsMattersBackend.auth.AuthService;
import com.example.ReviewsMattersBackend.movie.Movie;
import com.example.ReviewsMattersBackend.movie.MovieRepository;
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
