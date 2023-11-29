package com.springproject.managio.repository;

import com.springproject.managio.model.CompanyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyMemberRepository extends JpaRepository<CompanyMember, Integer> {

}
