package com.example.nelio.services;

import com.example.nelio.dto.MovieDTO;
import com.example.nelio.entities.Movie;
import com.example.nelio.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable){
        Page<Movie> result = repository.findAll(pageable);
        Page<MovieDTO> pages = result.map(x -> new MovieDTO(x));
        return pages;

    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){
        Movie result = repository.findById(id).get();
        MovieDTO dto = new MovieDTO(result);
        return dto;

    }
}
