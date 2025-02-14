package com.elifcan.ecommerce.repository;

import com.elifcan.ecommerce.entity.Cart;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.entity.ProductInCart;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findOptionalByUserId(Long userId);

}
