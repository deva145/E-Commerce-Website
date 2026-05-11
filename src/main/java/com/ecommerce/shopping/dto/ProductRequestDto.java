package com.ecommerce.shopping.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description should not be Blank")
    private String description;

    @Positive(message = "Price should be greater than zero")
    private double price;

    @Min(value = 0, message = "Stock value cannot be negative")
    private int stock;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "upload Image url")
    private String imageUrl;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, String description, double price, int stock, Long categoryId, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }


}
