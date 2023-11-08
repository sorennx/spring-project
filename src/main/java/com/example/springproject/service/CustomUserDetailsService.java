package com.example.springproject.service;

import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    User user = userRepository
      .findByEmail(email)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            "User not found with username or email: " + email
          )
      );

    Set<GrantedAuthority> authorities = user
      .getRoles()
      .stream()
      .map(role -> new SimpleGrantedAuthority(role.getName()))
      .collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPassword(),
      authorities
    );
  }
}
