package com.springproject.managio.service;

import com.springproject.managio.dto.AddCompanyMemberDTO;
import com.springproject.managio.dto.UserDTO;
import com.springproject.managio.model.Company;
import com.springproject.managio.model.User;
import com.springproject.managio.permission.Role;
import com.springproject.managio.repository.CompanyRepository;
import com.springproject.managio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyMemberService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    public void addMember(Integer companyId, AddCompanyMemberDTO addCompanyMemberDTO) {
        Company company = getCompany(companyId);
        User user = userRepository.findById(addCompanyMemberDTO.getId()).orElse(null);
        if (user != null) {
            user.setRole(Role.valueOf(addCompanyMemberDTO.getRole()));
            company.getUsers().add(user);
            user.setCompany(company);
            companyRepository.save(company);
            userRepository.save(user);
        }
    }


    public void removeMember(Integer companyId, Integer userId) {
        Company company = getCompany(companyId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && company != null) {
            company.getUsers().remove(user);
            user.setCompany(null);
            companyRepository.save(company);
            userRepository.save(user);
        }
    }

    private Company getCompany(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<User> getAllUsers(Integer companyId) {
        Company company = getCompany(companyId);
        return new ArrayList<>(company.getUsers());
    }

}
