package com.fran.pruebams.domain.repository;

import com.fran.pruebams.domain.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
