package com.ssafy.ssafit.db.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.db.entity.QTrainer;
import com.ssafy.ssafit.db.entity.QUser;
import com.ssafy.ssafit.db.entity.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainerRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTrainer qTrainer = QTrainer.trainer;
    QUser qUser = QUser.user;

    public Optional<Trainer> getTrainer(String userId) {
        System.out.println(userId);
        List<Trainer> trainers = jpaQueryFactory
                .selectFrom(qTrainer)
                .where(qTrainer.id.eq(
                        jpaQueryFactory.select(qTrainer.id)
                                .from(qUser)
                                .where(qUser.userId.eq(userId))
                ))
                .fetch();
        //select trainer_name from trainer where id = (select trainer_id from user where id = user_id);
        return Optional.ofNullable(trainers.get(0));
    }

}
