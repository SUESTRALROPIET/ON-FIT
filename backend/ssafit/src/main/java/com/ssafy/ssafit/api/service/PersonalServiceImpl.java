package com.ssafy.ssafit.api.service;

import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.Trainer;
import com.ssafy.ssafit.db.repository.PersonalRepository;
import com.ssafy.ssafit.db.repository.TrainerRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl implements PersonalService{
    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    TrainerRepositorySupport trainerRepositorySupport;

    @Override
    public ExerciseLog saveExercise(ExerciseLog exerciseLog) {
        return personalRepository.save(exerciseLog);
    }

    @Override
    public Trainer getTrainer(String userId) {
        Trainer trainer = trainerRepositorySupport.getTrainer(userId).orElse(null);
        return trainer;
    }


}
