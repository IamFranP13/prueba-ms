package com.fran.pruebams.application;

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

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .fullname(user.getFullname())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
