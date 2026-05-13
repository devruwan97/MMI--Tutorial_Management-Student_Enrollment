package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.TeacherRequest;
import com.mathsmastery.platform.dto.TeacherResponse;
import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.model.CourseSchedule;
import com.mathsmastery.platform.model.Teacher;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.CourseRepository;
import com.mathsmastery.platform.repository.CourseScheduleRepository;
import com.mathsmastery.platform.repository.TeacherRepository;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseScheduleRepository courseScheduleRepository;

    public TeacherService(
            TeacherRepository teacherRepository,
            UserRepository userRepository,
            CourseRepository courseRepository,
            CourseScheduleRepository courseScheduleRepository
    ) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.courseScheduleRepository = courseScheduleRepository;
    }

    public TeacherResponse createTeacher(Integer userId, TeacherRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != User.Role.teacher) {
            throw new RuntimeException("User is not a teacher");
        }

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setQualifications(request.getQualifications());
        teacher.setBio(request.getBio());

        return mapToResponse(teacherRepository.save(teacher));
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TeacherResponse getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findByUserId(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return mapToResponse(teacher);
    }

    public TeacherResponse updateTeacher(Integer id, TeacherRequest request) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setQualifications(request.getQualifications());
        teacher.setBio(request.getBio());

        return mapToResponse(teacherRepository.save(teacher));
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    public void assignCourse(Integer teacherId, Integer courseId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseSchedule schedule = new CourseSchedule();
        schedule.setTeacherId(teacher.getId());
        schedule.setCourseId(course.getId());

        schedule.setDayOfWeek("Monday");
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(11, 0));
        schedule.setLocation("Main Campus");

        courseScheduleRepository.save(schedule);
    }

    private TeacherResponse mapToResponse(Teacher teacher) {

        TeacherResponse res = new TeacherResponse();

        res.setId(teacher.getId());
        res.setUserId(teacher.getUser().getId());
        res.setName(teacher.getUser().getName());
        res.setEmail(teacher.getUser().getEmail());
        res.setRole(teacher.getUser().getRole().name());
        res.setQualifications(teacher.getQualifications());
        res.setBio(teacher.getBio());
        res.setCreatedAt(teacher.getCreatedAt());

        return res;
    }
}