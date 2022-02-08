package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

}
