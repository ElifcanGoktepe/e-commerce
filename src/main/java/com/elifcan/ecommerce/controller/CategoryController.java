package com.elifcan.ecommerce.controller;

import com.elifcan.ecommerce.dto.request.AddCategoryRequestDto;
import com.elifcan.ecommerce.dto.response.BaseResponse;
import com.elifcan.ecommerce.entity.Category;
import com.elifcan.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.elifcan.ecommerce.congif.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY)
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping(ADD_CATEGORY)
    public ResponseEntity<BaseResponse<Category>> addCategory(@RequestBody @Valid AddCategoryRequestDto dto){
        return ResponseEntity.ok(BaseResponse.<Category>builder()
                .message("Category added successfully.")
                .code(200)
                .data(categoryService.addCategory(dto))
                .build());
    }

    @GetMapping(MAIN_CATEGORY)
    public ResponseEntity<BaseResponse<List<Category>>> getMainCategories(){
        return ResponseEntity.ok(BaseResponse.<List<Category>>builder()
                        .message("Main categories listed below.")
                        .code(200)
                        .data(categoryService.getMainCategory())
                .build());
    }

    @GetMapping(SUB_CATEGORY)
    public ResponseEntity<BaseResponse<List<Category>>> getSubCategories(@PathVariable Long parentId){
        return ResponseEntity.ok(BaseResponse.<List<Category>>builder()
                        .message("Sub categories listed below.")
                        .code(200)
                        .data(categoryService.getSubCategories(parentId))
                .build());
    }
}
