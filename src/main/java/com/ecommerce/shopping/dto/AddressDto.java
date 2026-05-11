package com.ecommerce.shopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "state is required")
    private String state;
    @NotBlank(message = "country is required")
    private String country;
    @NotBlank(message = "ZipCode is required")
    private String zipcode;

    public AddressDto(String street, String state, String country, String zipcode) {
        this.street = street;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }
}
