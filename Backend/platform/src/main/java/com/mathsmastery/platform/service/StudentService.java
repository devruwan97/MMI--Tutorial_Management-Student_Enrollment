package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.StudentDTO;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.StudentRepository;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public StudentDTO createStudent(StudentDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = new Student();
        student.setUser(user);
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setParentName(dto.getParentName());
        student.setAddress(dto.getAddress());

        Student saved = studentRepository.save(student);

        return mapToDTO(saved);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToDTO(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setUserId(student.getUser() != null ? student.getUser().getId() : null);
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setParentName(student.getParentName());
        dto.setAddress(student.getAddress());

        return dto;
    }
}