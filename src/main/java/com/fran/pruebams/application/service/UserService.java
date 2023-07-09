package com.fran.pruebams.application.service;

import com.fran.pruebams.application.dto.UserDto;
import com.fran.pruebams.domain.model.User;
import com.fran.pruebams.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFullname(user.getFullname());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        return userDto;
    }
}
