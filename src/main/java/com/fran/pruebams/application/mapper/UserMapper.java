package com.fran.pruebams.application.mapper;

import com.fran.pruebams.domain.model.User;
import com.fran.pruebams.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomain(UserEntity entity) {
        return User.builder()
                .fullname(entity.getFullname())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }

}
