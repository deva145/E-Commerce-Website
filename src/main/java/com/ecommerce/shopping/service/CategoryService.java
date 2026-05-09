package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.CategoryRequestDto;
import com.ecommerce.shopping.dto.CategoryResponseDto;
import com.ecommerce.shopping.model.Category;
import com.ecommerce.shopping.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto){

        Category category = new Category();

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        Category savedCategory = categoryRepository.save(category);
        log.info("Category saved in Database");

        return new CategoryResponseDto(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getDescription()
        );
    }


    public List<CategoryResponseDto> fetchAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        log.info("Category fetched from Database");

        return categories.stream().map(category -> new CategoryResponseDto(category.getId(), category.getName(), category.getDescription()))
                .toList();
    }
}