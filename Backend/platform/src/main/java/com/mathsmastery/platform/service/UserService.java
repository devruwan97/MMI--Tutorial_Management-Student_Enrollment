package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User updateUser(Integer userId, User updatedUser) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setPhone(updatedUser.getPhone());

        if (updatedUser.getPasswordHash() != null &&
                !updatedUser.getPasswordHash().isEmpty()) {

            existingUser.setPasswordHash(
                    passwordEncoder.encode(updatedUser.getPasswordHash())
            );
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(existingUser);
    }

    public User register(User user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        return userRepository.save(user);
    }

    public List<User> bulkCreateFromExcel(MultipartFile file) {
        List<User> users = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                User user = new User();

                user.setName(getCell(row, 0));
                user.setEmail(getCell(row, 1));

                String rawPassword = getCell(row, 2);
                String roleStr = getCell(row, 3);

                user.setPhone(getCell(row, 4));

                // Role fallback
                try {
                    user.setRole(User.Role.valueOf(roleStr.toUpperCase()));
                } catch (Exception e) {
                    user.setRole(User.Role.student);
                }

                // Encode password
                user.setPasswordHash(passwordEncoder.encode(rawPassword));

                users.add(userRepository.save(user));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file", e);
        }

        return users;
    }

    private String getCell(Row row, int index) {
        Cell cell = row.getCell(index);
        return cell != null ? cell.toString().trim() : "";
    }
}