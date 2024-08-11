CREATE DATABASE  IF NOT EXISTS `movimientos-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `movimientos-db`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: movimientos-db
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
                          `numero_cuenta` varchar(255) NOT NULL,
                          `duenio_id` varchar(255) NOT NULL,
                          `estado` tinyint NOT NULL,
                          `saldo` double NOT NULL,
                          `tipo` tinyint NOT NULL,
                          PRIMARY KEY (`numero_cuenta`),
                          CONSTRAINT `cuenta_chk_1` CHECK ((`estado` between 0 and 1)),
                          CONSTRAINT `cuenta_chk_2` CHECK ((`tipo` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES ('0dfce29d-ac55-4749-96eb-a898fcb19351','67890',0,565,0),('6238a11c-ef3f-455f-ae31-3b370d6ef712','67890',0,1303,1),('802fd05e-1323-4004-a2d7-db21eeb01879','12345',1,1000,1),('d1fbc9da-cc6c-4b16-a67a-3f656191058a','67890',0,1000,0),('e508e1e9-0450-4852-8cca-3cb7d28cd5b5','12345',0,1132,0);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
                              `id` varchar(255) NOT NULL,
                              `cliente_id` varchar(255) NOT NULL,
                              `fecha` datetime(6) NOT NULL,
                              `saldo` double NOT NULL,
                              `tipo` tinyint NOT NULL,
                              `valor` double NOT NULL,
                              `numero_cuenta` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKk10u787s9re28fue9gdscb5kt` (`numero_cuenta`),
                              CONSTRAINT `FKk10u787s9re28fue9gdscb5kt` FOREIGN KEY (`numero_cuenta`) REFERENCES `cuenta` (`numero_cuenta`),
                              CONSTRAINT `movimiento_chk_1` CHECK ((`tipo` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES ('148d39eb-8b13-4207-90c1-5d22a7bf7a0f','67890','2024-08-11 00:16:11.495115',798,1,-202,'0dfce29d-ac55-4749-96eb-a898fcb19351'),('201c7c7f-5b3a-4f5a-9731-cef1dffffa4b','12345','2024-08-11 01:51:12.449564',565,0,-77,'0dfce29d-ac55-4749-96eb-a898fcb19351'),('607c67e4-3e9b-4762-a3c8-0c177c449f78','67890','2024-08-11 00:16:11.495153',1202,0,202,'6238a11c-ef3f-455f-ae31-3b370d6ef712'),('7e876822-be85-43a1-a6ca-31507195c1c2','67890','2024-08-11 01:05:13.603800',697,1,-101,'0dfce29d-ac55-4749-96eb-a898fcb19351'),('9f22ba79-977b-47d0-b56a-f55e00f10711','12345','2024-08-11 01:29:38.810555',642,0,-55,'0dfce29d-ac55-4749-96eb-a898fcb19351'),('c807edc2-b0b6-4f3f-bb19-e16e87678169','67890','2024-08-11 01:05:13.603834',1303,0,101,'6238a11c-ef3f-455f-ae31-3b370d6ef712'),('da27168e-8925-4bc8-ab71-fbbd57bf7d13','67890','2024-08-11 01:51:12.449601',1132,0,77,'e508e1e9-0450-4852-8cca-3cb7d28cd5b5'),('fce1756c-81b7-46c0-85d7-8601652a4a69','67890','2024-08-11 01:29:38.810627',1055,0,55,'e508e1e9-0450-4852-8cca-3cb7d28cd5b5');
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'movimientos-db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-11 10:31:39