package com.cisco.phoneapp.restapp.controllers;

import com.cisco.phoneapp.restapp.dto.UserDTO;
import com.cisco.phoneapp.restapp.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    public static User mapToUser(UserDTO userDto) {
        return new User(
        userDto.getUserId(),
        userDto.getUserName(),
        passwordEncoder.encode(userDto.getPassword()),
        userDto.getEmailAddress(),
        userDto.getPreferredPhoneNumber()
        );
    }

    public static UserDTO mapToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword("******");
        userDto.setEmailAddress(user.getEmailAddress());
        userDto.setPreferredPhoneNumber(user.getPreferredPhoneNumber());
        return userDto;
    }

}
