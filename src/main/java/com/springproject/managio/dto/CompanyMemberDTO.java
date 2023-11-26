package com.springproject.managio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMemberDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private Double salary;
}
