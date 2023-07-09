package com.fran.pruebams.application.mapper;

import com.fran.pruebams.application.dto.UserDto;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCsvConverter {
    public void convertUsersToCsv(List<UserDto> users, PrintWriter writer) {
        String csv = users.stream()
                .map(user -> user.getFullname() + "," + user.getPhone() + "," + user.getAddress())
                .collect(Collectors.joining("\n"));

        writer.write(csv);
    }
}
