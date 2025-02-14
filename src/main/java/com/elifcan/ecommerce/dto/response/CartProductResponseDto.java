package com.elifcan.ecommerce.dto.response;

import java.math.BigDecimal;

public record CartProductResponseDto(

        Long id,
        Long productId,
        String productName,
        String productImage,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice

) {
}
