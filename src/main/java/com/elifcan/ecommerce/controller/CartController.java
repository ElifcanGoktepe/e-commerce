package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.dto.request.AddToCartRequestDto;
import com.elifcan.ecommerce.dto.request.IncreaseDecreaseRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveAllProductsRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveProductFromCartRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.dto.response.CartProductResponseDto;
import com.elifcan.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CART)
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @PostMapping(ADD_TO_CART)
    public ResponseEntity<BaseResponse<Boolean>> addToCart(@RequestBody @Valid AddToCartRequestDto dto) {
        cartService.addToCart(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .code(200)
                .message("Product added to cart successfully.")
                .build());
    }

    @DeleteMapping(REMOVE_FROM_CART)
    public ResponseEntity<BaseResponse<Boolean>> removeFromCart(@RequestBody @Valid RemoveProductFromCartRequestDto dto){
        cartService.removeProductInCart(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Ürün sespetten çıkartıldı")
                .data(true)
                .build());
    }

    @DeleteMapping(CLEAR_CART)
    public ResponseEntity<BaseResponse<Boolean>> removeAllProduct(@RequestBody @Valid RemoveAllProductsRequestDto dto) {
        cartService.removeAllProducts(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Tüm Ürünler sespetten çıkartıldı")
                .data(true)
                .build());
    }

    @PostMapping(UP_DOWN_SEPET)
    public ResponseEntity<BaseResponse<Boolean>> increaseDecrease(@RequestBody @Valid IncreaseDecreaseRequestDto dto){
        cartService.increaseDecrease(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Ürün sepet değişikliği yapıldı")
                .data(true)
                .build());
    }


    @GetMapping("/get-all-sepet/{userId}")
    public ResponseEntity<BaseResponse<List<CartProductResponseDto>>> getAllCart(@PathVariable Long userId){
        return ResponseEntity.ok(BaseResponse.<List<CartProductResponseDto>>builder()
                .code(200)
                .message("Cart shown.")
                .data(cartService.getAllCart(userId))
                .build());
    }

}
