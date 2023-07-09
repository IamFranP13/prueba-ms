package com.fran.pruebams.presentation.controller;

import com.fran.pruebams.application.dto.UserDto;
import com.fran.pruebams.application.mapper.UserCsvConverter;
import com.fran.pruebams.application.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserCsvConverter userCsvConverter;

    public UserController(UserService userService, UserCsvConverter userCsvConverter) {
        this.userService = userService;
        this.userCsvConverter = userCsvConverter;
    }

    @GetMapping
    public void getAllUsers(HttpServletResponse response) throws IOException {
        List<UserDto> users = userService.getAllUsers();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv");

        try (PrintWriter writer = response.getWriter()) {
            userCsvConverter.convertUsersToCsv(users, writer);
        }
    }
}