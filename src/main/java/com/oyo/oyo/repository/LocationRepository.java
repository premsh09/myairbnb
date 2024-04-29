package com.oyo.oyo.repository;

import com.oyo.oyo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}