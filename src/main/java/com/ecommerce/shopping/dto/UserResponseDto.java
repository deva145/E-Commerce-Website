package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Address;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String email;
    private AddressDto address;


    public UserResponseDto(String firstName, String email, AddressDto address) {
        this.firstName = firstName;
        this.email = email;
        this.address = address;
    }
}
