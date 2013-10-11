-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: imperial_tracking
-- ------------------------------------------------------
-- Server version	5.6.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


/* DROP DATABASE if EXISTS `zs6erb_logging`; */

CREATE DATABASE `zs6erb_logging`;
use `zs6erb_logging`;


--
-- Table structure for table `power`
--

DROP TABLE IF EXISTS `power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power` (
  `power_id` int(11) NOT NULL AUTO_INCREMENT,
  `power` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`power_id`),
  UNIQUE KEY `power_id_UNIQUE` (`power_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power`
--

LOCK TABLES `power` WRITE;
/*!40000 ALTER TABLE `power` DISABLE KEYS */;
INSERT INTO `power` VALUES (1,'50');
/*!40000 ALTER TABLE `power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `band`
--

DROP TABLE IF EXISTS `band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `band` (
  `band_id` int(11) NOT NULL AUTO_INCREMENT,
  `band` varchar(45) DEFAULT NULL,
  `start_range` decimal(6,3) DEFAULT NULL,
  `end_range` decimal(6,3) DEFAULT NULL,
  PRIMARY KEY (`band_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `band`
--

LOCK TABLES `band` WRITE;
/*!40000 ALTER TABLE `band` DISABLE KEYS */;
INSERT INTO `band` VALUES (1,'70cm','430','440'),(2,'2m','144','146'),(3,'4m','70','70.3'),(4,'6m','50','54'),(5,'7m','40.675','40.685'),(6,'10m','28','30'),(7,'12m','24.89','24.99'),(8,'15m','21','21.45'),(9,'17m','18.068','18.168'),(10,'20m','14','14.35'),(11,'30m','10.1','10.15'),(12,'40m','7','7.2'),(13,'60m','3.5','3.8'),(14,'160m','1.81','1.85');
/*!40000 ALTER TABLE `band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mode`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `callsign` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `pwd_` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT 1,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `callsign_UNIQUE` (`callsign`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mode`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ZS6ERB','ERB','ERB','SVw5O/WdNsoFp11NmxsuOlMKnEY=','q3ND0bxW7dQ=', '7'),(2,'ZS6FDX','Francois','Reyneke','FVUaQEdFnSZhlsPcuY2424SA8DA=','xY6fI/mur9w=','7'),(3,'ZS6SR','Sean','Riley','gkBHpcSwUYa6g6vUvxOveZ6oOuQ=','emNnQJLEqds=','1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mode`
--

DROP TABLE IF EXISTS `mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mode` (
  `mode_id` int(11) NOT NULL AUTO_INCREMENT,
  `mode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mode`
--

LOCK TABLES `mode` WRITE;
/*!40000 ALTER TABLE `mode` DISABLE KEYS */;
INSERT INTO `mode` VALUES (1,'SSB'),(2,'AM'),(3,'FM'),(4,'CW'),(5,'RTTY'),(6,'PSK-31'),(7,'SSTV');
/*!40000 ALTER TABLE `mode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `callsign` varchar(15) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `freq` varchar(10) DEFAULT NULL,
  `band_id` int(11) DEFAULT NULL,
  `mode_id` int(11) DEFAULT NULL,
  `power_id` int(11) DEFAULT NULL,
  `local_rst` int(11) DEFAULT NULL,
  `remote_rst` int(11) DEFAULT NULL,
  `accOther` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contests`
--

DROP TABLE IF EXISTS `contests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contests` (
  `contest_id` int(11) NOT NULL AUTO_INCREMENT,
  `contest_name` varchar(50) DEFAULT NULL,
  `contest_startdate` datetime NOT NULL,
  `contest_enddate` datetime NOT NULL,
  `contest_start_qso` int (11) DEFAULT NULL,
  PRIMARY KEY (`contest_id`),
  UNIQUE KEY `contest_id_UNIQUE` (`contest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-14 14:35:32
