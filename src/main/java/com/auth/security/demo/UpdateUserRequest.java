package com.auth.security.demo;

import com.auth.security.user.modal.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "Firstname field is required!")
    private String firstname;
    @NotBlank(message = "Lastname field is required!")
    private String lastname;
    @Email(message = "This must be valid email address")
    private String email;
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
    private Role role;
}
