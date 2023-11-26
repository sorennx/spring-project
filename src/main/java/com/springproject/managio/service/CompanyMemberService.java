package com.springproject.managio.service;

import com.springproject.managio.dto.CreateCompanyMemberDTO;
import com.springproject.managio.model.Company;
import com.springproject.managio.model.CompanyMember;
import com.springproject.managio.model.User;
import com.springproject.managio.repository.CompanyMemberRepository;
import com.springproject.managio.repository.CompanyRepository;
import com.springproject.managio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyMemberService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMemberRepository companyMemberRepository;
    @Autowired
    private UserRepository userRepository;

    public User getOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Company getOwnerCompany() {
        return getOwner().getCompany();
    }

    public void createMember(CreateCompanyMemberDTO createCompanyMemberDTO) {
        Company company = getOwnerCompany();

        if (company != null) {
            CompanyMember companyMember = new CompanyMember();
            companyMember.setCompany(company);
            companyMember.setFirstname(createCompanyMemberDTO.getFirstname());
            companyMember.setLastname(createCompanyMemberDTO.getLastname());
            companyMember.setEmail(createCompanyMemberDTO.getEmail());
            companyMember.setBirthdate(createCompanyMemberDTO.getBirthdate());
            companyMember.setSalary(createCompanyMemberDTO.getSalary());

            companyMemberRepository.save(companyMember);
            company.getMembers().add(companyMember);
            companyRepository.save(company);
        }
    }

    public void removeMember(Integer userId) {
        Company company = getOwnerCompany();

        if (company != null) {
            List<CompanyMember> companyMembers = company.getMembers();
            CompanyMember companyMember = companyMembers.stream()
                    .filter(member -> member.getId().equals(userId))
                    .findFirst()
                    .orElse(null);

            if (companyMember != null) {
                companyMembers.remove(companyMember);
                companyRepository.save(company);
                companyMemberRepository.delete(companyMember);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company member not found"
                );
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company not found"
            );
        }
    }

    public List<CompanyMember> getAllMembers() {
        return new ArrayList<>(getOwnerCompany().getMembers());
    }

}
