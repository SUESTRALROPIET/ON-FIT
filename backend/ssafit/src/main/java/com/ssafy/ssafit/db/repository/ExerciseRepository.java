package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {
}
