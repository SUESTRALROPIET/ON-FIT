package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
