package com.springproject.managio.repository;

import com.springproject.managio.model.CompanyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyMemberRepository extends JpaRepository<CompanyMember, Integer> {
    Optional<CompanyMember> findByEmail(String email);
}
