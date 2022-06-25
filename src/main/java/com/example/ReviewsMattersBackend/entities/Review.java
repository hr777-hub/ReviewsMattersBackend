package com.example.ReviewsMattersBackend.entities;

import com.example.ReviewsMattersBackend.entities.Movie;
import com.example.ReviewsMattersBackend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long reviewId;
    private String review;
    private Instant reviewDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id",
                 referencedColumnName = "movieId")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
                referencedColumnName = "userId")
    private User user;
}
