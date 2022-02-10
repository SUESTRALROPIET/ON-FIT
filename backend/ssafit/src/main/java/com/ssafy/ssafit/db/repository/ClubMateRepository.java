package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.ClubMate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubMateRepository extends JpaRepository<ClubMate,Integer> {
    @Query("SELECT cm.userId.id FROM ClubMate cm where cm.clubId.id = :clubId")
    List<Integer> findUser(@Param("clubId") int clubId);
}
