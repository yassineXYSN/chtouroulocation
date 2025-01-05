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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `AddressPostal` varchar(45) NOT NULL,
  `mdp` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'yassine','chtourou','yassinechtourou03@gmail.com','Bardo 2000','admin123'),(2,'admin','admin','admin@gmail.com','11 admin street','admin123'),(4,'a','a','a','a','a');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `idcars` int NOT NULL AUTO_INCREMENT,
  `color` varchar(45) NOT NULL,
  `age` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `availability` tinyint NOT NULL,
  `idmake` int DEFAULT NULL,
  `idmodel` int DEFAULT NULL,
  PRIMARY KEY (`idcars`),
  KEY `fkcarmake` (`idmake`),
  KEY `fkcarmodel` (`idmodel`),
  CONSTRAINT `fkcarmake` FOREIGN KEY (`idmake`) REFERENCES `makes` (`idmake`),
  CONSTRAINT `fkcarmodel` FOREIGN KEY (`idmodel`) REFERENCES `models` (`idmodel`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Red','2020',25000,0,1,1),(2,'Blue','2019',24000,0,1,2),(3,'White','2021',30000,0,1,3),(4,'Black','2018',35000,0,1,4),(5,'Red','2022',40000,0,2,5),(6,'Blue','2021',35000,0,2,6),(7,'White','2020',28000,0,2,7),(8,'Black','2019',32000,0,2,8),(9,'Red','2021',23000,0,3,9),(10,'Blue','2020',27000,0,3,10),(11,'White','2019',29000,0,3,11),(12,'Black','2018',31000,0,3,12),(13,'Red','2020',41000,0,4,13),(14,'Blue','2021',45000,0,4,14),(15,'White','2019',47000,0,4,15),(16,'Black','2022',50000,0,4,16),(17,'Red','2021',42000,0,5,17),(18,'Blue','2020',48000,0,5,18),(19,'White','2019',46000,0,5,19),(20,'Black','2022',52000,0,5,20),(21,'Red','2021',38000,0,6,21),(22,'Blue','2020',40000,0,6,22),(23,'White','2019',45000,0,6,23),(24,'Black','2022',47000,0,6,24),(25,'Red','2021',35000,0,7,25),(26,'Blue','2020',31000,0,7,26),(27,'White','2019',33000,0,7,27),(28,'Black','2022',37000,1,7,28),(29,'Red','2022',95000,0,8,29),(30,'Blue','2021',55000,0,8,30),(31,'White','2020',80000,0,8,31),(32,'Black','2019',60000,1,8,32),(33,'Red','2021',27000,0,9,33),(34,'Blue','2020',22000,1,9,34),(35,'White','2019',30000,0,9,35),(36,'Black','2022',32000,1,9,36),(37,'Red','2021',20000,0,10,37),(38,'Blue','2020',25000,1,10,38),(39,'White','2019',27000,0,10,39),(40,'Black','2022',30000,1,10,40),(41,'Grey','2021',32000,1,1,1),(42,'Silver','2020',34000,1,2,6),(43,'Yellow','2022',36000,1,8,30),(44,'Green','2019',28000,1,4,14),(45,'Orange','2021',41000,1,7,25),(46,'Beige','2020',39000,1,5,18),(47,'Purple','2019',30000,1,3,11),(48,'Brown','2022',46000,1,6,24),(49,'Pink','2021',28000,1,9,35),(50,'White','2020',31000,1,10,38),(51,'Silver','2',1000,1,10,39);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `AddressPostal` varchar(45) NOT NULL,
  `mdp` varchar(45) NOT NULL,
  `nbrents` int(10) unsigned zerofill DEFAULT '0000000000',
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'vvv','bbb','aaaa','eee','bb',0000000001),(2,'vvv','bbb','yassinechtourou03@gmail.com','eee','bb',0000000002),(3,'amine','kalai','kalai@gmail.com','ghazela','amine123',0000000001),(7,'c','c','c','c','c',0000000015),(8,'yassine','chtourou','yassinechtourou3@gmail.com','bardo','123',0000000000);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `makes`
--

DROP TABLE IF EXISTS `makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `makes` (
  `idmake` int NOT NULL AUTO_INCREMENT,
  `makename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmake`),
  UNIQUE KEY `makename_UNIQUE` (`makename`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `makes`
--

LOCK TABLES `makes` WRITE;
/*!40000 ALTER TABLE `makes` DISABLE KEYS */;
INSERT INTO `makes` VALUES (6,'Audi'),(4,'BMW'),(7,'Chevrolet'),(2,'Ford'),(3,'Honda'),(10,'Hyundai'),(5,'Mercedes-Benz'),(9,'Nissan'),(8,'Tesla'),(1,'Toyota');
/*!40000 ALTER TABLE `makes` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `rents`
--

DROP TABLE IF EXISTS `rents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rents` (
  `idrent` int NOT NULL AUTO_INCREMENT,
  `iduser` int DEFAULT NULL,
  `idcar` int DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  PRIMARY KEY (`idrent`),
  KEY `fk_rent_user_idx` (`idcar`),
  KEY `fk_rent_client` (`iduser`),
  CONSTRAINT `fk_rent_car` FOREIGN KEY (`idcar`) REFERENCES `cars` (`idcars`),
  CONSTRAINT `fk_rent_client` FOREIGN KEY (`iduser`) REFERENCES `clients` (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rents`
--

LOCK TABLES `rents` WRITE;
/*!40000 ALTER TABLE `rents` DISABLE KEYS */;
INSERT INTO `rents` VALUES (5,7,24,'2025-01-05','2025-01-06'),(6,7,26,'2025-01-05','2025-01-06'),(7,7,30,'2025-01-05','2025-01-06');
/*!40000 ALTER TABLE `rents` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-05 15:56:19
