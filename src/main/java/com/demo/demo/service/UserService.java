package com.demo.demo.service;

import com.demo.demo.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users save(Users users);
    Optional<Users> findByEmail(String email);

    Users saveUpdate(Long id, Users users);
    void delete(Users users);
    List<Users> findAll();

    Optional<Users> findById(Long id);
}
