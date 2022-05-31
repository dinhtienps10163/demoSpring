package com.demo.demo.service.impl;

import com.demo.demo.model.Users;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.service.UserService;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users saveUpdate(Long id, Users newusers) {

        return userRepository.save(newusers);

    }

    @Override
    public void delete(Users users) {
        userRepository.delete(users);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

}
