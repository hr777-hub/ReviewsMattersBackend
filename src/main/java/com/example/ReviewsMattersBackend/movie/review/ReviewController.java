package com.example.ReviewsMattersBackend.movie.review;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveReview(@RequestBody ReviewDto reviewDto){
        reviewService.save(reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsForMovie(@PathVariable Long movieId){
        return status(HttpStatus.OK).body(reviewService.getAllReviewsForMovie(movieId));
    }
}
