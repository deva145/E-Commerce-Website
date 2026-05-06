package com.ecommerce.shopping.service;

import com.ecommerce.shopping.dto.AddressDto;
import com.ecommerce.shopping.dto.UserRequestDto;
import com.ecommerce.shopping.dto.UserResponseDto;
import com.ecommerce.shopping.model.Address;
import com.ecommerce.shopping.model.User;
import com.ecommerce.shopping.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponseDto> fetchAllUser(){

        log.info("Fetching all Users from Database");

        List<User> users = userRepository.findAll();

        log.info("Total users fetched: {}", users.size());

    return users.stream()
                .map(user ->{Address address = user.getAddress();

                    log.debug("Mapping user with email: {}", user.getEmail());

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

        log.info("Creating user with email: {}", user.getEmail());
        User savedUser = userRepository.save(user);
        log.info("User added successfully in DataBase with ID: {}", savedUser.getId());

        AddressDto addressDto = new AddressDto(
                savedUser.getAddress().getStreet(),
                savedUser.getAddress().getState(),
                savedUser.getAddress().getCountry(),
                savedUser.getAddress().getZipcode()
        );



        return new UserResponseDto(savedUser.getFirstName(), savedUser.getEmail(), addressDto);
    }

    public UserResponseDto getUserBYId(Long id) {

        log.info("Fetching user by ID: {}", id);

        User user = userRepository.findById(id).orElseThrow(()->{
            log.error("User not found with ID: {}", id);
            return new RuntimeException("User not found");
        });

        AddressDto addressDto = new AddressDto(
                user.getAddress().getStreet(),
                user.getAddress().getState(),
                user.getAddress().getCountry(),
                user.getAddress().getZipcode()
        );

        log.info("User fetched successfully with ID: {}", id);
        return new UserResponseDto(user.getFirstName(), user.getEmail(), addressDto);
    }


    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {

        log.info("Fetching the existing user data from id: {}", id);
        User updatedUser = userRepository.findById(id).orElseThrow(()-> {
            log.error("User not found with ID: {}", id);
        return new RuntimeException("User not found");
        });

        Address address = new Address();
        address.setState(userRequestDto.getAddress().getState());
        address.setStreet(userRequestDto.getAddress().getStreet());
        address.setCountry(userRequestDto.getAddress().getCountry());
        address.setZipcode(userRequestDto.getAddress().getZipcode());

        log.info("Updating old user with new data for id: {}", id);
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
        log.info("User updated successfully for ID: {}", id);
        log.info("Updated user stored successfully in database with ID: {}", savedUser.getId());

        return new UserResponseDto(savedUser.getFirstName(),
                savedUser.getEmail(),
                addressDto);

    }


    public String deleteUserById(Long id) {
        if (!userRepository.existsById(id)){
            log.error("User not found with ID: {}", id);
            throw new RuntimeException("User not found with id"+id);        }
        else {
            userRepository.deleteById(id);
            log.info("User deleted successfully with ID: {}", id);
            return "User Deleted Successfully from id:" + id;
        }
    }
}
