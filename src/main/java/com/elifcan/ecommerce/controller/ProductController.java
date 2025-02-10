package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.dto.request.AddProductRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping(ADD_PRODUCT)
    public ResponseEntity<BaseResponse<Boolean>> addProduct(@RequestBody @Valid AddProductRequestDto dto) {
        productService.addProduct(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .message("Product added successfully")
                        .code(200)
                        .data(true)
                .build());
    }
}
