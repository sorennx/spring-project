package com.springproject.managio.controller;

import com.springproject.managio.dto.CompanyDTO;
import com.springproject.managio.dto.CompanyMemberDTO;
import com.springproject.managio.dto.CreateCompanyDTO;
import com.springproject.managio.dto.UserDTO;
import com.springproject.managio.model.Company;
import com.springproject.managio.model.CompanyMember;
import com.springproject.managio.model.User;
import com.springproject.managio.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    private UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole().toString());
    }

    private CompanyMemberDTO convertCompanyMemberToDTO(CompanyMember companyMember) {
        return new CompanyMemberDTO(companyMember.getId(), companyMember.getFirstname(), companyMember.getLastname(), companyMember.getEmail(), companyMember.getBirthdate(), companyMember.getSalary());
    }

    private List<CompanyMemberDTO> convertToDTOs(List<CompanyMember> companyMembers) {
        return companyMembers.stream()
                .map(this::convertCompanyMemberToDTO)
                .collect(Collectors.toList());
    }

    private CompanyDTO convertToDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getName(), company.getAddress(), convertUserToDTO(company.getOwner()), company.getDescription(), convertToDTOs(company.getMembers()));
    }


    @GetMapping
    public ResponseEntity<CompanyDTO> getCompany() {
        return ResponseEntity.ok(convertToDTO(companyService.getCompany()));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyDTO companyDTO) {
        Company savedCompany = companyService.createCompany(companyDTO);
        return ResponseEntity.ok(convertToDTO(savedCompany));
    }

    @PutMapping
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CreateCompanyDTO companyDTO) {
        Company updatedCompany = companyService.updateCompany(companyDTO);

        return updatedCompany != null ? ResponseEntity.ok(convertToDTO(updatedCompany)) : ResponseEntity.notFound().build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCompany() {
        companyService.deleteCompany();
        return ResponseEntity.ok().build();
    }
}
