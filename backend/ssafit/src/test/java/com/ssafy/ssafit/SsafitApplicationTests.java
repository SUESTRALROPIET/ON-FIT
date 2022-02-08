package com.ssafy.ssafit;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.db.entity.QClub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class SsafitApplicationTests {

	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	void queryDsl_Select(){
		Club club = new Club();
		entityManager.persist(club);

		JPAQueryFactory query = new JPAQueryFactory(entityManager);
		QClub qClub = QClub.club;

		Club result = query
				.selectFrom(qClub)
				.fetchOne();

	}

}
