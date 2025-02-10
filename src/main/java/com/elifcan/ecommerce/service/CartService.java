package com.elifcan.ecommerce.service;

import com.elifcan.ecommerce.dto.request.AddToCartRequestDto;
import com.elifcan.ecommerce.dto.request.IncreaseDecreaseRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveAllProductsRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveProductFromCartRequestDto;
import com.elifcan.ecommerce.entity.Cart;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.entity.ProductInCart;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.repository.CartRepository;
import com.elifcan.ecommerce.repository.ProductInCartRepository;
import com.elifcan.ecommerce.utility.enums.CartChange;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void removeProductInCart(RemoveProductFromCartRequestDto dto) {
        Long cartId = getCartIdFromUserId(dto.userId());
        Optional<ProductInCart> productInCart = productInCartRepository.findOptionalByCartIdAndProductId(cartId, dto.productId());
        if (productInCart.isEmpty()) throw new ECommerceException(ErrorType.PRODUCT_NOT_FOUND);
        productInCartRepository.delete(productInCart.get());
    }


    public void removeAllProducts(RemoveAllProductsRequestDto dto) {
        Long cartId = getCartIdFromUserId(dto.kullaniciId());
        List<ProductInCart> cartList = productInCartRepository.findAllByCartId(cartId);
        productInCartRepository.deleteAll(cartList);
    }

    public void increaseDecrease(IncreaseDecreaseRequestDto dto) {
        Long cartId = getCartIdFromUserId(dto.userId());
        Optional<ProductInCart> productInCart = productInCartRepository.findOptionalByCartIdAndProductId(cartId,dto.userId());
        if(productInCart.isEmpty()) throw new ECommerceException(ErrorType.PRODUCT_NOT_FOUND);
        ProductInCart productInCart1 = productInCart.get();
        if(dto.change().equals(CartChange.INCREASE)){ // Increase the quantity
            productInCart1.setQuantity(productInCart1.getQuantity()+1);
            productInCartRepository.save(productInCart1);
        }else { // azaltma işlemi
            // DİKKAT!! ürün adedi 2 ve üzeinde ise arttırma yap, altında ise sil
            if(productInCart1.getQuantity()>1){
                productInCart1.setQuantity(productInCart1.getQuantity()-1);
                productInCartRepository.save(productInCart1);
            }else {
                productInCartRepository.delete(productInCart1);
            }
        }
    }

    private Long getCartIdFromUserId(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findOptionalByUserId(userId);
        if(cartOptional.isEmpty()) throw new ECommerceException(ErrorType.CART_NOT_EXIST);
        Long cartId = cartOptional.get().getId();
        return cartId;
    }

}
