Before executing the main class you should create the database
to create copy this lines and execute them in my sql
CREATE DATABASE `chtouroulocation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `makes` (
  `idmake` int NOT NULL AUTO_INCREMENT,
  `makename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmake`),
  UNIQUE KEY `makename_UNIQUE` (`makename`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `models` (
  `idmodel` int NOT NULL,
  `modelname` varchar(45) DEFAULT NULL,
  `idmake` int DEFAULT NULL,
  PRIMARY KEY (`idmodel`),
  KEY `fkmodelmake` (`idmake`),
  CONSTRAINT `fkmodelmake` FOREIGN KEY (`idmake`) REFERENCES `makes` (`idmake`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `admins` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `AddressPostal` varchar(45) NOT NULL,
  `mdp` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clients` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `AddressPostal` varchar(45) NOT NULL,
  `mdp` varchar(45) NOT NULL,
  `nbrents` int(10) unsigned zerofill DEFAULT '0000000000',
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

then you go to the jdbc class in src/classes and change this line :
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chtouroulocation","root","yassine123Y");
with the information from your sql 
