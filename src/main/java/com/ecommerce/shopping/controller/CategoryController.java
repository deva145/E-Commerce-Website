package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.CategoryRequestDto;
import com.ecommerce.shopping.dto.CategoryResponseDto;
import com.ecommerce.shopping.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryRequestDto));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDto>> fetchAllCategories(){
        return ResponseEntity.ok(categoryService.fetchAllCategories());
    }
}
