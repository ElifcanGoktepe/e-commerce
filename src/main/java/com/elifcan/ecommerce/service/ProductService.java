package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.AddProductRequestDto;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.mapper.ProductMapper;
import com.elifcan.ecommerce.repository.ProductRepository;
import com.elifcan.ecommerce.view.VwProductList;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final GenericResponseService responseBuilder;

    public void addProduct(AddProductRequestDto dto) {
//        Product product = Product.builder()
//                        .name(dto.name())
//                        .categoryId(dto.categoryId())
//                        .categoryName(dto.categoryName())
//                        .image(dto.image())
//                        .price(dto.price())
//                        .stock(dto.stock())
//                        .vat(dto.vat())
//                        .unit(dto.unit())
//                        .criticalStock(dto.criticalStock())
//                .build();
        productRepository.save(ProductMapper.INSTANCE.createProductObjectFromDto(dto));
    }

    public Optional<Product> findOptionalByProductId(Long productId) {
        return productRepository.findById(productId);
    }

    public List<VwProductList> getVwProductList() {
        return productRepository.getProductList();
    }

    public List<Product> findByName(String productName) {
        return productRepository.findAllByNameContaining(productName);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public void stockDecrease(Long productId, Integer quantity){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) throw new ECommerceException(ErrorType.PRODUCT_NOT_EXIST);
        Product product = productOptional.get();
        product.setStock(product.getStock() - quantity);
        productRepository.save(product); // update
    }


    public Optional<Product> findOptionalById(Long id) {
        return productRepository.findById(id);
    }
}
