package com.ssafy.ssafit.api.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.api.request.GoogleLoginReq;
import com.ssafy.ssafit.db.entity.ExerciseLog;
import com.ssafy.ssafit.db.entity.QExerciseLog;
import com.ssafy.ssafit.db.entity.Trainer;
import com.ssafy.ssafit.db.entity.User;
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

    @Override
    public User findById(String user_id) {
        return userRepository.findByUserId(user_id).orElse(null);
    }

    @Override
    public Optional<User> login(GoogleLoginReq googleLoginReq) {
        User user  = new User();
        user.setUserId(googleLoginReq.getUserId());

        Optional<User> update = userRepositorySupport.findUserByUserId(user.getUserId());

        if(!update.isPresent()){
            user.setFullName(googleLoginReq.getUserName());
            String userEmail = googleLoginReq.getUserId()+"@gmail.com";
            user.setEmail(userEmail);

            Trainer trainer = new Trainer();
            trainer.setId(1);
            user.setTrainerId(trainer);
            userRepository.save(user);
        }

        return Optional.of(user);
    }
}
