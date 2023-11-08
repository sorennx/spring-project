package com.example.springproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);
}
