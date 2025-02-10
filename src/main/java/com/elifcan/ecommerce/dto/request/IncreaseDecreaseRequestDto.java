package com.elifcan.ecommerce.dto.request;

import com.elifcan.ecommerce.utility.enums.CartChange;

public record IncreaseDecreaseRequestDto(
        Long userId,
        Long productId,
        CartChange change
) {
}
