package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<ExerciseLog,Long> {

}