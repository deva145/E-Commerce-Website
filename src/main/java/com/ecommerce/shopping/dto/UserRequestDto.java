package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Address;
import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
}
