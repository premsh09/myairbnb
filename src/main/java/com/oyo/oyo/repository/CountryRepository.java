package com.oyo.oyo.repository;

import com.oyo.oyo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}