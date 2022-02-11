package com.ssafy.ssafit.db.repository;

import com.ssafy.ssafit.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(String userId);
    //void save(User selectUser);
}
