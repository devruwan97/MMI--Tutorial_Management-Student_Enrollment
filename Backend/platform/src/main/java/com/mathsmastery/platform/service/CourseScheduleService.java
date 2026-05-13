package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.model.CourseSchedule;
import com.mathsmastery.platform.model.Teacher;
import com.mathsmastery.platform.repository.CourseRepository;
import com.mathsmastery.platform.repository.CourseScheduleRepository;
import com.mathsmastery.platform.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseScheduleService {

    private final CourseScheduleRepository scheduleRepo;
    private final CourseRepository courseRepo;
    private final TeacherRepository teacherRepository;

    public CourseScheduleService(
            CourseScheduleRepository scheduleRepo,
            CourseRepository courseRepo, TeacherRepository teacherRepository
    ) {
        this.scheduleRepo = scheduleRepo;
        this.courseRepo = courseRepo;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> getCoursesByTeacher(Integer userId) {
        Teacher teacher = teacherRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        List<CourseSchedule> schedules =
                scheduleRepo.findByTeacherId(teacher.getId());

        return schedules.stream()
                .map(s -> courseRepo.findById(s.getCourseId())
                        .orElse(null))
                .filter(c -> c != null)
                .distinct()
                .toList();
    }
}
