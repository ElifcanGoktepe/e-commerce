package com.elifcan.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RemoveProductFromCartRequestDto(
        @NotNull
        @Min(0)
        Long userId,
        @NotNull
        @Min(0)
        Long productId
) {
}
