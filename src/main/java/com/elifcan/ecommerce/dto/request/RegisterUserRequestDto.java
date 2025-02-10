package com.elifcan.ecommerce.dto.request;

import jakarta.validation.constraints.*;

public record RegisterUserRequestDto(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 2, max = 50)
        String name,
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 10, max = 20)
        String telephone,
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 8, max = 128)
        String password,
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 8, max = 128)
        String rePassword
) {
}
