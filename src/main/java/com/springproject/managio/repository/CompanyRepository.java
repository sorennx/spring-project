package com.springproject.managio.repository;

import com.springproject.managio.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);
}
