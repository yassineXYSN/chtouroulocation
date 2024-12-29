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
