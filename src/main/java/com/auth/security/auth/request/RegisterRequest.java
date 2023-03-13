package com.auth.security.auth.request;

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
public class RegisterRequest {
  @NotNull
  private String firstname;
  @NotNull
  private String lastname;
  @Email
  private String email;
  @Size(min = 2, max = 12)
  private String password;
}
