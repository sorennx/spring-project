package com.springproject.managio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyMemberDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String firstname;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastname;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Past
    private Date birthdate;

    @NotNull
    @Min(0)
    private Double salary;
}
