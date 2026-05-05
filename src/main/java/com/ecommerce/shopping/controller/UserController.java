package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.UserRequestDto;
import com.ecommerce.shopping.dto.UserResponseDto;
import com.ecommerce.shopping.model.User;
import com.ecommerce.shopping.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto> fetchAllUsers(){
        return userService.fetchAllUser();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserRequestDto userRequestDto){
        return userService.addUser(userRequestDto);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
}
