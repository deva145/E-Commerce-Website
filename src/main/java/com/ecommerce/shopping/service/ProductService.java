package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.ProductRequestDto;
import com.ecommerce.shopping.dto.ProductResponseDto;
import com.ecommerce.shopping.model.Category;
import com.ecommerce.shopping.model.Product;
import com.ecommerce.shopping.repository.CategoryRepository;
import com.ecommerce.shopping.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    public Page<ProductResponseDto> fetchAllProducts(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        log.info("Fetching all products");
        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName(),
                product.getImageUrl()
        ));
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Category category = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(()->new RuntimeException("Category not found"));

        Product product = new Product();

        String categoryName = category.getName();

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setCategory(category);
        product.setImageUrl(productRequestDto.getImageUrl());

        Product savedProduct = productRepository.save(product);
        log.info("Product saved in Database: {}", productRequestDto.getName());

        return new ProductResponseDto(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                categoryName,
                savedProduct.getImageUrl()
        );

    }

    public ProductResponseDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found with Id: {}", id);
            return new RuntimeException("Product not found");
        });

        String categoryName = product.getCategory().getName();

        log.info("Product found in Database with Id: {}", id);

        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), categoryName, product.getImageUrl());
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product savedProduct = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found with ID: {}", id);
            return new RuntimeException("Product not found");
        });

        Category category = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));

        String categoryName = category.getName();

        savedProduct.setName(productRequestDto.getName());
        savedProduct.setDescription(productRequestDto.getDescription());
        savedProduct.setPrice(productRequestDto.getPrice());
        savedProduct.setStock(productRequestDto.getStock());
        savedProduct.setCategory(category);
        savedProduct.setImageUrl(productRequestDto.getImageUrl());

        productRepository.save(savedProduct);
        log.info("Product updated with ID: {}", id);

        return new ProductResponseDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice(), savedProduct.getStock(), categoryName, savedProduct.getImageUrl());

    }

    public void deleteProduct(Long id){

        if(!productRepository.existsById(id)){

            log.error("Product not found with ID: {}", id);

            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);

        log.info("Product deleted with ID: {}", id);
    }
}
