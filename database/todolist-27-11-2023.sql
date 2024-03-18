-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.3.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for todolist
CREATE DATABASE IF NOT EXISTS `todolist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `todolist`;

-- Dumping structure for table todolist.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(500) NOT NULL,
  `admin_id` int(11) unsigned NOT NULL,
  `task_id` int(11) unsigned NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comments_task` (`task_id`) USING BTREE,
  KEY `fk_comments_admin` (`admin_id`) USING BTREE,
  CONSTRAINT `fk_comments_admin` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_comments_task` FOREIGN KEY (`task_id`) REFERENCES `work` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table todolist.comments: ~6 rows (approximately)
DELETE FROM `comments`;
INSERT INTO `comments` (`id`, `text`, `admin_id`, `task_id`, `user_id`, `date`) VALUES
	(71, '<p><strong>Haven\'t finished listening yet!!</strong></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 06:56:05'),
	(72, '<p><strong>Please complete the listening and then switch back to the status done.</strong></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 06:56:26'),
	(75, '<p><span style="color: rgb(224, 62, 45);"><strong>Not completed, please try again!</strong></span></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 08:47:17'),
	(78, '<p><span style="color: rgb(224, 62, 45);"><strong>Complete this task before December 9, 2023.</strong></span></p>', 1, 61, 'dungnnqe170175@fpt.edu.vn', '2023-11-26 03:56:47'),
	(79, '<p><span style="color: rgb(224, 62, 45);"><strong>Change the app\'s user interface!</strong></span></p>', 1, 63, 'tranngocsang9a3@gmail.com', '2023-11-26 04:10:40'),
	(80, '<p><span style="text-decoration: underline; color: rgb(224, 62, 45);"><strong>Help me finish it soon!</strong></span></p>\r\n<div class="ripple-wave active">&nbsp;</div>', 1, 57, 'tranngocsang9a3@gmail.com', '2023-11-26 11:45:09');

-- Dumping structure for table todolist.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  `firstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.users: ~7 rows (approximately)
DELETE FROM `users`;
INSERT INTO `users` (`id`, `username`, `password`, `status`, `firstName`, `lastName`, `role`) VALUES
	(1, 'sangtnqe170193@fpt.edu.vn', '$2a$12$Tn1IRY95Y84v.AgiFwI4.uDW7n.lhEgdrSz8jA8QOUdUC7OyRfDM.', 1, 'Admin', 'Administration', 2),
	(2, 'nguyenhoangvu1201@gmail.com', '$2a$12$TX92mDogC2sP5h0ugsiBK.YgfGN/9SIID.GNN7YRZTYfJGwn0XITa', 2, 'Nguyen Hoang ', 'Vu', 1),
	(3, 'dungnnqe170175@fpt.edu.vn', '$2a$12$.NYZNeDLPbJK2NX5d2xZ0.Fie0xPMudNgie8irbSMKgY/XzzUNGkO', 1, 'Nguyen Ngoc ', 'Dung', 1),
	(4, 'hunglmqe170213@fpt.edu.vn', '$2a$12$3e7snHttf9Dwa4I4Z8eH2ulGmW0lKH3RxOHTe54RMfBTZHM/DBIKO', 1, 'Le Manh', 'Hung', 1),
	(5, 'sangpdpqe170196@fpt.edu.vn', '$2a$12$BjeW47qjn8TCXFaX96Iux.a1CgYo3Lb/oETgmSSMqpNfoUFBQoTbm', 1, 'Pham Dinh', 'Phuong Sang', 1),
	(6, 'oanhdhqs170130@fpt.edu.vn', '$2a$12$hMZM8gefFi7aXbBdTTRv2OaOtLHn5XO589L607BzvfveMn.KWH7QS', 1, 'Dang Hoang ', 'Oanh', 1),
	(7, 'tranngocsang9a3@gmail.com', '$2a$12$2JuYI/24OI1908tXIJxsau0XRCw.1LbluNq39fu.wnJOZSSUBSEka', 1, 'Tran ', 'Sang', 1);

