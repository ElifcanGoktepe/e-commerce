package com.elifcan.ecommerce.dto.request;

import com.elifcan.ecommerce.utility.enums.Units;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddProductRequestDto(
        @NotNull
        Long categoryId,
        @NotNull
        String categoryName,
        @NotNull
        String name,
        @NotNull
        String image,
        @NotNull
        BigDecimal price,
        Integer stock,
        @NotNull
        Integer vat,
        Units unit,
        @NotNull
        Integer criticalStock
) {
}
