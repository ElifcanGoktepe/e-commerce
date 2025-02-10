package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.AddToCartRequestDto;
import com.elifcan.ecommerce.entity.Cart;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.entity.ProductInCart;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.repository.CartRepository;
import com.elifcan.ecommerce.repository.ProductInCartRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductInCartRepository productInCartRepository;
    private final ProductService productService;

    public void addToCart(AddToCartRequestDto dto) {
        Cart cart;
        Optional<Cart> cartOptional = cartRepository.findOptionalByUserId(dto.userId()); // checking any other cart for the user
        if (cartOptional.isEmpty()) {
            cart = Cart.builder()
                    .userId(dto.userId())
                    .build();
            cartRepository.save(cart);
        }
        else{
            cart = cartOptional.get();
        }
        Optional<Product> productOptional = productService.findOptionalByProductId(dto.productId());
        if (productOptional.isEmpty()) throw new ECommerceException(ErrorType.PRODUCT_NOT_EXIST);
        Product product = productOptional.get();
        ProductInCart productInCart = ProductInCart.builder()
                .productId(dto.productId())
                .cartId(cart.getId())
                .quantity(1)
                .unitPrice(product.getPrice())
                .totalPrice(product.getPrice())
                .build();
        productInCartRepository.save(productInCart);
    }

}
