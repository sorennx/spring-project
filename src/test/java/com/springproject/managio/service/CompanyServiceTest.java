package com.springproject.managio.service;

import com.springproject.managio.dto.RegisterCompanyDto;
import com.springproject.managio.model.Company;
import com.springproject.managio.repository.CompanyRepository;
import com.springproject.managio.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCompany() {
        RegisterCompanyDto dto = new RegisterCompanyDto();
        String COMPANY_NAME = "TEST_COMPANY";
        String COMPANY_ADDRESS = "TEST_ADDRESS";

        dto.setName(COMPANY_NAME);
        dto.setAddress(COMPANY_ADDRESS);

        Company company = new Company();
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company savedCompany = companyService.createCompany(dto);

        assertEquals(COMPANY_NAME, savedCompany.getName());
        assertEquals(COMPANY_ADDRESS, savedCompany.getAddress());
        verify(companyRepository, times(1)).save(any(Company.class));
    }
}
