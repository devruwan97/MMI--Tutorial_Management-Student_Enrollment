package com.mathsmastery.platform.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UnitResponse {

    private Integer unitCount;
    private List<UnitDTO> units;

}
