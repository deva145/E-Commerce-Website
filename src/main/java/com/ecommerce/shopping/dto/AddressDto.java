package com.ecommerce.shopping.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String state;
    private String country;
    private String zipcode;

    public AddressDto(String street, String state, String country, String zipcode) {
        this.street = street;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }
}
