package com.springproject.managio.service;

import com.springproject.managio.dto.RegisterCompanyDto;
import com.springproject.managio.model.Company;
import com.springproject.managio.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(RegisterCompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());

        return companyRepository.save(company);
    }

}
