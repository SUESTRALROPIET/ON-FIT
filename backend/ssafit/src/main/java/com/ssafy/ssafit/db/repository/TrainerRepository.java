package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
}
