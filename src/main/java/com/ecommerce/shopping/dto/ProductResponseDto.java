package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String categoryName;
    private String imageUrl;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Long id, String name, String description, double price, int stock, String categoryName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }
}
