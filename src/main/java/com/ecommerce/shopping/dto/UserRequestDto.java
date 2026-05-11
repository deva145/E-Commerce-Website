package com.ecommerce.shopping.dto;

import com.ecommerce.shopping.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "First name required")
    private String firstName;
    private String lastName;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
    @Valid
    @NotNull(message = "Address is required")
    private Address address;
}
