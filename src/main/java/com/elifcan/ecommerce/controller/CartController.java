package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.dto.request.AddToCartRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CART)
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @PostMapping(ADD_TO_CART)
    public ResponseEntity<BaseResponse<Boolean>> addToCategory(@RequestBody @Valid AddToCartRequestDto dto) {
        cartService.addToCart(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .code(200)
                .message("Product added to cart successfully.")
                .build());
    }
}
