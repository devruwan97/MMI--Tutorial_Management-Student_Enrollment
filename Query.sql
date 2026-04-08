DROP DATABASE mmi_db;
CREATE DATABASE mmi_db;
USE mmi_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'student', 'teacher') NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    date_of_birth DATE,
    parent_name VARCHAR(100),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    qualifications TEXT,
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    category VARCHAR(100),
    fee DECIMAL(10,2),
    capacity INT,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL
);


CREATE TABLE units (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    unit_code VARCHAR(20),
    unit_name VARCHAR(100),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);


CREATE TABLE course_schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    teacher_id INT,
    day_of_week VARCHAR(20),
    start_time TIME,
    end_time TIME,
    location VARCHAR(100),
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE
);


CREATE TABLE enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    status ENUM('enrolled', 'pending', 'cancelled') DEFAULT 'pending',
    enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);


CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    enrollment_id INT,
    amount DECIMAL(10,2),
    discount_applied DECIMAL(10,2),
    final_amount DECIMAL(10,2),
    status ENUM('paid', 'pending', 'failed') DEFAULT 'pending',
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(id) ON DELETE CASCADE
);


CREATE TABLE sibling_groups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(100)
);


CREATE TABLE siblings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    sibling_group_id INT,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (sibling_group_id) REFERENCES sibling_groups(id) ON DELETE CASCADE
);


CREATE TABLE discounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type ENUM('percentage', 'fixed'),
    value DECIMAL(10,2),
    `condition` VARCHAR(100),
    active BOOLEAN DEFAULT TRUE
);


CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    unit_id INT,
    date DATE,
    status ENUM('present', 'absent'),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE
);


CREATE TABLE grade_codes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10),
    name VARCHAR(50),
    description TEXT,
    is_pass BOOLEAN,
    affects_gpa BOOLEAN
);


CREATE TABLE grade_master (
    id INT AUTO_INCREMENT PRIMARY KEY,
    grade_code_id INT,
    min_score INT,
    max_score INT,
    gpa_value DECIMAL(3,2),
    FOREIGN KEY (grade_code_id) REFERENCES grade_codes(id) ON DELETE CASCADE
);


CREATE TABLE student_grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    unit_id INT,
    enrollment_id INT,
    score DECIMAL(5,2),
    grade_code_id INT,
    graded_by INT,
    graded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(id) ON DELETE CASCADE,
    FOREIGN KEY (grade_code_id) REFERENCES grade_codes(id),
    FOREIGN KEY (graded_by) REFERENCES teachers(id)
);