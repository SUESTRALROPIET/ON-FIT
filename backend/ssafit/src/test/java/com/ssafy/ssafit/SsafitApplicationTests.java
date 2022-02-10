package com.ssafy.ssafit;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssafit.api.service.ClubService;
import com.ssafy.ssafit.db.entity.Club;
import com.ssafy.ssafit.db.entity.ClubMate;
import com.ssafy.ssafit.db.entity.QClub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class SsafitApplicationTests {

	@Autowired
	ClubService clubService;

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

	@Test
	void club_Select(){
		List<Club> CL = clubService.getClub();
		Assert.assertEquals(CL.size(),3);
	}

	@Test
	void test_club(){
		List<Integer> userList = clubService.getUserId(3);
//		List<ClubMate> cl = clubService.getAll(3);
//		System.out.println(userList.get(0));
//		Assert.assertEquals(cl.size(),1);
		Assert.assertEquals(userList.size(),1);
	}

}
