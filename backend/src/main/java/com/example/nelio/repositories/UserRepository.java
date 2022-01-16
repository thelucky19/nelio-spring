package com.example.nelio.repositories;

import com.example.nelio.entities.Movie;
import com.example.nelio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
