package com.springproject.managio.service;

import com.springproject.managio.dto.CreateCompanyDTO;
import com.springproject.managio.exception.CompanyAlreadyExistsException;
import com.springproject.managio.model.Company;
import com.springproject.managio.model.User;
import com.springproject.managio.repository.CompanyRepository;
import com.springproject.managio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    public User getOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Company getOwnerCompany() {
        return getOwner().getCompany();
    }

    public Company createCompany(CreateCompanyDTO companyDTO) {
        if (companyRepository.existsByName(companyDTO.getName())) {
            throw new CompanyAlreadyExistsException("{\"error\":\"Company with this name already exists\"}");
        }

        // Set the company user by JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        company.setOwner(user);
        company.setDescription(companyDTO.getDescription());

        user.setCompany(company);
        return companyRepository.save(company);
    }


    public Company updateCompany(CreateCompanyDTO companyDTO) {
        Company company = getOwnerCompany();
        if (company != null) {
            company.setName(companyDTO.getName());
            company.setAddress(companyDTO.getAddress());
            company.setDescription(companyDTO.getDescription());
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany() {

        companyRepository.deleteById(getOwnerCompany().getId());
    }

    public Company getCompany() {
        return companyRepository.findById(getOwnerCompany().getId()).orElse(null);
    }
}
