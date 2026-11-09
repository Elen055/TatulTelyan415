create database bam;
use bam;
db_training_current.sql

100 %
-- phpMyAdmin SQL Dump
-- version 4.1.14.8
-- http://www.phpmyadmin.net
--
-- Host: db568322521.db.1and1.com
-- Generation Time: Mar 10, 2015 at 08:00 AM
-- Server version: 5.1.73-log
-- PHP Version: 5.4.36-0+deb7u3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE TABLE IF NOT EXISTS `Employees` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `First Name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `Last Name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `RegDate` datetime NOT NULL,
  `Gender` int(11) NOT NULL DEFAULT '1',
  `Salary` decimal(10,0) NOT NULL DEFAULT '200000',
  `NumberOfChildren` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=31 ;

--
-- Dumping data for table `Employees`
--

INSERT INTO `Employees` (`ID`, `First Name`, `Last Name`, `RegDate`, `Gender`, `Salary`, `NumberOfChildren`) VALUES
(1, 'Պետրոս', 'Մարտիրոսյան', '2014-03-28 00:00:00', 1, '200000', 5),
(2, 'Զաբել', 'Պապիկյան', '2014-03-09 00:00:00', 1, '200000', 1),
(3, 'Կարինե', 'Մարտիրոսյան', '2014-03-11 00:00:00', 2, '200000', 3),
(4, 'Դավիթ', 'Գարազմանյան', '2014-03-10 00:00:00', 1, '200000', 3),
(5, 'Զաքար', 'Զաքարյան', '2014-03-28 00:00:00', 1, '300000', 5),
(6, 'Գայանե', 'Մայանյան', '2014-03-24 00:00:00', 2, '300000', 3),
(8, 'Զաբել', 'Զաբելյան', '2014-03-28 00:00:00', 2, '340000', 5),
(9, 'Սարչապետ', 'Մարչապետյան', '2014-03-10 00:00:00', 1, '340000', 5),
(10, 'Սրբուհի', 'Աշոտյան', '2013-12-15 00:00:00', 2, '340000', 3),
(11, 'Հարևան', 'Վրացիդզե', '2013-09-08 00:00:00', 1, '340000', 3),
(13, 'Սրբուհի', 'Խելոքյանց', '2013-09-08 00:00:00', 1, '300000', 1),
(14, 'Վարդուհի', 'Խելոքյանունց', '2013-09-08 00:00:00', 1, '300000', 3),
(15, 'Սրբուհի', 'Խելոքյանց', '2014-03-27 00:00:00', 2, '300000', 3),
(16, 'Պետրոս', 'Պողոսյան', '2014-03-27 00:00:00', 1, '300000', 3),
(17, 'Գագո', 'Զադոյան', '2013-11-10 00:00:00', 1, '300000', 3),
(19, 'Անհայտ', 'Սեռյան', '2014-03-03 00:00:00', 0, '300000', 3),
(20, 'Բարդուղիմեոս', 'Պապիկյան', '2013-12-08 00:00:00', 1, '300000', 3),
(21, 'Մագա', 'Արատյան', '2014-06-26 00:00:00', 2, '500000', 2),
(22, 'Զառա', 'Մարատյան', '2014-06-26 00:00:00', 1, '500000', 0),
(23, 'Գերեգին', 'Մակարյան', '2014-01-26 00:00:00', 1, '320000', 0),
(24, 'Մարգար', 'Պլտոյան', '2012-05-22 00:00:00', 1, '421500', 0),
(25, 'Մագա', 'Մարգարյան', '2014-06-03 00:00:00', 2, '300000', 4),
(26, 'Մարինա', 'Դուռգա', '2014-06-26 00:00:00', 2, '150000', 5),
(27, 'Արտակ', 'Գրիգորյան', '2015-01-26 00:00:00', 1, '25000', 2),
(28, 'Մարտուն', 'Անթառանյան', '2015-02-26 00:00:00', 1, '170000', 6),
(29, 'Մարգարիտա', 'Ծաղիկյան', '2012-01-26 00:00:00', 2, '40000', 2),
(30, 'Պետրոս', 'Պողոսյան', '2015-03-11 00:00:00', 1, '25000', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


select * from Employees;