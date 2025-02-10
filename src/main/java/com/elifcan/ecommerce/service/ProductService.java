package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.AddProductRequestDto;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.repository.ProductRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(AddProductRequestDto dto) {
        productRepository.save(Product.builder()
                        .name(dto.name())
                        .categoryId(dto.categoryId())
                        .categoryName(dto.categoryName())
                        .image(dto.image())
                        .price(dto.price())
                        .stock(dto.stock())
                        .vat(dto.vat())
                        .unit(dto.unit())
                        .criticalStock(dto.criticalStock())
                .build());
    }

    public Optional<Product> findOptionalByProductId(Long productId) {
        return productRepository.findById(productId);
    }
}
