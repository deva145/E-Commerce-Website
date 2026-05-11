package com.ecommerce.shopping.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDto {
    @NotBlank(message = "Category name is required")
    private String name;

    @NotBlank(message = "Description should not be Blank")
    private String description;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
