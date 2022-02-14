package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.api.response.ClubLogRes;
import com.ssafy.ssafit.db.entity.ClubLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface ClubLogRepository extends JpaRepository<ClubLog,Integer> {

    @Query("SELECT cl FROM ClubLog cl WHERE cl.clubId.id = :clubId")    // 이거 몇시간 한거야.. 후,,,,,,,
    List<ClubLog> findClubLog(@Param("clubId") int clubId);
}
