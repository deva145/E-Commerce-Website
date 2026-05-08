package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.ProductRequestDto;
import com.ecommerce.shopping.dto.ProductResponseDto;
import com.ecommerce.shopping.model.Product;
import com.ecommerce.shopping.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> fetchAllProducts(ProductRequestDto productRequestDto){
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
        P
    }
}
