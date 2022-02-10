package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.db.entity.User;

import java.util.Optional;

public interface UserService {

    User findById(int user_id);
}
