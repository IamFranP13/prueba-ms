package com.fran.pruebams.presentation.controller;

import com.fran.pruebams.application.dto.UserDto;
import com.fran.pruebams.application.service.UserService;
import com.fran.pruebams.application.mapper.UserCsvConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class UserControllerTest {

    private final UserService userService = Mockito.mock(UserService.class);
    private final UserCsvConverter userCsvConverter = Mockito.mock(UserCsvConverter.class);
    private final UserController userController = new UserController(userService, userCsvConverter);

    @Test
    public void getAllUsers() throws Exception {
        UserDto user1 = new UserDto();
        user1.setFullname("Test user 1");
        UserDto user2 = new UserDto();
        user2.setFullname("Test user 2");

        List<UserDto> users = Arrays.asList(user1, user2);
        given(userService.getAllUsers()).willReturn(users);

        MockHttpServletResponse response = new MockHttpServletResponse();
        userController.getAllUsers(response);

        // Verify the status code and content type
        assertEquals(200, response.getStatus());
        assertEquals("text/csv", response.getContentType());
    }
}
