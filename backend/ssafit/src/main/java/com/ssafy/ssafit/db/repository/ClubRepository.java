package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClubRepository extends JpaRepository<Club,Integer> {

    Club findFirstByOrderByCreatedAtDesc();

    /*
    * Update, Delete 쿼리 작성시 @Modifying , @Transactional 추가해줘야함!
    * */
    @Modifying
    @Transactional
    @Query("UPDATE Club c SET c.count = c.count + 1 WHERE c.id = :clubId")
    void plusClubCount(@Param("clubId")int club_id);
//    Club findFirstByOrderByCreatedAtDESC();

//    void createCLubMate(@Param("cId") Long club_id,@Param("uID") int user_id);
}
