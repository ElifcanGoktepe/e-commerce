package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.config.JwtManager;
import com.elifcan.ecommerce.dto.request.AddToCartRequestDto;
import com.elifcan.ecommerce.dto.request.IncreaseDecreaseRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveAllProductsRequestDto;
import com.elifcan.ecommerce.dto.request.RemoveProductFromCartRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.dto.response.CartProductResponseDto;
import com.elifcan.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.elifcan.ecommerce.config.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CART)
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;
    private final JwtManager jwtManager;

    @PostMapping(ADD_TO_CART)
    public ResponseEntity<BaseResponse<Boolean>> addToCart(@RequestBody @Valid AddToCartRequestDto dto) {
        Optional<Long> optionalUserID = jwtManager.validateToken(dto.token());
        cartService.addToCart(dto, optionalUserID.get());
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
