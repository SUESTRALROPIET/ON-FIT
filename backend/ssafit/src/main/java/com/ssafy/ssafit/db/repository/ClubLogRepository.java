package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.api.response.ClubLogRes;
import com.ssafy.ssafit.db.entity.ClubLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubLogRepository extends JpaRepository<ClubLog,Integer> {

    @Query("SELECT cl.exerciseId, cl.exCount, cl.exTime FROM ClubLog cl WHERE cl.clubId.id = :clubId")
    List<ClubLogRes> findClubLog(@Param("clubId") int clubId);
}
