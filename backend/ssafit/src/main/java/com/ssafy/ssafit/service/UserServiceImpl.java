package com.ssafy.ssafit.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.db.entity.*;
import com.ssafy.ssafit.db.repository.PersonalRepository;
import com.ssafy.ssafit.db.repository.UserRepository;
import com.ssafy.ssafit.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QExerciseLog qExerciseLog = QExerciseLog.exerciseLog;

    @Override
    public List<ExerciseLog> getExerciseInfo(String user_id) {
        System.out.println(user_id);
        List<ExerciseLog> exerciseLogs = jpaQueryFactory
                .selectFrom(qExerciseLog)
                .where(qExerciseLog.userId.userId.eq(user_id))
                .fetch();
        return exerciseLogs;
    }

    @Override
    public Optional<User> updateTrainer(User user) {
        Optional<User> update = userRepositorySupport.findUserByUserId(user.getUserId());
        update.ifPresent(selectUser -> {
            selectUser.setTrainerId(user.getTrainerId());
            userRepository.save(selectUser);
        });
        return update;
    }
}