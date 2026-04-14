package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.TeacherRequest;
import com.mathsmastery.platform.dto.TeacherResponse;
import com.mathsmastery.platform.model.Teacher;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.TeacherRepository;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public TeacherService(TeacherRepository teacherRepository,
                          UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
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
        Teacher teacher = teacherRepository.findById(id)
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
