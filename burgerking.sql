-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2019 at 11:46 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `burgerking`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustID` varchar(255) NOT NULL,
  `CustName` text NOT NULL,
  `CustContact` varchar(255) NOT NULL,
  `CustAddr` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustID`, `CustName`, `CustContact`, `CustAddr`) VALUES
('', '', '', ''),
('C001', 'Aditya Sinha', '8234940', 'Jaipur'),
('C002', 'Sneha', '8234941', 'Vadodara'),
('C003', 'Justin', '2345678', 'C-98, Shawn Appartment, NY');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `ItemID` varchar(255) NOT NULL,
  `ItemDes` text NOT NULL,
  `ItemPrice` double NOT NULL,
  `ItemType` varchar(255) NOT NULL,
  `ItemImage` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`ItemID`, `ItemDes`, `ItemPrice`, `ItemType`, `ItemImage`) VALUES
('I001', 'Veg Whopper', 119, 'Burger', 'VegWhopper.jpg'),
('I002', 'Crispy Veg', 35, 'Burger', 'CrispyVeg.png'),
('I003', 'BK Veggie', 79, 'Burger', 'BKVeggie.png');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `OrderID` varchar(255) NOT NULL,
  `CustID` varchar(255) NOT NULL,
  `OrderDate` date NOT NULL,
  `OrderStatus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderID`, `CustID`, `OrderDate`, `OrderStatus`) VALUES
('O001', 'C001', '2019-04-20', 'PAID'),
('O002', 'C003', '2019-04-21', 'PAID'),
('O003', 'C003', '2019-04-21', 'PAID');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` varchar(255) NOT NULL,
  `OrderID` varchar(255) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `OrderID`, `Price`) VALUES
('P001', 'O001', 294),
('P002', 'O002', 158),
('P003', 'O004', 70),
('P004', 'O004', 35),
('P005', 'O003', 210);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` text NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `contactno` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `username`, `password`, `contactno`) VALUES
('Aditya Sinha', 'aditya', '123456', '9872347'),
('Aditya SInha', 'aditya99', '1111', '2348730'),
('BURGER KING', 'bk99', '12345', '25798720');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustID`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
