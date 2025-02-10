package com.elifcan.ecommerce.repository;

import com.elifcan.ecommerce.entity.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInCartRepository extends JpaRepository <ProductInCart, Long> {
}
