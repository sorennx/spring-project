package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.springproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
