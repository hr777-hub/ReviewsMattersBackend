package com.example.ReviewsMattersBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoviesTileDto {
    private Long movieId;
    private String movieName;
    private String image;
}
