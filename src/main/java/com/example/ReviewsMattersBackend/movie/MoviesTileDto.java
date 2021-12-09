package com.example.ReviewsMattersBackend.movie;

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
