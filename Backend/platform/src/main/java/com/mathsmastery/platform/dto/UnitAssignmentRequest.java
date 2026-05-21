package com.mathsmastery.platform.dto;

import java.util.List;

public class UnitAssignmentRequest {

    private Integer teacherId;
    private Integer courseId;
    private List<Integer> unitIds;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(List<Integer> unitIds) {
        this.unitIds = unitIds;
    }
}