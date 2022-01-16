package com.example.nelio.services;

import com.example.nelio.dto.MovieDTO;
import com.example.nelio.dto.ScoreDTO;
import com.example.nelio.entities.Movie;
import com.example.nelio.entities.Score;
import com.example.nelio.entities.User;
import com.example.nelio.repositories.MovieRepository;
import com.example.nelio.repositories.ScoreRepository;
import com.example.nelio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).get();

        Score score = new Score();
        score.setUser(user);
        score.setMovie(movie);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        double avg = movie.getScores().stream()
                .mapToDouble(Score::getValue)
                .average().orElse(Double.NaN);

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.saveAndFlush(movie);

        return new MovieDTO(movie);
    }
}
