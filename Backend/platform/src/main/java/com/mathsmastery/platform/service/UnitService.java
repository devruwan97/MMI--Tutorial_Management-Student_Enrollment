package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.MaterialDTO;
import com.mathsmastery.platform.dto.UnitDTO;
import com.mathsmastery.platform.dto.UnitResponse;
import com.mathsmastery.platform.model.Term;
import com.mathsmastery.platform.model.Unit;
import com.mathsmastery.platform.model.UnitMaterial;
import com.mathsmastery.platform.repository.TermRepository;
import com.mathsmastery.platform.repository.UnitMaterialRepository;
import com.mathsmastery.platform.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitMaterialRepository materialRepository;
    private final TermRepository termRepository;

    public UnitService(UnitRepository unitRepository,
                       UnitMaterialRepository materialRepository, TermRepository termRepository) {
        this.unitRepository = unitRepository;
        this.materialRepository = materialRepository;
        this.termRepository = termRepository;
    }

    public UnitResponse getUnitsByCourse(Integer courseId) {

        List<Unit> units = unitRepository.findByCourseId(courseId);

        List<UnitDTO> unitDTOs = new ArrayList<>();

        for (Unit u : units) {

            List<UnitMaterial> materials =
                    materialRepository.findByUnitCode(u.getUnitCode());

            List<MaterialDTO> materialDTOs = materials.stream().map(m -> {
                MaterialDTO dto = new MaterialDTO();
                dto.setId(m.getId());
                dto.setTitle(m.getTitle());
                dto.setUrl(m.getUrl());
                return dto;
            }).toList();


            Term term = termRepository.findById(u.getTermId()).orElse(null);

            UnitDTO dto = new UnitDTO();
            dto.setId(u.getId());
            dto.setUnitCode(u.getUnitCode());
            dto.setUnitName(u.getUnitName());
            dto.setDescription(u.getDescription());

            dto.setTermId(u.getTermId());

            if (term != null) {
                dto.setTermName(term.getTermName());
                dto.setTermStartDate(term.getStartDate());
                dto.setTermEndDate(term.getEndDate());
            }

            dto.setMaterials(materialDTOs);

            unitDTOs.add(dto);
        }

        UnitResponse response = new UnitResponse();
        response.setUnitCount(unitDTOs.size());
        response.setUnits(unitDTOs);

        return response;
    }
}
