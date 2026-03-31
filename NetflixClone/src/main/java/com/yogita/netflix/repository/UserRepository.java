package com.yogita.netflix.repository;

import com.yogita.netflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
    boolean existsByEmail(String email);
}