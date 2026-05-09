package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.ProductRequestDto;
import com.ecommerce.shopping.dto.ProductResponseDto;
import com.ecommerce.shopping.model.Product;
import com.ecommerce.shopping.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> fetchAllProducts(){

        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getImageUrl()
        )).toList();
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setCategory(productRequestDto.getCategory());
        product.setImageUrl(productRequestDto.getImageUrl());

        Product savedProduct = productRepository.save(product);
        log.info("Product saved in Database: {}", productRequestDto.getName());

        return new ProductResponseDto(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock(),
                savedProduct.getCategory(),
                savedProduct.getImageUrl()
        );

    }

    public ProductResponseDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found with Id: {}", id);
            return new RuntimeException("Product not found");
        });
        log.info("Product found in Database with Id: {}", id);
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getCategory(), product.getImageUrl());
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product savedProduct = productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found with ID: {}", id);
            return new RuntimeException("Product not found");
        });
        savedProduct.setName(productRequestDto.getName());
        savedProduct.setDescription(productRequestDto.getDescription());
        savedProduct.setPrice(productRequestDto.getPrice());
        savedProduct.setStock(productRequestDto.getStock());
        savedProduct.setCategory(productRequestDto.getCategory());
        savedProduct.setImageUrl(productRequestDto.getImageUrl());

        productRepository.save(savedProduct);
        log.info("Product updated with ID: {}", id);

        return new ProductResponseDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice(), savedProduct.getStock(), savedProduct.getCategory(), savedProduct.getImageUrl());

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
