-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 02, 2019 at 01:34 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `company`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(200) NOT NULL,
  `employee_national_id_no` int(20) NOT NULL,
  `employee_photograph` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_name`, `employee_national_id_no`, `employee_photograph`) VALUES
(1, 'amen', 1234566, '127.0.0.1/Co/users/user1.png'),
(2, 'javaid', 1234558, '127.0.0.1/Co/users/user2.png');

-- --------------------------------------------------------

--
-- Table structure for table `employeeAssignments`
--

CREATE TABLE `employeeAssignments` (
  `employee_id` int(20) NOT NULL,
  `assignment_name` varchar(200) NOT NULL,
  `assignment_description` text NOT NULL,
  `start_date` varchar(20) NOT NULL,
  `end_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employeeAssignments`
--

INSERT INTO `employeeAssignments` (`employee_id`, `assignment_name`, `assignment_description`, `start_date`, `end_date`) VALUES
(1, 'chick the connect', 'this is a test', '2/11/2019', '10/11/2019'),
(2, 'chick emps', 'this is a test', '5/11/2019', '20/11/2019');

-- --------------------------------------------------------

--
-- Table structure for table `employeeAssignments_copy`
--

CREATE TABLE `employeeAssignments_copy` (
  `employee_id` int(20) NOT NULL,
  `assignment_name` varchar(200) NOT NULL,
  `assignment_description` text NOT NULL,
  `start_date` varchar(20) NOT NULL,
  `end_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employeeAssignments_copy`
--

INSERT INTO `employeeAssignments_copy` (`employee_id`, `assignment_name`, `assignment_description`, `start_date`, `end_date`) VALUES
(1, 'chick the connect', 'this is a test', '2/11/2019', '10/11/2019'),
(2, 'chick emps', 'this is a test', '5/11/2019', '20/11/2019');

-- --------------------------------------------------------

--
-- Table structure for table `employee_copy`
--

CREATE TABLE `employee_copy` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(200) NOT NULL,
  `employee_national_id_no` int(20) NOT NULL,
  `employee_photograph` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee_copy`
--

INSERT INTO `employee_copy` (`employee_id`, `employee_name`, `employee_national_id_no`, `employee_photograph`) VALUES
(1, 'amen', 1234566, '127.0.0.1/Co/users/user1.png'),
(2, 'javaid', 1234558, '127.0.0.1/Co/users/user2.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
