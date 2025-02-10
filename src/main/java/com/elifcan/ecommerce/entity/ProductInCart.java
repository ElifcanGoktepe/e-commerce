package com.elifcan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblprodcuts_in_cart")
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long productId;
    Long cartId;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
}
