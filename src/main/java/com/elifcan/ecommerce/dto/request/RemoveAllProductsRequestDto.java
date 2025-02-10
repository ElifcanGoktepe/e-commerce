package com.elifcan.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RemoveAllProductsRequestDto(
        @NotNull
        @Min(1)
        Long kullaniciId
) {
}
