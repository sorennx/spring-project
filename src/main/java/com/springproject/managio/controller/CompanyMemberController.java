package com.springproject.managio.controller;

import com.springproject.managio.dto.CreateCompanyMemberDTO;
import com.springproject.managio.dto.CompanyMemberDTO;
import com.springproject.managio.model.CompanyMember;
import com.springproject.managio.service.CompanyMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/members")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class CompanyMemberController {
    @Autowired
    private CompanyMemberService companyMemberService;

    @GetMapping
    public ResponseEntity<List<CompanyMemberDTO>> getAllUsers() {
        List<CompanyMember> members = companyMemberService.getAllMembers();
        List<CompanyMemberDTO> companyMemberDTOs = members.stream()
                .map(user -> new CompanyMemberDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getBirthdate(), user.getSalary()))
                .toList();
        return ResponseEntity.ok(companyMemberDTOs);
    }


    @PostMapping
    public ResponseEntity<Void> addMember(@Valid @RequestBody CreateCompanyMemberDTO createCompanyMemberDTO) {
        companyMemberService.createMember(createCompanyMemberDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Integer userId) {
        companyMemberService.removeMember(userId);
        return ResponseEntity.ok().build();
    }
}
