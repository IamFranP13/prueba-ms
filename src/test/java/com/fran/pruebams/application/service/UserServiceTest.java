package com.fran.pruebams.application.service;

import com.fran.pruebams.application.dto.UserDto;
import com.fran.pruebams.domain.model.User;
import com.fran.pruebams.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    public void getAllUsers() {
        User user1 = new User();
        user1.setFullname("Test user 1");
        User user2 = new User();
        user2.setFullname("Test user 2");

        given(userRepository.findAll()).willReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("Test user 1", users.get(0).getFullname());
        assertEquals("Test user 2", users.get(1).getFullname());
    }
}
