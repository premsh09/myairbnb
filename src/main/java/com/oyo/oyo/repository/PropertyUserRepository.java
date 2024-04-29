package com.oyo.oyo.repository;

import com.oyo.oyo.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {
    Optional<PropertyUser> findByUsername(String username);
}