-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2024 at 08:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `contact_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `kontak`
--

CREATE TABLE `kontak` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nomor_telepon` varchar(15) NOT NULL,
  `kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kontak`
--

INSERT INTO `kontak` (`id`, `nama`, `nomor_telepon`, `kategori`) VALUES
(1, 'Andi Prasetyo', '081234567890', 'Teman'),
(2, 'Budi Santoso', '082345678901', 'Keluarga'),
(3, 'Citra Dewi', '083456789012', 'Rekan Kerja'),
(4, 'Dani Ramadhan', '084567890123', 'Teman'),
(5, 'Eka Putri', '085678901234', 'Keluarga'),
(6, 'Fajar Alamsyah', '086789012345', 'Teman'),
(7, 'Gina Sari', '087890123456', 'Rekan Kerja'),
(8, 'Hendra Saputra', '088901234567', 'Teman'),
(9, 'Ika Wulandari', '089012345678', 'Keluarga'),
(10, 'Joko Widodo', '081098765432', 'Teman'),
(11, 'Kiki Handayani', '082109876543', 'Rekan Kerja'),
(12, 'Lia Nuraini', '083210987654', 'Teman'),
(13, 'Martha Sari', '084321098765', 'Keluarga'),
(14, 'Nina Oktavia', '085432109876', 'Rekan Kerja'),
(15, 'Omar Fadillah', '086543210987', 'Teman'),
(16, 'Putu Ayu', '087654321098', 'Keluarga'),
(17, 'Rudi Hidayat', '088765432109', 'Teman'),
(18, 'Sari Wati', '089876543210', 'Rekan Kerja'),
(19, 'Tina Melati', '081234567891', 'Teman'),
(20, 'Umar Basuki', '082345678902', 'Keluarga'),
(21, 'Vina Ramadhani', '083456789013', 'Rekan Kerja'),
(22, 'Wawan Setiawan', '084567890124', 'Teman'),
(23, 'Xena Amalia', '085678901235', 'Keluarga'),
(24, 'Yudi Hartono', '086789012346', 'Teman'),
(25, 'Zara Fitriani', '087890123457', 'Rekan Kerja'),
(26, 'Aldo Pramono', '088901234568', 'Teman'),
(27, 'Bella Oktaviani', '089012345679', 'Keluarga'),
(28, 'Cindy Kusuma', '081098765433', 'Teman'),
(29, 'Dodi Maulana', '082109876544', 'Rekan Kerja'),
(30, 'Eko Prasetyo', '083210987655', 'Teman'),
(31, 'Fani Susanti', '084321098766', 'Keluarga'),
(32, 'Gilang Rahardjo', '085432109877', 'Rekan Kerja'),
(33, 'Hanafi Kurnia', '086543210988', 'Teman'),
(34, 'Iwan Setiawan', '087654321099', 'Keluarga'),
(35, 'Jasmine Alia', '088765432100', 'Teman'),
(36, 'Kamal Anwar', '089876543211', 'Rekan Kerja'),
(37, 'Lukas Purnama', '081234567892', 'Teman'),
(38, 'Mila Rahmawati', '082345678903', 'Keluarga'),
(39, 'Nadia Zahra', '083456789014', 'Rekan Kerja'),
(40, 'Oki Nurhayati', '084567890125', 'Teman'),
(41, 'Pavan Dwi', '085678901236', 'Keluarga'),
(42, 'Rina Supardi', '086789012347', 'Teman'),
(43, 'Sandi Mahardika', '087890123458', 'Rekan Kerja'),
(44, 'Tari Ningsih', '088901234569', 'Teman'),
(49, 'Andi Prasetyo', '081234567890', 'Teman'),
(50, 'Budi Santoso', '082345678901', 'Keluarga'),
(51, 'Citra Dewi', '083456789012', 'Rekan Kerja'),
(52, 'Dani Ramadhan', '084567890123', 'Teman'),
(53, 'Eka Putri', '085678901234', 'Keluarga');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kontak`
--
ALTER TABLE `kontak`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kontak`
--
ALTER TABLE `kontak`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
