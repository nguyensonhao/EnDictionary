-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2020 at 04:37 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `haonsce140386`
--

-- --------------------------------------------------------

--
-- Table structure for table `meaning`
--

CREATE TABLE `meaning` (
  `wid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  `mText` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `meaning`
--

INSERT INTO `meaning` (`wid`, `tid`, `mText`) VALUES
(7, 1, 'A person whose job involves designing and building engines, machines, roads, bridges, etc'),
(8, 1, 'A person who has been trained in medical science, whose job is to treat people who are ill or injured'),
(9, 1, 'A person whose job is to take care of sick or injured people, usually in a hospital\na registered nurse'),
(10, 1, 'An official organization whose job is to make people obey the law and to prevent and solve crime; the people who work for this organization'),
(11, 1, 'A game played by two teams of 11 players, using a round ball which players kick up and down the playing field. Teams try to kick the ball into the other team’s goal.'),
(12, 1, 'A round fruit with shiny red or green skin that is fairly hard and white inside'),
(14, 1, 'An animal with four legs and a tail, often kept as a pet or trained for work, for example hunting or guarding buildings. There are many types of dog, some of which are wild.'),
(15, 1, 'A creature that is covered with feathers and has two wings and two legs. Most birds can fly.'),
(16, 1, 'A small animal with soft fur that people often keep as a pet. Cats catch and kill birds and mice.'),
(17, 1, 'An electronic machine that can store, organize and find information, do processes with numbers and other data, and control other machines');

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `tId` int(11) NOT NULL,
  `tText` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`tId`, `tText`) VALUES
(3, 'adjectives'),
(4, 'adverbs'),
(1, 'nouns'),
(5, 'pronouns');

-- --------------------------------------------------------

--
-- Table structure for table `word`
--

CREATE TABLE `word` (
  `wid` int(11) NOT NULL,
  `wText` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `word`
--

INSERT INTO `word` (`wid`, `wText`) VALUES
(12, 'Apple'),
(15, 'Bird'),
(16, 'Cat'),
(17, 'Computer'),
(8, 'Doctor'),
(14, 'Dog'),
(7, 'Engineer'),
(9, 'Nurse'),
(10, 'Police'),
(11, 'Soccer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `meaning`
--
ALTER TABLE `meaning`
  ADD KEY `fk01_m` (`wid`),
  ADD KEY `fk02_m` (`tid`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`tId`),
  ADD UNIQUE KEY `tText` (`tText`);

--
-- Indexes for table `word`
--
ALTER TABLE `word`
  ADD PRIMARY KEY (`wid`),
  ADD UNIQUE KEY `wText` (`wText`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `tId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `word`
--
ALTER TABLE `word`
  MODIFY `wid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `meaning`
--
ALTER TABLE `meaning`
  ADD CONSTRAINT `fk01_m` FOREIGN KEY (`wid`) REFERENCES `word` (`wid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk02_m` FOREIGN KEY (`tid`) REFERENCES `type` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
