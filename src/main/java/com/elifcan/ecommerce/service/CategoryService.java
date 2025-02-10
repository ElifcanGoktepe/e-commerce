package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.AddCategoryRequestDto;
import com.elifcan.ecommerce.entity.Category;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(AddCategoryRequestDto dto) {
        Boolean isExist = categoryRepository.existsByNameAndParentId(dto.name(), dto.parentId());
        if (isExist) throw new ECommerceException(ErrorType.ALREADY_EXISTS);
        Category category = Category.builder()
                .name(dto.name())
                .parentId(dto.parentId())
                .build();
        categoryRepository.save(category);
        return category;
    }
}
