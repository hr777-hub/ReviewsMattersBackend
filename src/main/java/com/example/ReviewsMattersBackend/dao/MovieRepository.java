package com.example.ReviewsMattersBackend.dao;

import com.example.ReviewsMattersBackend.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
