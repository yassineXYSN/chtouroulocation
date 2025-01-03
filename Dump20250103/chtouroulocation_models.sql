-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chtouroulocation
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `models` (
  `idmodel` int NOT NULL,
  `modelname` varchar(45) DEFAULT NULL,
  `idmake` int DEFAULT NULL,
  PRIMARY KEY (`idmodel`),
  KEY `fkmodelmake` (`idmake`),
  CONSTRAINT `fkmodelmake` FOREIGN KEY (`idmake`) REFERENCES `makes` (`idmake`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,'Corolla',1),(2,'Camry',1),(3,'RAV4',1),(4,'Highlander',1),(5,'F-150',2),(6,'Mustang',2),(7,'Escape',2),(8,'Explorer',2),(9,'Civic',3),(10,'Accord',3),(11,'CR-V',3),(12,'Pilot',3),(13,'3 Series',4),(14,'5 Series',4),(15,'X3',4),(16,'X5',4),(17,'C-Class',5),(18,'E-Class',5),(19,'GLC',5),(20,'GLE',5),(21,'A3',6),(22,'A4',6),(23,'Q5',6),(24,'Q7',6),(25,'Silverado',7),(26,'Malibu',7),(27,'Equinox',7),(28,'Traverse',7),(29,'Model S',8),(30,'Model 3',8),(31,'Model X',8),(32,'Model Y',8),(33,'Altima',9),(34,'Sentra',9),(35,'Rogue',9),(36,'Pathfinder',9),(37,'Elantra',10),(38,'Sonata',10),(39,'Tucson',10),(40,'Santa Fe',10);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-03 23:26:14
