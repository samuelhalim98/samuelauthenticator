-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 06, 2019 at 07:06 AM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id9170894_authenticator`
--

-- --------------------------------------------------------

--
-- Table structure for table `AREA`
--

CREATE TABLE `AREA` (
  `AREAID` int(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `LOCATION` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AREA`
--

INSERT INTO `AREA` (`AREAID`, `NAME`, `PHONE`, `ADDRESS`, `LOCATION`) VALUES
(1, 'Aman Central', '01934558488', 'Jalan Alor Setar', 'Alor Setar'),
(2, 'Cafe Merah', '0156766456', 'University Utara Malaysia', 'Sintok'),
(3, 'C-Mart', '01944674445', 'Jalan Changlun', 'Changlun');

-- --------------------------------------------------------

--
-- Table structure for table `CART`
--

CREATE TABLE `CART` (
  `ORDERID` int(10) NOT NULL,
  `USERNAME` varchar(10) NOT NULL,
  `USERID` varchar(40) NOT NULL,
  `CODE` varchar(30) NOT NULL,
  `STATUS` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CART`
--

INSERT INTO `CART` (`ORDERID`, `USERNAME`, `USERID`, `CODE`, `STATUS`) VALUES
(1, '2', 'US - 001', 'XYZXZ', 'READY');

-- --------------------------------------------------------

--
-- Table structure for table `CODE`
--

CREATE TABLE `CODE` (
  `USERNAME` int(5) NOT NULL,
  `USERID` varchar(50) NOT NULL,
  `CODE` varchar(5) NOT NULL,
  `STATUS` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CODE`
--

INSERT INTO `CODE` (`USERNAME`, `USERID`, `CODE`, `STATUS`) VALUES
(0, '1', 'XYZXZ', 'READY'),
(0, '2', 'POKLX', 'READY'),
(0, '3', 'XXQWE', 'NOT READY');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `EMAIL` varchar(100) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `LOCATION` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `LOCATION`) VALUES
('abc@gmail.com', '5975676ae179641188b2bde3c8d545d8334991f6', '0194702439', 'Ahmad Hanis', 'Changlun'),
('abcd@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '01234545959', 'John', 'Changlun'),
('slumberjer@gmail.com', '1b64dad048eda4f2a22621490c0ea7a1db37ad43', '0194702493', 'Hanis', 'All'),
('ahmad@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '01934455765', 'Ahmad', 'All'),
('qq1819301928@gmail.com', '0c8134c9a330eac5a89c4f18bcfe77e4780be309', '01135747336', 'Yang', 'Sintok'),
('samuel', 'samuel', '0517263528', 'Samuel', 'UUM'),
('stephensamuelhalim@gmail.com', 'Eskopi77', '081219191820', 'Stephen Samuel Halim', 'Semarang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CART`
--
ALTER TABLE `CART`
  ADD PRIMARY KEY (`ORDERID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
