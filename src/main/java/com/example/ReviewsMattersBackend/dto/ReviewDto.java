package com.example.ReviewsMattersBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private Long reviewId;
    private String review;
    private Long movieId;
    private Instant reviewDate;
    private String username;
}
