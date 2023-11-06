package com.example.springproject.controller;

import com.example.springproject.dto.LoginDTO;
import com.example.springproject.dto.RegisterDTO;
import com.example.springproject.model.Role;
import com.example.springproject.model.User;
import com.example.springproject.repository.RoleRepository;
import com.example.springproject.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<String> authenticateUser(
    @RequestBody LoginDTO loginDto
  ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginDto.getUsernameOrEmail(),
        loginDto.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
    // add check for username exists in a DB
    if (userRepository.existsByUsername(registerDTO.getUsername())) {
      return new ResponseEntity<>(
        "Username is already taken!",
        HttpStatus.BAD_REQUEST
      );
    }

    // add check for email exists in DB
    if (userRepository.existsByEmail(registerDTO.getEmail())) {
      return new ResponseEntity<>(
        "Email is already taken!",
        HttpStatus.BAD_REQUEST
      );
    }

    // create user object
    User user = new User();
    user.setName(registerDTO.getName());
    user.setUsername(registerDTO.getUsername());
    user.setEmail(registerDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

    Role roles = roleRepository.findByName("ROLE_ADMIN").get();
    user.setRoles(Collections.singleton(roles));

    userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }
}
