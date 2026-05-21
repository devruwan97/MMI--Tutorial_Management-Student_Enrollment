CREATE DATABASE  IF NOT EXISTS `mmi_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mmi_db`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: mmi_db
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assessment_grades`
--

DROP TABLE IF EXISTS `assessment_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assessment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `marks` double DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `assessment_id` (`assessment_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `assessment_grades_ibfk_1` FOREIGN KEY (`assessment_id`) REFERENCES `assessments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `assessment_grades_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_grades`
--

LOCK TABLES `assessment_grades` WRITE;
/*!40000 ALTER TABLE `assessment_grades` DISABLE KEYS */;
INSERT INTO `assessment_grades` VALUES (1,1,5,85,'A','2026-05-20 11:09:45');
/*!40000 ALTER TABLE `assessment_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessments`
--

DROP TABLE IF EXISTS `assessments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL,
  `assessment_code` varchar(255) NOT NULL,
  `assessment_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_marks` int(11) DEFAULT '100',
  `due_date` date DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `unit_id` (`unit_id`),
  CONSTRAINT `assessments_ibfk_1` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessments`
--

LOCK TABLES `assessments` WRITE;
/*!40000 ALTER TABLE `assessments` DISABLE KEYS */;
INSERT INTO `assessments` VALUES (1,1,'A1','Assignment 1','Basic Algebra Tasks',100,'2026-06-01','2026-05-12'),(2,1,'Q1','Quiz 1','Limits and derivatives',50,'2026-06-10','2026-05-12'),(3,13,'A1','Assignment 1','Basic Algebra Tasks',100,'2026-06-01','2026-05-12'),(4,13,'A2','Assignment 2','Advanced Algebra Tasks',100,'2026-08-01',NULL),(5,13,'A3','Assignment 3','Advanced Algebra Tasks',100,'2026-05-01',NULL);
/*!40000 ALTER TABLE `assessments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` enum('present','absent') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `unit_id` (`unit_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attendance_ibfk_3` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_schedules`
--

DROP TABLE IF EXISTS `course_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_schedules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `day_of_week` varchar(255) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_schedules_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `course_schedules_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_schedules`
--

LOCK TABLES `course_schedules` WRITE;
/*!40000 ALTER TABLE `course_schedules` DISABLE KEYS */;
INSERT INTO `course_schedules` VALUES (1,1,6,'Monday','09:00:00','11:00:00','Building A - Room 101'),(2,2,7,'Tuesday','11:00:00','13:00:00','Building A - Room 102'),(3,3,8,'Wednesday','10:00:00','12:00:00','Building B - Lab 1'),(4,4,11,'Thursday','14:00:00','16:00:00','Building C - Room 201'),(5,4,6,'Monday','09:00:00','11:00:00','Main Campus'),(6,8,8,'Monday','09:00:00','11:00:00','Main Campus');
/*!40000 ALTER TABLE `course_schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_teachers`
--

DROP TABLE IF EXISTS `course_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_teachers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_teachers_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `course_teachers_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_teachers`
--

LOCK TABLES `course_teachers` WRITE;
/*!40000 ALTER TABLE `course_teachers` DISABLE KEYS */;
INSERT INTO `course_teachers` VALUES (1,1,6,'lecturer'),(2,1,14,'tutor'),(3,1,18,'tutor'),(4,2,7,'lecturer'),(5,2,15,'co_lecturer'),(6,2,19,'tutor'),(7,3,8,'lecturer'),(8,3,14,'co_lecturer'),(9,3,17,'tutor'),(10,4,11,'lecturer'),(11,4,18,'tutor'),(12,4,16,'co_lecturer');
/*!40000 ALTER TABLE `course_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `fee` decimal(38,2) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Diploma of Foundation Mathematics','Preparatory program covering essential mathematical concepts for higher education pathways','Diploma',8500.00,250,3,'2026-05-11 11:22:49'),(2,'Diploma of Applied Mathematics','Industry-focused diploma in practical mathematical applications and modelling','Diploma',9200.00,180,3,'2026-05-11 11:22:49'),(3,'Advanced Diploma of Mathematical Sciences','Advanced mathematical theory and analytical problem-solving','Advanced Diploma',11000.00,140,3,'2026-05-11 11:22:49'),(4,'Bachelor of Mathematics','Comprehensive undergraduate program in pure and applied mathematics','Bachelor Degree',28500.00,220,3,'2026-05-11 11:22:49'),(5,'Bachelor of Applied Mathematics','Undergraduate program focused on mathematical modelling and applications','Bachelor Degree',29500.00,180,3,'2026-05-11 11:22:49'),(6,'Bachelor of Data Science and Mathematics','Integrated program combining mathematics, analytics and computational methods','Bachelor Degree',32000.00,170,3,'2026-05-11 11:22:49'),(7,'Bachelor of Computational Mathematics','Mathematics-driven computing and algorithm development','Bachelor Degree',31000.00,150,3,'2026-05-11 11:22:49'),(8,'Bachelor of Statistics','Professional undergraduate program in statistical analysis and modelling','Bachelor Degree',30000.00,160,3,'2026-05-11 11:22:49'),(9,'Bachelor of Financial Mathematics','Mathematics applied to finance, investment and quantitative modelling','Bachelor Degree',33500.00,140,3,'2026-05-11 11:22:49'),(10,'Graduate Certificate in Mathematical Analytics','Postgraduate specialisation in advanced mathematical analytics','Graduate Certificate',9500.00,90,3,'2026-05-11 11:22:49'),(11,'Graduate Diploma of Applied Statistics','Postgraduate statistical modelling and advanced inference','Graduate Diploma',14500.00,80,3,'2026-05-11 11:22:49'),(12,'Master of Mathematics','Advanced postgraduate mathematical research and theoretical analysis','Masters Degree',38500.00,70,3,'2026-05-11 11:22:49'),(13,'Master of Applied Mathematics','Professional postgraduate degree in applied mathematical systems','Masters Degree',39500.00,75,3,'2026-05-11 11:22:49'),(14,'Master of Data Analytics','Advanced analytics, machine learning and mathematical modelling','Masters Degree',41000.00,85,3,'2026-05-11 11:22:49'),(15,'Doctor of Philosophy (Mathematical Sciences)','Research-intensive doctoral program in mathematical sciences','Doctorate',52000.00,35,3,'2026-05-11 11:22:49');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` enum('percentage','fixed') DEFAULT NULL,
  `value` double DEFAULT NULL,
  `condition` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `condition_rule` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollments`
--

DROP TABLE IF EXISTS `enrollments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `status` enum('enrolled','pending','cancelled') DEFAULT 'pending',
  `enrolled_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKi0g6mfijtuh199nj653nva6j5` (`student_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollments`
--

LOCK TABLES `enrollments` WRITE;
/*!40000 ALTER TABLE `enrollments` DISABLE KEYS */;
INSERT INTO `enrollments` VALUES (11,1,4,'enrolled','2026-05-11 13:01:40'),(12,2,6,'enrolled','2026-05-11 13:01:40'),(13,3,8,'enrolled','2026-05-11 13:01:40'),(15,5,9,'enrolled','2026-05-11 13:01:40'),(16,6,7,'enrolled','2026-05-11 13:01:40'),(17,7,10,'enrolled','2026-05-11 13:01:40'),(18,8,11,'enrolled','2026-05-11 13:01:40'),(19,9,12,'enrolled','2026-05-11 13:01:40'),(74,11,11,'enrolled','2026-05-13 07:31:54'),(75,12,12,'enrolled','2026-05-13 07:31:54'),(76,13,13,'enrolled','2026-05-13 07:31:54'),(77,14,14,'enrolled','2026-05-13 07:31:54'),(78,15,15,'enrolled','2026-05-13 07:31:54'),(79,16,1,'enrolled','2026-05-13 07:31:54'),(80,17,2,'enrolled','2026-05-13 07:31:54'),(81,18,3,'enrolled','2026-05-13 07:31:54'),(82,19,4,'enrolled','2026-05-13 07:31:54'),(83,20,5,'enrolled','2026-05-13 07:31:54'),(84,21,6,'enrolled','2026-05-13 07:31:54'),(85,22,7,'enrolled','2026-05-13 07:31:54'),(86,23,8,'enrolled','2026-05-13 07:31:54'),(87,24,9,'enrolled','2026-05-13 07:31:54'),(88,25,10,'enrolled','2026-05-13 07:31:54'),(89,26,11,'enrolled','2026-05-13 07:31:54'),(90,27,12,'enrolled','2026-05-13 07:31:54'),(91,28,13,'enrolled','2026-05-13 07:31:54'),(92,29,14,'enrolled','2026-05-13 07:31:54'),(93,30,15,'enrolled','2026-05-13 07:31:54'),(94,31,1,'enrolled','2026-05-13 07:31:54'),(95,32,2,'enrolled','2026-05-13 07:31:54'),(96,33,3,'enrolled','2026-05-13 07:31:54'),(97,34,4,'enrolled','2026-05-13 07:31:54'),(98,35,5,'enrolled','2026-05-13 07:31:54'),(99,36,6,'enrolled','2026-05-13 07:31:54'),(100,37,7,'enrolled','2026-05-13 07:31:54'),(101,38,8,'enrolled','2026-05-13 07:31:54'),(102,39,9,'enrolled','2026-05-13 07:31:54'),(103,40,10,'enrolled','2026-05-13 07:31:54'),(105,1,1,'pending','2026-05-19 04:35:53'),(106,1,8,'pending','2026-05-19 04:55:42');
/*!40000 ALTER TABLE `enrollments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_codes`
--

DROP TABLE IF EXISTS `grade_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_pass` tinyint(1) DEFAULT NULL,
  `affects_gpa` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_codes`
--

LOCK TABLES `grade_codes` WRITE;
/*!40000 ALTER TABLE `grade_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_master`
--

DROP TABLE IF EXISTS `grade_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_code_id` int(11) DEFAULT NULL,
  `min_score` double DEFAULT NULL,
  `max_score` double DEFAULT NULL,
  `gpa_value` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grade_code_id` (`grade_code_id`),
  CONSTRAINT `grade_master_ibfk_1` FOREIGN KEY (`grade_code_id`) REFERENCES `grade_codes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_master`
--

LOCK TABLES `grade_master` WRITE;
/*!40000 ALTER TABLE `grade_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `message` text,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `enrollment_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `discount_applied` double DEFAULT NULL,
  `final_amount` double DEFAULT NULL,
  `status` enum('paid','pending','failed') DEFAULT 'pending',
  `payment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `enrollment_id` (`enrollment_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollments` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,1,105,8500,0,8500,'paid','2026-05-19 04:35:53'),(2,1,106,30000,0,30000,'pending','2026-05-19 04:55:42');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sibling_groups`
--

DROP TABLE IF EXISTS `sibling_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sibling_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sibling_groups`
--

LOCK TABLES `sibling_groups` WRITE;
/*!40000 ALTER TABLE `sibling_groups` DISABLE KEYS */;
INSERT INTO `sibling_groups` VALUES (1,NULL);
/*!40000 ALTER TABLE `sibling_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sibling_requests`
--

DROP TABLE IF EXISTS `sibling_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sibling_requests` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `requester_student_id` bigint(20) NOT NULL,
  `target_student_id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sibling_requests`
--

LOCK TABLES `sibling_requests` WRITE;
/*!40000 ALTER TABLE `sibling_requests` DISABLE KEYS */;
INSERT INTO `sibling_requests` VALUES (1,5,11,'APPROVED','2026-05-21 00:50:27');
/*!40000 ALTER TABLE `sibling_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `siblings`
--

DROP TABLE IF EXISTS `siblings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `siblings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `sibling_id` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `siblings`
--

LOCK TABLES `siblings` WRITE;
/*!40000 ALTER TABLE `siblings` DISABLE KEYS */;
INSERT INTO `siblings` VALUES (1,5,11,'ACTIVE','2026-05-21 00:51:09'),(2,11,5,'ACTIVE','2026-05-21 00:51:09');
/*!40000 ALTER TABLE `siblings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grades`
--

DROP TABLE IF EXISTS `student_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `enrollment_id` int(11) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `grade_code_id` int(11) DEFAULT NULL,
  `graded_by` int(11) DEFAULT NULL,
  `graded_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `grade_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `unit_id` (`unit_id`),
  KEY `enrollment_id` (`enrollment_id`),
  KEY `grade_code_id` (`grade_code_id`),
  KEY `graded_by` (`graded_by`),
  CONSTRAINT `student_grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_grades_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_grades_ibfk_3` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_grades_ibfk_4` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_grades_ibfk_5` FOREIGN KEY (`grade_code_id`) REFERENCES `grade_codes` (`id`),
  CONSTRAINT `student_grades_ibfk_6` FOREIGN KEY (`graded_by`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grades`
--

LOCK TABLES `student_grades` WRITE;
/*!40000 ALTER TABLE `student_grades` DISABLE KEYS */;
INSERT INTO `student_grades` VALUES (1,16,1,1,79,85,NULL,15,'2026-05-20 13:06:13','A'),(2,31,1,1,94,62,NULL,15,'2026-05-20 13:06:18','C'),(3,1,4,13,11,86,NULL,15,'2026-05-20 13:22:07','A'),(4,1,1,2,105,78,NULL,15,'2026-05-20 13:35:27','B'),(5,1,4,14,11,90,NULL,15,'2026-05-20 13:36:29','A'),(6,1,4,15,11,73,NULL,15,'2026-05-20 13:36:38','B');
/*!40000 ALTER TABLE `student_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,5,'2003-04-12','David Morgan','12 King Street, Melbourne','2026-05-11 12:24:46'),(2,6,'2002-09-25','Emma Smith','45 Queen Street, Sydney','2026-05-11 12:24:46'),(3,7,'2004-01-18','Michael Johnson','78 George Street, Brisbane','2026-05-11 12:24:46'),(5,9,'2003-11-05','Daniel Lee','99 Swanston Street, Melbourne','2026-05-11 12:24:46'),(6,29,'2003-03-11','Robert Clarke','10 Queen St, Melbourne','2026-05-11 13:01:06'),(7,30,'2002-07-22','Emily Bennett','15 King St, Sydney','2026-05-11 13:01:06'),(8,31,'2004-01-09','James Martin','88 George St, Brisbane','2026-05-11 13:01:06'),(9,32,'2003-10-30','Laura Wilson','22 Collins St, Melbourne','2026-05-11 13:01:06'),(11,34,'2003-01-01','Parent A','Melbourne','2026-05-13 07:23:16'),(12,35,'2003-01-02','Parent B','Sydney','2026-05-13 07:23:16'),(13,36,'2003-01-03','Parent C','Brisbane','2026-05-13 07:23:16'),(14,37,'2003-01-04','Parent D','Perth','2026-05-13 07:23:16'),(15,38,'2003-01-05','Parent E','Adelaide','2026-05-13 07:23:16'),(16,39,'2003-01-06','Parent F','Hobart','2026-05-13 07:23:16'),(17,40,'2003-01-07','Parent G','Darwin','2026-05-13 07:23:16'),(18,41,'2003-01-08','Parent H','Canberra','2026-05-13 07:23:16'),(19,42,'2003-01-09','Parent I','Melbourne','2026-05-13 07:23:16'),(20,43,'2003-01-10','Parent J','Sydney','2026-05-13 07:23:16'),(21,44,'2003-01-11','Parent K','Brisbane','2026-05-13 07:23:16'),(22,45,'2003-01-12','Parent L','Perth','2026-05-13 07:23:16'),(23,46,'2003-01-13','Parent M','Adelaide','2026-05-13 07:23:16'),(24,47,'2003-01-14','Parent N','Hobart','2026-05-13 07:23:16'),(25,48,'2003-01-15','Parent O','Darwin','2026-05-13 07:23:16'),(26,49,'2003-01-16','Parent P','Canberra','2026-05-13 07:23:16'),(27,50,'2003-01-17','Parent Q','Melbourne','2026-05-13 07:23:16'),(28,51,'2003-01-18','Parent R','Sydney','2026-05-13 07:23:16'),(29,52,'2003-01-19','Parent S','Brisbane','2026-05-13 07:23:16'),(30,53,'2003-01-20','Parent T','Perth','2026-05-13 07:23:16'),(31,54,'2003-01-21','Parent U','Adelaide','2026-05-13 07:23:16'),(32,55,'2003-01-22','Parent V','Hobart','2026-05-13 07:23:16'),(33,56,'2003-01-23','Parent W','Darwin','2026-05-13 07:23:16'),(34,57,'2003-01-24','Parent X','Canberra','2026-05-13 07:23:16'),(35,58,'2003-01-25','Parent Y','Melbourne','2026-05-13 07:23:16'),(36,59,'2003-01-26','Parent Z','Sydney','2026-05-13 07:23:16'),(37,60,'2003-01-27','Parent AA','Brisbane','2026-05-13 07:23:16'),(38,61,'2003-01-28','Parent AB','Perth','2026-05-13 07:23:16'),(39,62,'2003-01-29','Parent AC','Adelaide','2026-05-13 07:23:16'),(40,63,'2003-01-30','Parent AD','Hobart','2026-05-13 07:23:16');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assessment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submitted_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_submission_assessment` (`assessment_id`),
  KEY `fk_submission_student` (`student_id`),
  CONSTRAINT `fk_submission_assessment` FOREIGN KEY (`assessment_id`) REFERENCES `assessments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_submission_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES (1,1,5,'Assignment_1.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','GRADED','2026-05-13 21:59:40'),(2,2,5,'Quiz_Report.pdf','https://www.orimi.com/pdf-test.pdf','PENDING','2026-05-13 21:59:40'),(3,3,5,'Final_Project.pdf','https://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf','PENDING','2026-05-13 21:59:40'),(4,1,16,'Assignment_1.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','PENDING','2026-05-20 18:32:51'),(5,1,31,'Assignment_1.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','PENDING','2026-05-20 18:32:51'),(6,1,1,'Assignment_1.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','PENDING','2026-05-20 18:32:51'),(7,1,16,'Assignment_2.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','PENDING','2026-05-20 18:32:51'),(8,1,31,'Assignment_2.pdf','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','PENDING','2026-05-20 18:32:51');
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (6,15,'PhD Mathematics (Algebra)','Head of Pure Mathematics Department','2026-05-11 12:32:25'),(7,16,'PhD Statistics','Head of Data & Probability Division','2026-05-11 12:32:25'),(8,17,'PhD Applied Mathematics','Specialist in Mathematical Modelling','2026-05-11 12:32:25'),(9,18,'PhD Computational Mathematics','Focus on Algorithms and Numerical Methods','2026-05-11 12:32:25'),(10,19,'PhD Discrete Mathematics','Graph Theory and Combinatorics Expert','2026-05-11 12:32:25'),(11,20,'PhD Mathematical Analysis','Calculus and Real Analysis Specialist','2026-05-11 12:32:25'),(12,21,'PhD Financial Mathematics','Quantitative Finance and Modelling','2026-05-11 12:32:25'),(13,22,'PhD Statistics & Machine Learning','Probability and Data Science','2026-05-11 12:32:25'),(14,23,'MSc Mathematics','Supports undergraduate mathematics units','2026-05-11 12:32:25'),(15,24,'MSc Applied Mathematics','Tutorial and lab support','2026-05-11 12:32:25'),(16,25,'MSc Statistics','Probability and data analysis support','2026-05-11 12:32:25'),(17,26,'MSc Computational Science','Programming and modelling support','2026-05-11 12:32:25'),(18,27,'BSc Mathematics','Undergraduate tutoring support','2026-05-11 12:32:25'),(19,28,'BSc Statistics','First-year mathematics tutoring','2026-05-11 12:32:25');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terms`
--

DROP TABLE IF EXISTS `terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `term_name` varchar(255) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terms`
--

LOCK TABLES `terms` WRITE;
/*!40000 ALTER TABLE `terms` DISABLE KEYS */;
INSERT INTO `terms` VALUES (1,2026,'Semester 1','2026-02-01','2026-06-30','2026-05-12 10:46:50'),(2,2026,'Semester 2','2026-07-20','2026-11-30','2026-05-12 10:46:50'),(3,2026,'Summer Semester','2026-12-10','2027-01-31','2026-05-12 10:46:50'),(4,2025,'Semester 1','2025-02-01','2025-06-30','2026-05-12 10:53:44'),(5,2025,'Semester 2','2025-07-20','2025-11-30','2026-05-12 10:53:44'),(6,2027,'Semester 1','2027-02-01','2027-06-30','2026-05-12 10:53:44'),(7,2027,'Semester 2','2027-07-20','2027-11-30','2026-05-12 10:53:44');
/*!40000 ALTER TABLE `terms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_materials`
--

DROP TABLE IF EXISTS `unit_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit_materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL,
  `unit_code` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_unit_materials_unit` (`unit_id`),
  CONSTRAINT `fk_unit_materials_unit` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_materials`
--

LOCK TABLES `unit_materials` WRITE;
/*!40000 ALTER TABLE `unit_materials` DISABLE KEYS */;
INSERT INTO `unit_materials` VALUES (1,1,'DPM101','Foundations of Algebra Lecture Notes','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','2026-05-12 10:17:34'),(2,1,'DPM101','Foundations of Algebra Tutorial Questions','https://www.africau.edu/images/default/sample.pdf','2026-05-12 10:17:34'),(3,2,'DPM102','Introduction to Calculus Workbook','https://www.orimi.com/pdf-test.pdf','2026-05-12 10:17:34'),(4,2,'DPM102','Calculus Practice Exercises','https://gahp.net/wp-content/uploads/2017/09/sample.pdf','2026-05-12 10:17:34'),(5,3,'DPM103','Mathematical Reasoning Guide','https://www.learningcontainer.com/wp-content/uploads/2019/09/sample-pdf-file.pdf','2026-05-12 10:17:34'),(6,3,'DPM103','Logic and Proof Techniques','https://www.clickdimensions.com/links/TestPDFfile.pdf','2026-05-12 10:17:34'),(7,4,'DPM104','Applied Problem Solving Exercises','https://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf','2026-05-12 10:17:34'),(8,4,'DPM104','Real World Mathematics Applications','https://www.pdf995.com/samples/pdf.pdf','2026-05-12 10:17:34'),(9,5,'DAM201','Applied Linear Algebra Notes','https://www.hq.nasa.gov/alsj/a17/A17_FlightPlan.pdf','2026-05-12 10:17:34'),(10,5,'DAM201','Matrices and Vector Systems Workbook','https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf','2026-05-12 10:17:34'),(11,6,'DAM202','Differential Equations Lecture Slides','https://www.irs.gov/pub/irs-pdf/fw4.pdf','2026-05-12 10:17:34'),(12,6,'DAM202','Dynamic Systems Tutorial','https://www.uscis.gov/sites/default/files/document/forms/i-9-paper-version.pdf','2026-05-12 10:17:34'),(13,7,'DAM203','Numerical Methods Lab Manual','https://www.ets.org/Media/Tests/TOEFL/pdf/SampleQuestions.pdf','2026-05-12 10:17:34'),(14,7,'DAM203','Approximation Techniques Workbook','https://www.orimi.com/pdf-test.pdf','2026-05-12 10:17:34'),(15,8,'DAM204','Mathematical Modelling Case Studies','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','2026-05-12 10:17:34'),(16,8,'DAM204','Industry Modelling Project Guide','https://www.africau.edu/images/default/sample.pdf','2026-05-12 10:17:34'),(17,9,'AMS301','Advanced Algebra Research Notes','https://www.learningcontainer.com/wp-content/uploads/2019/09/sample-pdf-file.pdf','2026-05-12 10:17:34'),(18,9,'AMS301','Groups and Rings Handbook','https://www.clickdimensions.com/links/TestPDFfile.pdf','2026-05-12 10:17:34'),(19,10,'AMS302','Real Analysis Lecture Notes','https://gahp.net/wp-content/uploads/2017/09/sample.pdf','2026-05-12 10:17:34'),(20,10,'AMS302','Continuity and Limits Workbook','https://www.pdf995.com/samples/pdf.pdf','2026-05-12 10:17:34'),(21,11,'AMS303','Optimization Theory Guide','https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf','2026-05-12 10:17:34'),(22,11,'AMS303','Linear Optimization Exercises','https://www.hq.nasa.gov/alsj/a17/A17_FlightPlan.pdf','2026-05-12 10:17:34'),(23,12,'AMS304','Research Methods Handbook','https://www.irs.gov/pub/irs-pdf/fw4.pdf','2026-05-12 10:17:34'),(24,12,'AMS304','Academic Writing for Mathematics','https://www.uscis.gov/sites/default/files/document/forms/i-9-paper-version.pdf','2026-05-12 10:17:34'),(25,13,'BMA401','Pure Mathematics I Lecture Notes','https://www.ets.org/Media/Tests/TOEFL/pdf/SampleQuestions.pdf','2026-05-12 10:17:34'),(26,13,'BMA401','Advanced Calculus Exercises','https://www.orimi.com/pdf-test.pdf','2026-05-12 10:17:34'),(27,14,'BMA402','Linear Algebra II Workbook','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','2026-05-12 10:17:34'),(28,14,'BMA402','Vector Spaces and Transformations','https://www.africau.edu/images/default/sample.pdf','2026-05-12 10:17:34'),(29,15,'BMA403','Discrete Mathematics Study Guide','https://www.learningcontainer.com/wp-content/uploads/2019/09/sample-pdf-file.pdf','2026-05-12 10:17:34'),(30,15,'BMA403','Graph Theory Tutorial','https://www.clickdimensions.com/links/TestPDFfile.pdf','2026-05-12 10:17:34'),(31,16,'BMA404','Mathematical Applications Handbook','https://gahp.net/wp-content/uploads/2017/09/sample.pdf','2026-05-12 10:17:34'),(32,16,'BMA404','Applied Modelling Project Manual','https://www.pdf995.com/samples/pdf.pdf','2026-05-12 10:17:34'),(33,13,'BMA401','Assessment 1','https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf','2026-05-12 13:18:08'),(34,13,'BMA401','Assessment 2','https://www.orimi.com/pdf-test.pdf','2026-05-12 13:18:08'),(35,13,'BMA401','Assessment 3','https://gahp.net/wp-content/uploads/2017/09/sample.pdf','2026-05-12 13:18:08');
/*!40000 ALTER TABLE `unit_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `units` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `unit_code` varchar(255) DEFAULT NULL,
  `unit_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `term_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `fk_units_terms` (`term_id`),
  CONSTRAINT `fk_units_terms` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE SET NULL,
  CONSTRAINT `units_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES (1,1,'DPM101','Foundations of Algebra','Basic algebraic structures and problem solving','2026-05-11 12:48:37',1),(2,1,'DPM102','Introduction to Calculus','Limits, derivatives and basic integration','2026-05-11 12:48:37',1),(3,1,'DPM103','Mathematical Reasoning','Logic and proof techniques','2026-05-11 12:48:37',1),(4,1,'DPM104','Applied Problem Solving','Real-world math applications','2026-05-11 12:48:37',1),(5,2,'DAM201','Applied Linear Algebra','Matrices and vector systems','2026-05-11 12:48:37',1),(6,2,'DAM202','Differential Equations','Modeling dynamic systems','2026-05-11 12:48:37',1),(7,2,'DAM203','Numerical Methods','Approximation and computation','2026-05-11 12:48:37',1),(8,2,'DAM204','Mathematical Modelling','Industry-based modelling','2026-05-11 12:48:37',1),(9,3,'AMS301','Advanced Algebra','Groups, rings, and structures','2026-05-11 12:48:37',2),(10,3,'AMS302','Real Analysis','Limits, continuity, rigor','2026-05-11 12:48:37',2),(11,3,'AMS303','Optimization Theory','Linear and nonlinear optimization','2026-05-11 12:48:37',2),(12,3,'AMS304','Research Methods','Mathematical research skills','2026-05-11 12:48:37',2),(13,4,'BMA401','Pure Mathematics I','Advanced calculus','2026-05-11 12:48:37',2),(14,4,'BMA402','Linear Algebra II','Vector spaces and transformations','2026-05-11 12:48:37',2),(15,4,'BMA403','Discrete Mathematics','Combinatorics and graphs','2026-05-11 12:48:37',2),(16,4,'BMA404','Mathematical Applications','Applied modelling','2026-05-11 12:48:37',2),(17,1,'DPM105','Statistics Basics','Introduction to statistical thinking and probability','2026-05-12 10:55:37',1),(18,1,'DPM106','Linear Algebra Basics','Vectors, matrices and transformations','2026-05-12 10:55:37',2),(19,2,'DAM205','Advanced Numerical Methods','Higher level computation techniques','2026-05-12 10:55:37',1),(20,2,'DAM206','Systems Modelling','Mathematical system representations','2026-05-12 10:55:37',2),(21,3,'AMS305','Abstract Algebra II','Advanced group theory concepts','2026-05-12 10:55:37',1),(22,3,'AMS306','Functional Analysis','Vector spaces and operators','2026-05-12 10:55:37',2),(23,4,'BMA405','Complex Analysis','Functions of complex variables','2026-05-12 10:55:37',1),(24,4,'BMA406','Mathematical Proof Techniques','Advanced proof strategies','2026-05-12 10:55:37',2),(25,1,'DPM107','Discrete Structures','Logic, sets and relations','2026-05-12 10:55:37',1),(26,1,'DPM108','Applied Mathematics Tools','Real-world problem solving methods','2026-05-12 10:55:37',2),(27,2,'DAM207','Optimization Methods','Linear programming and optimization','2026-05-12 10:55:37',1),(28,2,'DAM208','Mathematical Simulation','Simulation modelling techniques','2026-05-12 10:55:37',2),(29,3,'AMS307','Number Theory','Prime numbers and modular arithmetic','2026-05-12 10:55:37',1),(30,3,'AMS308','Topology Basics','Introductory topological concepts','2026-05-12 10:55:37',2),(31,4,'BMA407','Mathematical Physics','Applications in physics systems','2026-05-12 10:55:37',1),(32,4,'BMA408','Advanced Linear Algebra','Eigenvalues and diagonalization','2026-05-12 10:55:37',2),(33,4,'BMA411','Mathematical Modelling II','Advanced modelling techniques','2026-05-12 10:57:09',3),(34,4,'BMA412','Capstone Mathematics Project','Final year project work','2026-05-12 10:57:09',4);
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units_teachers`
--

DROP TABLE IF EXISTS `units_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `units_teachers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `assigned_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_teacher_course_unit` (`teacher_id`,`course_id`,`unit_id`),
  UNIQUE KEY `UKfo4bmavu6m01ikfhksh8wdgvq` (`teacher_id`,`course_id`,`unit_id`),
  KEY `fk_units_teachers_course` (`course_id`),
  KEY `fk_units_teachers_unit` (`unit_id`),
  CONSTRAINT `fk_units_teachers_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_units_teachers_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_units_teachers_unit` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units_teachers`
--

LOCK TABLES `units_teachers` WRITE;
/*!40000 ALTER TABLE `units_teachers` DISABLE KEYS */;
INSERT INTO `units_teachers` VALUES (1,6,1,1,'2026-05-19 12:33:01'),(2,6,1,17,'2026-05-19 12:33:01'),(3,6,1,25,'2026-05-19 12:33:01'),(4,7,2,5,'2026-05-20 05:10:33'),(5,7,2,20,'2026-05-20 05:10:33'),(6,7,2,27,'2026-05-20 05:10:33'),(9,6,1,4,'2026-05-20 06:15:02');
/*!40000 ALTER TABLE `units_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('admin','student','teacher') NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'Devruwan','dev@gmail.com','$2a$10$km8mCtypWsYcFBVW1ELUCOH2TIv1OhNRv9sJx4v/8OOzo1J5TKZH2','admin','0412345678','2026-04-15 12:05:42','2026-04-15 12:05:42'),(5,'Alex Morgan','alex.morgan@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61451234567','2026-05-11 12:19:12','2026-05-11 12:24:06'),(6,'Jordan Smith','jordan.smith@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61452345678','2026-05-11 12:19:12','2026-05-11 12:24:06'),(7,'Taylor John','taylor.john@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61453456780','2026-05-11 12:19:12','2026-05-19 03:37:38'),(9,'Morgan Lee','morgan.lee@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61455678901','2026-05-11 12:19:12','2026-05-11 12:24:06'),(15,'Prof Michael Harrison','michael.harrison@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000001','2026-05-11 12:31:55','2026-05-11 12:31:55'),(16,'Prof Sarah Bennett','sarah.bennett@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000002','2026-05-11 12:31:55','2026-05-11 12:31:55'),(17,'Dr Andrew Collins','andrew.collins@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000003','2026-05-11 12:31:55','2026-05-11 12:31:55'),(18,'Dr Emily Walker','emily.walker@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000004','2026-05-11 12:31:55','2026-05-11 12:31:55'),(19,'Dr David Nguyen','david.nguyen@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000005','2026-05-11 12:31:55','2026-05-11 12:31:55'),(20,'Dr Olivia Martinez','olivia.martinez@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000006','2026-05-11 12:31:55','2026-05-11 12:31:55'),(21,'Dr James Carter','james.carter@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000007','2026-05-11 12:31:55','2026-05-11 12:31:55'),(22,'Dr Sophia Kim','sophia.kim@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000008','2026-05-11 12:31:55','2026-05-11 12:31:55'),(23,'Lecturer Mark Thompson','mark.thompson@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000009','2026-05-11 12:31:55','2026-05-11 12:31:55'),(24,'Lecturer Hannah Lee','hannah.lee@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000010','2026-05-11 12:31:55','2026-05-11 12:31:55'),(25,'Lecturer Ryan Patel','ryan.patel@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000011','2026-05-11 12:31:55','2026-05-11 12:31:55'),(26,'Lecturer Isabella Rossi','isabella.rossi@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000012','2026-05-11 12:31:55','2026-05-11 12:31:55'),(27,'Tutor Liam Scott','liam.scott@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000013','2026-05-11 12:31:55','2026-05-11 12:31:55'),(28,'Tutor Mia Johnson','mia.johnson@teacher.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','teacher','+61461000014','2026-05-11 12:31:55','2026-05-11 12:31:55'),(29,'Ethan Clarke','ethan.clarke@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61470000001','2026-05-11 13:00:54','2026-05-11 13:00:54'),(30,'Olivia Bennett','olivia.bennett@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61470000002','2026-05-11 13:00:54','2026-05-11 13:00:54'),(31,'Lucas Martin','lucas.martin@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61470000003','2026-05-11 13:00:54','2026-05-11 13:00:54'),(32,'Ava Wilson','ava.wilson@student.mmi.edu.au','$2a$10$PBw4vAQj6wnnWoTc/2HlTeL6d7sjmWdMVqrIs/JSg6GdYeHjufkF2','student','+61470000004','2026-05-11 13:00:54','2026-05-11 13:00:54'),(34,'John Carter','john.carter01@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000001','2026-05-13 07:22:34','2026-05-13 07:22:34'),(35,'Emily Johnson','emily.johnson02@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000002','2026-05-13 07:22:34','2026-05-13 07:22:34'),(36,'Michael Brown','michael.brown03@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000003','2026-05-13 07:22:34','2026-05-13 07:22:34'),(37,'Sophia Wilson','sophia.wilson04@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000004','2026-05-13 07:22:34','2026-05-13 07:22:34'),(38,'Daniel Smith','daniel.smith05@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000005','2026-05-13 07:22:34','2026-05-13 07:22:34'),(39,'Olivia Davis','olivia.davis06@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000006','2026-05-13 07:22:34','2026-05-13 07:22:34'),(40,'James Miller','james.miller07@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000007','2026-05-13 07:22:34','2026-05-13 07:22:34'),(41,'Ava Taylor','ava.taylor08@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000008','2026-05-13 07:22:34','2026-05-13 07:22:34'),(42,'William Anderson','william.anderson09@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000009','2026-05-13 07:22:34','2026-05-13 07:22:34'),(43,'Isabella Thomas','isabella.thomas10@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000010','2026-05-13 07:22:34','2026-05-13 07:22:34'),(44,'Ethan White','ethan.white11@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000011','2026-05-13 07:22:34','2026-05-13 07:22:34'),(45,'Mia Harris','mia.harris12@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000012','2026-05-13 07:22:34','2026-05-13 07:22:34'),(46,'Alexander Martin','alexander.martin13@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000013','2026-05-13 07:22:34','2026-05-13 07:22:34'),(47,'Charlotte Lee','charlotte.lee14@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000014','2026-05-13 07:22:34','2026-05-13 07:22:34'),(48,'Benjamin Clark','benjamin.clark15@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000015','2026-05-13 07:22:34','2026-05-13 07:22:34'),(49,'Amelia Lewis','amelia.lewis16@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000016','2026-05-13 07:22:34','2026-05-13 07:22:34'),(50,'Lucas Walker','lucas.walker17@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000017','2026-05-13 07:22:34','2026-05-13 07:22:34'),(51,'Harper Hall','harper.hall18@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000018','2026-05-13 07:22:34','2026-05-13 07:22:34'),(52,'Henry Allen','henry.allen19@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000019','2026-05-13 07:22:34','2026-05-13 07:22:34'),(53,'Evelyn Young','evelyn.young20@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000020','2026-05-13 07:22:34','2026-05-13 07:22:34'),(54,'Jack King','jack.king21@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000021','2026-05-13 07:22:34','2026-05-13 07:22:34'),(55,'Grace Wright','grace.wright22@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000022','2026-05-13 07:22:34','2026-05-13 07:22:34'),(56,'Samuel Scott','samuel.scott23@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000023','2026-05-13 07:22:34','2026-05-13 07:22:34'),(57,'Lily Green','lily.green24@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000024','2026-05-13 07:22:34','2026-05-13 07:22:34'),(58,'David Adams','david.adams25@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000025','2026-05-13 07:22:34','2026-05-13 07:22:34'),(59,'Zoe Baker','zoe.baker26@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000026','2026-05-13 07:22:34','2026-05-13 07:22:34'),(60,'Matthew Nelson','matthew.nelson27@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000027','2026-05-13 07:22:34','2026-05-13 07:22:34'),(61,'Chloe Carter','chloe.carter28@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000028','2026-05-13 07:22:34','2026-05-13 07:22:34'),(62,'Ryan Mitchell','ryan.mitchell29@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000029','2026-05-13 07:22:34','2026-05-13 07:22:34'),(63,'Ella Perez','ella.perez30@student.mmi.edu.au','$2a$10$6MfXLRw3o4RYcpS.kDGV5ecRKZbHXZFdkxeRvukjT.gneCLhMd4Xi','student','0400000030','2026-05-13 07:22:34','2026-05-13 07:22:34'),(64,'Karren Fernando','karren.fernando@admin.mmi.edu.au','$2a$10$sGuPV8SaC2ENXBVKLX5zbuLF2STtevmrjOkIv.AfWp9xWm6JDZw.2','admin','0455258985','2026-05-19 03:06:27','2026-05-19 03:06:27'),(65,'John Cyrus','alex@student.com','$2a$10$Pdj4YPxzTbS72.U6mnhZX.gZnLaTx2zDZ0YR8gcvUR9DhP3XN738u','student','0412345678','2026-05-19 07:36:56','2026-05-19 07:36:56'),(66,'Alex Teacher','john@teacher.com','$2a$10$vpGndSu7UpQyCE0p/o1kaehUiZ.Xp.e7OwrHp.LyNfD84ImCNEK9i','student','0498765432','2026-05-19 07:36:56','2026-05-19 07:36:56'),(67,'Admin User','admin@system.com','$2a$10$Bczfo3iItvxHi37n/8gnT.vUEawQbYJgwNoIQe5Oa4bIiaiBTOxku','student','0400000000','2026-05-19 07:36:56','2026-05-19 07:36:56');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-21 10:55:50
