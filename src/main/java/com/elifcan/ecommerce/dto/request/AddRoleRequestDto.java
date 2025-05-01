package com.elifcan.ecommerce.dto.request;

public record AddRoleRequestDto(
        String roleName,
        Long userId
) {
}