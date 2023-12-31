package com.springproject.managio.dto;

import com.springproject.managio.permission.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

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
    @Size(min = 8, max = 100)
    private String password;

    private Role role;
}
