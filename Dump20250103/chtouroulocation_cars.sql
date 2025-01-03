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
INSERT INTO `cars` VALUES (1,'Red','2020',25000,0,1,1),(2,'Blue','2019',24000,0,1,2),(3,'White','2021',30000,0,1,3),(4,'Black','2018',35000,0,1,4),(5,'Red','2022',40000,0,2,5),(6,'Blue','2021',35000,1,2,6),(7,'White','2020',28000,0,2,7),(8,'Black','2019',32000,0,2,8),(9,'Red','2021',23000,0,3,9),(10,'Blue','2020',27000,1,3,10),(11,'White','2019',29000,0,3,11),(12,'Black','2018',31000,1,3,12),(13,'Red','2020',41000,0,4,13),(14,'Blue','2021',45000,1,4,14),(15,'White','2019',47000,0,4,15),(16,'Black','2022',50000,1,4,16),(17,'Red','2021',42000,0,5,17),(18,'Blue','2020',48000,1,5,18),(19,'White','2019',46000,0,5,19),(20,'Black','2022',52000,1,5,20),(21,'Red','2021',38000,0,6,21),(22,'Blue','2020',40000,1,6,22),(23,'White','2019',45000,0,6,23),(24,'Black','2022',47000,1,6,24),(25,'Red','2021',35000,0,7,25),(26,'Blue','2020',31000,1,7,26),(27,'White','2019',33000,0,7,27),(28,'Black','2022',37000,1,7,28),(29,'Red','2022',95000,0,8,29),(30,'Blue','2021',55000,1,8,30),(31,'White','2020',80000,0,8,31),(32,'Black','2019',60000,1,8,32),(33,'Red','2021',27000,0,9,33),(34,'Blue','2020',22000,1,9,34),(35,'White','2019',30000,0,9,35),(36,'Black','2022',32000,1,9,36),(37,'Red','2021',20000,0,10,37),(38,'Blue','2020',25000,1,10,38),(39,'White','2019',27000,0,10,39),(40,'Black','2022',30000,1,10,40),(41,'Grey','2021',32000,1,1,1),(42,'Silver','2020',34000,1,2,6),(43,'Yellow','2022',36000,1,8,30),(44,'Green','2019',28000,1,4,14),(45,'Orange','2021',41000,1,7,25),(46,'Beige','2020',39000,1,5,18),(47,'Purple','2019',30000,1,3,11),(48,'Brown','2022',46000,1,6,24),(49,'Pink','2021',28000,1,9,35),(50,'White','2020',31000,1,10,38),(51,'Silver','2',1000,1,10,39);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
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
