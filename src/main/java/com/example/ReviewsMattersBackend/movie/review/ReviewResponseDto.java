package com.example.ReviewsMattersBackend.movie.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private String review;
    private String username;
    private Instant reviewDate;
}
