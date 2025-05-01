package com.elifcan.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequestDto(
        @NotNull
        @Min(0)
        String token,
        @NotNull
        @Min(0)
        Long productId
) {
}
