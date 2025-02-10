package com.elifcan.ecommerce.repository;

import com.elifcan.ecommerce.entity.ProductInCart;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductInCartRepository extends JpaRepository <ProductInCart, Long> {
    Optional<ProductInCart> findOptionalByCartIdAndProductId(Long cartId, Long productId);

    List<ProductInCart> findAllByCartId(Long cartId);
}
