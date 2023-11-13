package com.springproject.managio.dto;

import com.springproject.managio.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
}
