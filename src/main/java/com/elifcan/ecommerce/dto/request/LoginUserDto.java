package com.elifcan.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginUserDto(
        @Email
        @NotEmpty
        @NotNull
        String email,
        @NotEmpty
        @NotNull
        @Size(min = 8,max = 128)
        String password
) {
}
