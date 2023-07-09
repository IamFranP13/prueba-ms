package com.fran.pruebams.infrastructure.persistence.repository;

import com.fran.pruebams.application.UserMapper;
import com.fran.pruebams.domain.model.User;
import com.fran.pruebams.domain.repository.UserRepository;
import com.fran.pruebams.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userJpaRepository.save(userEntity);
    }
}
