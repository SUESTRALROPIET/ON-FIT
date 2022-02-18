package com.ssafy.ssafit.api.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.api.request.ExerciseLogReq;
import com.ssafy.ssafit.db.entity.*;
import com.ssafy.ssafit.db.repository.PersonalRepository;
import com.ssafy.ssafit.db.repository.TrainerRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PersonalServiceImpl implements PersonalService{
    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    TrainerRepositorySupport trainerRepositorySupport;
    
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QExercise qExercise = QExercise.exercise;
    
    @Override
    public ExerciseLog saveExercise(ExerciseLogReq exerciseLogReq) {

        User user = new User();
        user.setUserId(exerciseLogReq.getUserId());
        ExerciseLog exerciseLog = new ExerciseLog();

        // 유저가 어떤 운동을, 얼마나 오래, 얼마나 정확하게 했는지 기록
        exerciseLog.setUserId(user);
        exerciseLog.setExDuration(exerciseLogReq.getExDuration());
        exerciseLog.setExStatus(exerciseLogReq.getExStatus());

        // 현재 실시한 운동의 정보(기본 횟수, 칼로리)를 db에서 가져온 후 횟수를 곱해 현재 운동의 총 횟수와 총 칼로리량 구하기
        Exercise exercise = jpaQueryFactory
                .select(qExercise)
                .from(qExercise)
                .where(qExercise.id.eq(exerciseLogReq.getExId()))
                .fetchOne();

        exerciseLog.setExId(exercise);
        exerciseLog.setExCount(exercise.getCount()*exerciseLogReq.getExSet());
        exerciseLog.setExCal(exercise.getCalorie()*exerciseLogReq.getExSet());
        exerciseLog.setExTime(Timestamp.valueOf(LocalDateTime.now()));

        return personalRepository.save(exerciseLog);
    }

    @Override
    public Trainer getTrainer(String userId) {
        Trainer trainer = trainerRepositorySupport.getTrainer(userId).orElse(null);
        return trainer;
    }


}
