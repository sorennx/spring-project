package com.springproject.managio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Integer id;
    private String name;
    private String address;
    private UserDTO owner;
    private String description;
    private List<CompanyMemberDTO> members;
}
