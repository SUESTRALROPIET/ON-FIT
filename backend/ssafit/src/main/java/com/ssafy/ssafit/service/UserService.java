package com.ssafy.ssafit.service;

import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<ExerciseLog> getExerciseInfo(String user_id);
    Optional<User> updateTrainer(User user);
}
