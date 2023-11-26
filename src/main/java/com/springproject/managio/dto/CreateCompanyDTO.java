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
public class CreateCompanyDTO {
  private String name;
  private String address;
  private String owner;
  private String description;
}
