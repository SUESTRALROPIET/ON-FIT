package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.api.request.ExerciseLogReq;
import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.Trainer;

public interface PersonalService {
    ExerciseLog saveExercise(ExerciseLogReq exerciseLogReq);
    Trainer getTrainer(String userId);
}
