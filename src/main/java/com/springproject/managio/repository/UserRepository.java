package com.springproject.managio.repository;

import java.util.Optional;

import com.springproject.managio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
