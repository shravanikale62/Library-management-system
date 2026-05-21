-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: library_db
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2,'prakash','it',8,'Computer organization and architechture'),(3,'R.V.Sharma','CS',25,'DCN'),(4,'J.E. Gordon','CS',15,'Structures'),(5,'Adel s.Sedra','Electronics engineering',20,'Microelectronics'),(6,'Thomas h.Cormen','Computer and Software engineering',20,'Introduction to algorithms'),(7,'Robert c.Martin','Computer and Software engineering',25,'Clean code'),(8,'James F.Kurose','Computer and Software engineering',30,'Computer Networking'),(9,'Richard M.Felder','Chemical engineering',20,'Element Principal of Chemical processes'),(10,'J.M.Smith','Chemical engineering',20,'Chemical Engineering Kinetics'),(11,'John d.Anderson','Aerospace engineering',9,'Introduction to flight'),(12,'John d.Anderson','Aerospace engineering',16,'Fundamentals of Aerodynamics');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_books`
--

DROP TABLE IF EXISTS `issue_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue_books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_title` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `fine_amount` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_books`
--

LOCK TABLES `issue_books` WRITE;
/*!40000 ALTER TABLE `issue_books` DISABLE KEYS */;
INSERT INTO `issue_books` VALUES (1,'coa','2026-04-11','2026-05-11','Returned','Shravani kale',0),(2,'coa','2026-05-23','2026-07-22','Returned','neha tiwari',0),(3,'DCN','2026-05-11','2026-06-11','Returned','Rekha Bhatt',0),(4,'DAA','2026-05-11','2026-06-11','Returned','Riya Sawant',0),(5,'CD','2026-03-22','2026-05-11','Returned','Sima Thakur',50),(6,'Introduction to Flight','2026-04-11','2026-05-14','Returned','Shravani kale',35);
/*!40000 ALTER TABLE `issue_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'shravanikale62@gmail.com','Shravani Kale','8855800297','1234','Student'),(2,'shravanikale62@gmail.com','Shravani Kale','8855800297','1234','Student'),(3,'shravanikale62@gmail.com','Shravani Kale','8855800297','1234','Student'),(4,'shravanikale62@gmail.com','Shravani Kale','8855800297','1234','Student'),(5,'shravanikale62@gmail.com','Shravani Kale','8855800297','1234','Student'),(6,'neha12@gmail.com','Neha tiwari','234567778889','1234','Student'),(7,'priyanka123@gmail.com','priyanka dikshit','123243456','1234','Student'),(8,'shweta@gmail.com','shweta kamal','1234567','1234','Admin'),(9,'admin@gmail.com','Admin','9999999999','admin123','Admin'),(10,'pranali@gmail.com','Pranali Kangte','1111111111','1234','Student');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-21 20:53:05
