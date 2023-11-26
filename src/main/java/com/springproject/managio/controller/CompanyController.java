package com.springproject.managio.controller;

import com.springproject.managio.dto.CompanyDTO;
import com.springproject.managio.dto.CreateCompanyDTO;
import com.springproject.managio.dto.UserDTO;
import com.springproject.managio.model.Company;
import com.springproject.managio.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    private CompanyDTO convertToDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getName(), company.getAddress(), company.getOwner(), company.getDescription(), company.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Integer id) {
        Company company = companyService.getCompany(id);
        return ResponseEntity.ok(convertToDTO(company));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyDTO companyDTO) {
        Company savedCompany = companyService.createCompany(companyDTO);
        return ResponseEntity.ok(convertToDTO(savedCompany));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Integer id, @RequestBody CreateCompanyDTO companyDTO) {
        Company updatedCompany = companyService.updateCompany(id, companyDTO);
        if (updatedCompany != null) {
            return ResponseEntity.ok(convertToDTO(updatedCompany));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
