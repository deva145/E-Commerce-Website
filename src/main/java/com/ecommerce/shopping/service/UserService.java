package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.AddressDto;
import com.ecommerce.shopping.dto.UserRequestDto;
import com.ecommerce.shopping.dto.UserResponseDto;
import com.ecommerce.shopping.model.Address;
import com.ecommerce.shopping.model.User;
import com.ecommerce.shopping.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponseDto> fetchAllUser(){
        List<User> users = userRepository.findAll();

    return users.stream()
                .map(user ->{Address address = user.getAddress();
                AddressDto addressDto = new AddressDto(
                        address.getStreet(),
                        address.getState(),
                        address.getCountry(),
                        address.getZipcode()
                );
                    return new UserResponseDto(
                            user.getFirstName(),
                            user.getEmail(),
                            addressDto);
                })
            .toList();
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        Address address = new Address();
        address.setStreet(userRequestDto.getAddress().getStreet());
        address.setState(userRequestDto.getAddress().getState());
        address.setCountry(userRequestDto.getAddress().getCountry());
        address.setZipcode(userRequestDto.getAddress().getZipcode());

        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setAddress(address);

        User savedUser = userRepository.save(user);

        AddressDto addressDto = new AddressDto(
                savedUser.getAddress().getStreet(),
                savedUser.getAddress().getState(),
                savedUser.getAddress().getCountry(),
                savedUser.getAddress().getZipcode()
        );



        return new UserResponseDto(savedUser.getFirstName(), savedUser.getEmail(), addressDto);
    }

    private  UserResponseDto getUserBYId(Long id, UserRequestDto userRequestDto) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not found in this id:"+id));

        AddressDto addressDto = new AddressDto(
                user.getAddress().getStreet(),
                user.getAddress().getState(),
                user.getAddress().getCountry(),
                user.getAddress().getZipcode()
        );

        return new UserResponseDto(user.getFirstName(), user.getEmail(), addressDto);
    }


    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User updatedUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found in this id:"+id));

        Address address = new Address();
        address.setState(userRequestDto.getAddress().getState());
        address.setStreet(userRequestDto.getAddress().getStreet());
        address.setCountry(userRequestDto.getAddress().getCountry());
        address.setZipcode(userRequestDto.getAddress().getZipcode());

        updatedUser.setFirstName(userRequestDto.getFirstName());
        updatedUser.setLastName(userRequestDto.getLastName());
        updatedUser.setEmail(userRequestDto.getEmail());
        updatedUser.setAddress(address);
        updatedUser.setPhoneNumber(userRequestDto.getPhoneNumber());

        AddressDto addressDto = new AddressDto(
                updatedUser.getAddress().getStreet(),
                updatedUser.getAddress().getState(),
                updatedUser.getAddress().getCountry(),
                updatedUser.getAddress().getZipcode()
        );

        User savedUser = userRepository.save(updatedUser);

        return new UserResponseDto(savedUser.getFirstName(),
                savedUser.getEmail(),
                addressDto);

    }


    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "User Deleted Successfully from id:" + id;
    }
}
