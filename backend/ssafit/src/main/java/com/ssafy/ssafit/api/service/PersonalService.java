package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.Trainer;

public interface PersonalService {
    ExerciseLog saveExercise(ExerciseLog exerciseLog);
    Trainer getTrainer(String userId);
}
