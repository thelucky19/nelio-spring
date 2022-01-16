package com.example.nelio.repositories;

import com.example.nelio.entities.Movie;
import com.example.nelio.entities.Score;
import com.example.nelio.entities.ScorePK;
import com.example.nelio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
