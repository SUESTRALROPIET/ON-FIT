package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.db.entity.User;
import com.ssafy.ssafit.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(int user_id) {
        return userRepository.findById(user_id).orElse(null);
    }
}
