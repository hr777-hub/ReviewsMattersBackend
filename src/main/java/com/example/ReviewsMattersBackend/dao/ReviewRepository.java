package com.example.ReviewsMattersBackend.dao;

import com.example.ReviewsMattersBackend.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT * FROM Review r WHERE r.movie_id = ?1",
            nativeQuery = true)
    List<Review> findByMovieId(@Param("movieId") Long movieId);
}
