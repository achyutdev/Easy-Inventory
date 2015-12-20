-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2015 at 07:30 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `inventorymgtsys`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `selling_price` double DEFAULT NULL,
  `profit` double DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `tag`, `name`, `company`, `cost_price`, `selling_price`, `profit`, `status`) VALUES
(1, '131312s', 'Milk', 'Daber', 341, 32, NULL, 0),
(2, '3423a', 'Tea', 'Jupitor', 123.435, 624, NULL, 0),
(3, '484AOSGGF1', 'Laptop', 'Asus', 23.432, 24.36928, NULL, 0),
(4, '739184ew', 'Trimer', 'Jpt-J', 123.34, 345, NULL, 0),
(12, '3IURSDNNK', 'Moffeee', 'TestMart', 32.99, 33.319900000000004, 0.3299000000000021, 1),
(13, 'KIOFG2B0TV', 'dgs', 'twert', 231, 233.31, 2.3100000000000023, 0),
(14, 'Q9116BQFCV', 'Test Pro', 'Ptotp', 432, 444.96, 12.95999999999998, 0),
(15, 'IPKVDTC4I5', 'Trunk', 'Cisco', 321.9, 337.995, 16.095000000000027, 0),
(17, 'I3K1Q1FOMJ', 'Hard Drive', 'HP', 324, 324, 0, 1),
(18, 'JVVUTQTTPH', 'Electric Bulb', 'GE', 34, 41.14, 2.5600000000000023, 7),
(19, 'T9L3SMD13J', 'HDMI Cable', 'Dev Electric', 69.09, 72.5445, 3.454499999999996, 0),
(20, 'TPO88Q4FB6', 'HeadSet', 'Beat', 325, 344.5, 19.5, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
