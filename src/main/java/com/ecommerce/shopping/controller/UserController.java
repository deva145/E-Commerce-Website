package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.UserRequestDto;
import com.ecommerce.shopping.dto.UserResponseDto;
import com.ecommerce.shopping.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> fetchAllUsers(){
        List<UserResponseDto> users = userService.fetchAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserBYId(@PathVariable Long id){
        UserResponseDto user = userService.getUserBYId(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto user = userService.addUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        UserResponseDto user = userService.updateUser(id, userRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
