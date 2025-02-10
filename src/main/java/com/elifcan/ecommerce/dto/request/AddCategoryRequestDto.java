package com.elifcan.ecommerce.dto.request;

public record AddCategoryRequestDto(
        Long parentId,
        String name
) {
}
