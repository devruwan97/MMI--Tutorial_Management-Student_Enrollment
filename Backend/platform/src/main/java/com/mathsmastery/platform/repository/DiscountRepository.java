package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    List<Discount> findByActiveTrue();
}