-- Dumping structure for table todolist.view
CREATE TABLE IF NOT EXISTS `view` (
  `viewed` int(10) unsigned NOT NULL,
  PRIMARY KEY (`viewed`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.view: ~1 rows (approximately)
DELETE FROM `view`;
INSERT INTO `view` (`viewed`) VALUES
	(370);

-- Dumping structure for table todolist.work
CREATE TABLE IF NOT EXISTS `work` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `created_date` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `updated_date` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `priority` int(10) unsigned NOT NULL,
  `due` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.work: ~58 rows (approximately)
DELETE FROM `work`;
INSERT INTO `work` (`id`, `name`, `description`, `status`, `created_by`, `updated_by`, `created_date`, `updated_date`, `priority`, `due`) VALUES
	(1, 'Learn Japanese', 'Everyday', 2, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:13:04', '2023-11-22 02:13:25', 1, '2023-11-30'),
	(2, 'Stay up late', 'Coding', 1, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:14:37', '2023-11-22 02:31:48', 0, '2023-11-30'),
	(3, 'Eating late at night', 'Afraid of fat', 0, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:15:23', '2023-11-22 02:31:55', 0, '2023-11-30'),
	(4, 'House cleaning', 'Everyday', 3, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:16:31', '2023-11-22 02:18:13', 1, '2024-01-31'),
	(5, 'Fitness', '17h - 19h', 2, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:17:26', '2023-11-22 02:32:01', 0, '2023-12-23'),
	(7, 'Gaming', '15h - 22h', 0, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:19:43', '2023-11-22 02:34:26', 0, '2024-01-27'),
	(8, 'Play football', '20h - 21h', 1, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:34:20', '', 0, '2023-12-10'),
	(9, 'Dish washing', 'Everyday', 2, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:35:10', '', 0, '2023-12-31'),
	(10, 'Learning PRJ', 'THU - 23/11', 3, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:36:36', '', 0, '2023-11-23'),
	(11, 'Listening to music', '2 days 1 times', 2, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:53:45', '', 0, '2023-12-10'),
	(12, 'PRF192', 'Programming Fundamentals	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:56:05', '', 0, '2023-11-23'),
	(13, 'CSI104', 'Introduction to Computer	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:56:25', '', 0, '2023-11-23'),
	(14, 'CEA201', 'Computer Organization and Architecture	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:56:47', '', 0, '2023-11-23'),
	(15, 'MAE101', 'Mathematics for Engineering	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:57:11', '', 0, '2023-11-23'),
	(16, 'SSL101c', 'Academic Skills for University Success	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:57:32', '', 0, '2023-11-23'),
	(17, 'SSG104', 'Communication and In-Group Working Skills	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:57:51', '', 0, '2023-11-23'),
	(18, 'OSG202', 'Operating Systems	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:58:07', '', 0, '2023-11-23'),
	(19, 'NWC203c', 'Computer Networking	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:58:44', '', 0, '2023-11-23'),
	(20, 'PRO192', 'Object-Oriented Programming	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:59:00', '', 0, '2023-11-23'),
	(21, 'MAD101', 'Discrete mathematics	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:59:20', '', 0, '2023-11-23'),
	(22, 'WED201c', 'Web Design	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:59:41', '', 0, '2023-11-23'),
	(23, 'LAB211', 'OOP with Java Lab	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 02:59:54', '', 0, '2023-11-23'),
	(24, 'CSD201', 'Data Structures and Algorithms	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:00:08', '', 0, '2023-11-23'),
	(25, 'JPD113', 'Elementary Japanese 1- A1.1	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:00:43', '', 0, '2023-11-23'),
	(26, 'PRJ301', 'Java Web Application Development	', 2, 'dungnnqe170175@fpt.edu.vn', 'dungnnqe170175@fpt.edu.vn', '2023-11-22 03:01:06', '2023-11-22 03:01:13', 1, '2023-12-09'),
	(27, 'SWE201c', 'Introduction to Software Engineering	', 2, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:01:42', '', 1, '2023-12-03'),
	(28, 'IOT102', 'Internet of Things	', 3, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:02:54', '', 1, '2023-11-23'),
	(29, 'MAS291', 'Statistics & Probability	', 3, 'dungnnqe170175@fpt.edu.vn', 'dungnnqe170175@fpt.edu.vn', '2023-11-22 03:03:15', '2023-11-22 03:04:03', 1, '2023-11-23'),
	(30, 'JPD123', 'Elementary Japanese 1-A1.2	', 0, 'dungnnqe170175@fpt.edu.vn', 'dungnnqe170175@fpt.edu.vn', '2023-11-22 03:04:27', '2023-11-25 05:20:02', 1, '2023-11-26'),
	(31, 'SWR302', 'Software Requirement	', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:06:10', '', 0, '2024-01-01'),
	(32, 'SWT301', 'Software Testing	', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:06:32', '', 0, '2024-01-03'),
	(33, 'SWP391', 'Software development project	', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:06:53', '', 0, '2024-01-03'),
	(34, 'ITE302c', 'Ethics in IT	', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:07:21', '', 0, '2024-01-03'),
	(35, 'SE_COM*1	', 'Select Combo 1	', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:07:51', '', 0, '2024-01-03'),
	(36, 'OJT202', 'On-The-Job Training	', 0, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:08:27', '', 0, '2024-05-01'),
	(37, 'SYB302c', 'Entrepreneurship', 0, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 03:08:49', '', 0, '2024-05-01'),
	(38, 'Have dinner', '19h - 19h30', 2, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 06:07:19', '2023-11-22 08:28:47', 0, '2023-11-23'),
	(39, 'Anh Sinh Cuu Em', 'Bug roi anh', 3, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 08:31:58', '', 0, '2023-11-23'),
	(40, 'Anh Sinh Cuu Em', 'Bug roi anh', 3, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 08:32:08', '', 0, '2023-11-23'),
	(41, 'Anh Sinh Cuu Em', 'Bug roi anh', 3, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 08:32:17', '', 0, '2023-11-23'),
	(42, 'Play FC online', '22h - 23h', 2, 'hunglmqe170213@fpt.edu.vn', '', '2023-11-22 10:41:42', '', 0, '2023-11-23'),
	(43, 'Fix bug', 'Everyday', 2, 'hunglmqe170213@fpt.edu.vn', '', '2023-11-22 10:42:55', '', 1, '2023-11-23'),
	(44, 'Project PRJ', 'Login, Register', 1, 'hunglmqe170213@fpt.edu.vn', '', '2023-11-22 10:43:28', '', 0, '2023-12-09'),
	(45, 'MAS291', 'Study and final', 3, 'hunglmqe170213@fpt.edu.vn', '', '2023-11-22 10:44:31', '', 0, '2023-11-23'),
	(46, 'Travel to Sapa', 'two day', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 11:07:58', '', 0, '2023-11-28'),
	(47, 'Buy IP15', 'Future ', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-22 11:11:29', '', 0, '2023-11-23'),
	(48, 'Play FC', 'Everyday', 2, 'sangpdpqe170196@fpt.edu.vn', 'sangpdpqe170196@fpt.edu.vn', '2023-11-22 11:26:52', '2023-11-22 11:27:00', 0, '2024-04-27'),
	(49, 'Open the player card', 'Everyday', 2, 'sangpdpqe170196@fpt.edu.vn', '', '2023-11-22 11:27:58', '', 0, '2024-04-16'),
	(51, 'PRJ projects', '', 2, 'hunglmqe170213@fpt.edu.vn', '', '2023-11-23 10:45:45', '', 1, '2023-12-09'),
	(52, 'Listen', 'Academic Writing Skills	', 2, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-23 08:08:27', '', 0, '2023-11-24'),
	(53, 'LAB211', '900 LOC', 2, 'sangpdpqe170196@fpt.edu.vn', 'sangpdpqe170196@fpt.edu.vn', '2023-11-23 08:24:26', '2023-11-23 08:24:30', 0, '2023-11-24'),
	(54, 'Stay up late', 'Everyday', 0, 'sangpdpqe170196@fpt.edu.vn', '', '2023-11-23 08:24:53', '', 1, '2023-11-24'),
	(57, 'Edit interface', '', 1, 'tranngocsang9a3@gmail.com', '', '2023-11-25 11:25:14', '', 0, '2023-11-26'),
	(61, 'Import Excel', '1 week', 1, 'dungnnqe170175@fpt.edu.vn', '', '2023-11-26 03:55:28', '', 1, '2023-12-09'),
	(63, 'Change User interface', '3 days', 2, 'tranngocsang9a3@gmail.com', 'tranngocsang9a3@gmail.com', '2023-11-26 04:09:40', '2023-11-26 10:52:08', 1, '2023-11-29'),
	(87, 'Comment function', 'Submit', 3, 'tranngocsang9a3@gmail.com', 'tranngocsang9a3@gmail.com', '2023-11-25 11:23:24', '2023-11-26 10:51:08', 0, '2024-01-26'),
	(90, 'Teamwork SWE', 'Week 5 ', 2, 'tranngocsang9a3@gmail.com', 'tranngocsang9a3@gmail.com', '2023-11-26 03:46:39', '2023-11-26 10:49:09', 0, '2023-12-27'),
	(91, 'Buy IP15', 'Future ', 1, 'tranngocsang9a3@gmail.com', 'tranngocsang9a3@gmail.com', '2023-11-26 04:07:04', '2023-11-26 10:48:52', 0, '2023-12-13');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
