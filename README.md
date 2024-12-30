Before proceeding, ensure you have the following installed on your system:

Java Development Kit (JDK 8 or later)
MySQL Server
JDBC driver for MySQL
Database Setup
Create the Database and Tables
Open your MySQL client (e.g., MySQL Workbench or the command-line interface) and execute the following SQL commands:

sql
CREATE DATABASE chtouroulocation /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;  

CREATE TABLE makes (  
    idmake INT NOT NULL AUTO_INCREMENT,  
    makename VARCHAR(45) DEFAULT NULL,  
    PRIMARY KEY (idmake),  
    UNIQUE KEY makename_UNIQUE (makename)  
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;  

CREATE TABLE models (  
    idmodel INT NOT NULL,  
    modelname VARCHAR(45) DEFAULT NULL,  
    idmake INT DEFAULT NULL,  
    PRIMARY KEY (idmodel),  
    KEY fkmodelmake (idmake),  
    CONSTRAINT fkmodelmake FOREIGN KEY (idmake) REFERENCES makes (idmake)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;  

CREATE TABLE cars (  
    idcars INT NOT NULL AUTO_INCREMENT,  
    color VARCHAR(45) NOT NULL,  
    age VARCHAR(45) NOT NULL,  
    price FLOAT NOT NULL,  
    availability TINYINT NOT NULL,  
    idmake INT DEFAULT NULL,  
    idmodel INT DEFAULT NULL,  
    PRIMARY KEY (idcars),  
    KEY fkcarmake (idmake),  
    KEY fkcarmodel (idmodel),  
    CONSTRAINT fkcarmake FOREIGN KEY (idmake) REFERENCES makes (idmake),  
    CONSTRAINT fkcarmodel FOREIGN KEY (idmodel) REFERENCES models (idmodel)  
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;  

CREATE TABLE admins (  
    idusers INT NOT NULL AUTO_INCREMENT,  
    name VARCHAR(45) NOT NULL,  
    lastname VARCHAR(45) NOT NULL,  
    email VARCHAR(45) NOT NULL,  
    AddressPostal VARCHAR(45) NOT NULL,  
    mdp VARCHAR(45) NOT NULL,  
    PRIMARY KEY (idusers)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;  

CREATE TABLE clients (  
    idusers INT NOT NULL AUTO_INCREMENT,  
    name VARCHAR(45) NOT NULL,  
    lastname VARCHAR(45) NOT NULL,  
    email VARCHAR(45) NOT NULL,  
    AddressPostal VARCHAR(45) NOT NULL,  
    mdp VARCHAR(45) NOT NULL,  
    nbrents INT(10) UNSIGNED ZEROFILL DEFAULT '0000000000',  
    PRIMARY KEY (idusers)  
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;  
Verify the Database
Confirm that the database chtouroulocation and its tables (makes, models, cars, admins, clients) were created successfully.

insert statements

-- Insert makes
INSERT INTO makes (idmake, makename) VALUES
(1, 'Toyota'),
(2, 'Ford'),
(3, 'Honda'),
(4, 'BMW'),
(5, 'Mercedes-Benz'),
(6, 'Audi'),
(7, 'Chevrolet'),
(8, 'Tesla'),
(9, 'Nissan'),
(10, 'Hyundai');

-- Insert models
INSERT INTO models (idmodel, modelname, idmake) VALUES
-- Toyota models
(1, 'Corolla', 1),
(2, 'Camry', 1),
(3, 'RAV4', 1),
(4, 'Highlander', 1),

-- Ford models
(5, 'F-150', 2),
(6, 'Mustang', 2),
(7, 'Escape', 2),
(8, 'Explorer', 2),

-- Honda models
(9, 'Civic', 3),
(10, 'Accord', 3),
(11, 'CR-V', 3),
(12, 'Pilot', 3),

-- BMW models
(13, '3 Series', 4),
(14, '5 Series', 4),
(15, 'X3', 4),
(16, 'X5', 4),

-- Mercedes-Benz models
(17, 'C-Class', 5),
(18, 'E-Class', 5),
(19, 'GLC', 5),
(20, 'GLE', 5),

-- Audi models
(21, 'A3', 6),
(22, 'A4', 6),
(23, 'Q5', 6),
(24, 'Q7', 6),

-- Chevrolet models
(25, 'Silverado', 7),
(26, 'Malibu', 7),
(27, 'Equinox', 7),
(28, 'Traverse', 7),

-- Tesla models
(29, 'Model S', 8),
(30, 'Model 3', 8),
(31, 'Model X', 8),
(32, 'Model Y', 8),

-- Nissan models
(33, 'Altima', 9),
(34, 'Sentra', 9),
(35, 'Rogue', 9),
(36, 'Pathfinder', 9),

-- Hyundai models
(37, 'Elantra', 10),
(38, 'Sonata', 10),
(39, 'Tucson', 10),
(40, 'Santa Fe', 10);

INSERT INTO cars (idcars, color, age, price, availability, idmake, idmodel) VALUES
(1, 'Red', '2020', 25000.0, 1, 1, 1), -- Toyota Corolla
(2, 'Blue', '2019', 24000.0, 1, 1, 2), -- Toyota Camry
(3, 'White', '2021', 30000.0, 1, 1, 3), -- Toyota RAV4
(4, 'Black', '2018', 35000.0, 1, 1, 4), -- Toyota Highlander
(5, 'Red', '2022', 40000.0, 1, 2, 5), -- Ford F-150
(6, 'Blue', '2021', 35000.0, 1, 2, 6), -- Ford Mustang
(7, 'White', '2020', 28000.0, 1, 2, 7), -- Ford Escape
(8, 'Black', '2019', 32000.0, 1, 2, 8), -- Ford Explorer
(9, 'Red', '2021', 23000.0, 1, 3, 9), -- Honda Civic
(10, 'Blue', '2020', 27000.0, 1, 3, 10), -- Honda Accord
(11, 'White', '2019', 29000.0, 1, 3, 11), -- Honda CR-V
(12, 'Black', '2018', 31000.0, 1, 3, 12), -- Honda Pilot
(13, 'Red', '2020', 41000.0, 1, 4, 13), -- BMW 3 Series
(14, 'Blue', '2021', 45000.0, 1, 4, 14), -- BMW 5 Series
(15, 'White', '2019', 47000.0, 1, 4, 15), -- BMW X3
(16, 'Black', '2022', 50000.0, 1, 4, 16), -- BMW X5
(17, 'Red', '2021', 42000.0, 1, 5, 17), -- Mercedes-Benz C-Class
(18, 'Blue', '2020', 48000.0, 1, 5, 18), -- Mercedes-Benz E-Class
(19, 'White', '2019', 46000.0, 1, 5, 19), -- Mercedes-Benz GLC
(20, 'Black', '2022', 52000.0, 1, 5, 20), -- Mercedes-Benz GLE
(21, 'Red', '2021', 38000.0, 1, 6, 21), -- Audi A3
(22, 'Blue', '2020', 40000.0, 1, 6, 22), -- Audi A4
(23, 'White', '2019', 45000.0, 1, 6, 23), -- Audi Q5
(24, 'Black', '2022', 47000.0, 1, 6, 24), -- Audi Q7
(25, 'Red', '2021', 35000.0, 1, 7, 25), -- Chevrolet Silverado
(26, 'Blue', '2020', 31000.0, 1, 7, 26), -- Chevrolet Malibu
(27, 'White', '2019', 33000.0, 1, 7, 27), -- Chevrolet Equinox
(28, 'Black', '2022', 37000.0, 1, 7, 28), -- Chevrolet Traverse
(29, 'Red', '2022', 95000.0, 1, 8, 29), -- Tesla Model S
(30, 'Blue', '2021', 55000.0, 1, 8, 30), -- Tesla Model 3
(31, 'White', '2020', 80000.0, 1, 8, 31), -- Tesla Model X
(32, 'Black', '2019', 60000.0, 1, 8, 32), -- Tesla Model Y
(33, 'Red', '2021', 27000.0, 1, 9, 33), -- Nissan Altima
(34, 'Blue', '2020', 22000.0, 1, 9, 34), -- Nissan Sentra
(35, 'White', '2019', 30000.0, 1, 9, 35), -- Nissan Rogue
(36, 'Black', '2022', 32000.0, 1, 9, 36), -- Nissan Pathfinder
(37, 'Red', '2021', 20000.0, 1, 10, 37), -- Hyundai Elantra
(38, 'Blue', '2020', 25000.0, 1, 10, 38), -- Hyundai Sonata
(39, 'White', '2019', 27000.0, 1, 10, 39), -- Hyundai Tucson
(40, 'Black', '2022', 30000.0, 1, 10, 40), -- Hyundai Santa Fe
(41, 'Grey', '2021', 32000.0, 1, 1, 1), -- Toyota Corolla
(42, 'Silver', '2020', 34000.0, 1, 2, 6), -- Ford Mustang
(43, 'Yellow', '2022', 36000.0, 1, 8, 30), -- Tesla Model 3
(44, 'Green', '2019', 28000.0, 1, 4, 14), -- BMW 5 Series
(45, 'Orange', '2021', 41000.0, 1, 7, 25), -- Chevrolet Silverado
(46, 'Beige', '2020', 39000.0, 1, 5, 18), -- Mercedes-Benz E-Class
(47, 'Purple', '2019', 30000.0, 1, 3, 11), -- Honda CR-V
(48, 'Brown', '2022', 46000.0, 1, 6, 24), -- Audi Q7
(49, 'Pink', '2021', 28000.0, 1, 9, 35), -- Nissan Rogue
(50, 'White', '2020', 31000.0, 1, 10, 38); -- Hyundai Sonata

Java Configuration
Set Up JDBC Connection
Open the JDBC class located in the src/classes directory of the project. Locate line 10:

java
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chtouroulocation", "root", "yassine123Y");  
Update the database URL, username, and password according to your MySQL setup.

Example:

java
Connection con = DriverManager.getConnection("jdbc:mysql://<your-host>:<your-port>/<your-database>", "<your-username>", "<your-password>");  
Running the Project
Compile and run the main class in your Java project.
The application will connect to the chtouroulocation database and interact with the tables you created.
Troubleshooting
Database Connection Issues:
Ensure the database server is running and that the JDBC URL, username, and password in the JDBC class are correct.

Missing Tables or Data:
Verify that all SQL commands were executed successfully during the database setup phase.

License
This project is for educational purposes only.
