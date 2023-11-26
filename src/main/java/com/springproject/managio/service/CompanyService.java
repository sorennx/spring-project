package com.springproject.managio.service;

import com.springproject.managio.dto.CompanyDTO;
import com.springproject.managio.dto.CreateCompanyDTO;
import com.springproject.managio.dto.UserDTO;
import com.springproject.managio.exception.CompanyAlreadyExistsException;
import com.springproject.managio.model.Company;
import com.springproject.managio.model.User;
import com.springproject.managio.permission.Role;
import com.springproject.managio.repository.CompanyRepository;
import com.springproject.managio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;


    public Company createCompany(CreateCompanyDTO companyDTO) {
        if (companyRepository.existsByName(companyDTO.getName())) {
            throw new CompanyAlreadyExistsException("{\"error\":\"Company with this name already exists\"}");
        }

        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        company.setOwner(companyDTO.getOwner());
        company.setDescription(companyDTO.getDescription());
        return companyRepository.save(company);
    }


    public Company updateCompany(Integer id, CreateCompanyDTO companyDTO) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setName(companyDTO.getName());
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    public Company getCompany(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }
}
