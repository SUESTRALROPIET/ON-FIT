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
        List<Trainer> trainer = jpaQueryFactory
                .selectFrom(qTrainer)
                        .join(qUser)
                                .on(qTrainer.id.eq(qUser.trainerId.id))
                                        .where(qUser.userId.eq(userId))
                                                .fetch();
        System.out.println(trainer.size());
        return Optional.ofNullable(trainer.get(0));
    }

}
