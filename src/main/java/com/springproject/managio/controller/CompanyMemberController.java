package com.springproject.managio.controller;

import com.springproject.managio.dto.AddCompanyMemberDTO;
import com.springproject.managio.dto.UserDTO;
import com.springproject.managio.model.User;
import com.springproject.managio.service.CompanyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/members")
public class CompanyMemberController {
    @Autowired
    private CompanyMemberService companyMemberService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@PathVariable Integer companyId) {
        List<User> users = companyMemberService.getAllUsers(companyId);
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }


    @PostMapping
    public ResponseEntity<Void> addMember(@PathVariable Integer companyId, @RequestBody AddCompanyMemberDTO addCompanyMemberDTO) {
        companyMemberService.addMember(companyId, addCompanyMemberDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Integer companyId, @PathVariable Integer userId) {
        companyMemberService.removeMember(companyId, userId);
        return ResponseEntity.ok().build();
    }
}
