package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.UnitMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitMaterialRepository extends JpaRepository<UnitMaterial, Integer> {

    List<UnitMaterial> findByUnitCode(String unitCode);

}