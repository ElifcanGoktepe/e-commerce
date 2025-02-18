package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.congif.JwtManager;
import com.elifcan.ecommerce.dto.request.AddProductRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.entity.Product;
import com.elifcan.ecommerce.exception.ECommerceException;
import com.elifcan.ecommerce.exception.ErrorType;
import com.elifcan.ecommerce.service.ProductService;
import com.elifcan.ecommerce.view.VwProductList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final JwtManager jwtManager;

    /**
     * Reading infomation in Application.yml
     *
     * For this case @Value is used
     */

    @Value("{$my-application.product-title}")
    private String productTitle;

    @GetMapping("/yml-read")
    public ResponseEntity<Void> ymlRead(){
        System.out.println("Product name : " + productTitle);
        return ResponseEntity.ok().build();
    }

    @PostMapping(ADD_PRODUCT)
    public ResponseEntity<BaseResponse<Boolean>> addProduct(@RequestBody @Valid AddProductRequestDto dto) {
        productService.addProduct(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .message("Product added successfully")
                        .code(200)
                        .data(true)
                .build());
    }

    @GetMapping(GET_ALL_PRODUCT + "/{token}")
    public ResponseEntity<BaseResponse<List<VwProductList>>> getAllProduct(@PathVariable String token) {
        Optional<Long> userId = jwtManager.validateToken(token);
        if(userId.isEmpty())
            throw new ECommerceException(ErrorType.INVALID_TOKEN);
        return ResponseEntity.ok(BaseResponse.<List<VwProductList>>builder()
                        .code(200)
                        .message("Products listed below.")
                        .data(productService.getVwProductList())
                .build());
    }

    @GetMapping(FIND_BY_ID + "/{productName}")
    public ResponseEntity<BaseResponse<List<Product>>> findByName(@PathVariable String productName){
        return ResponseEntity.ok(BaseResponse.<List<Product>>builder()
                .code(200)
                .message("Searched products listed below.")
                .data(productService.findByName(productName))
                .build());
    }

    @DeleteMapping(DELETE_PRODUCT + "/{productId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteProductById (@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Product deleted successfully.")
                .data(true)
                .build());
    }
}
