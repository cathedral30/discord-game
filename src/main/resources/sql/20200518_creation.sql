CREATE DATABASE  IF NOT EXISTS `discord` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `discord`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: discord
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bodyparts`
--

DROP TABLE IF EXISTS `bodyparts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bodyparts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(30) NOT NULL,
  `hpm` int(11) DEFAULT NULL,
  `hm` int(11) DEFAULT NULL,
  `dm` int(11) DEFAULT NULL,
  `am` int(11) DEFAULT NULL,
  `sam` int(11) DEFAULT NULL,
  `ham` int(11) DEFAULT NULL,
  `pm` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodyparts`
--

LOCK TABLES `bodyparts` WRITE;
/*!40000 ALTER TABLE `bodyparts` DISABLE KEYS */;
INSERT INTO `bodyparts` VALUES (1,'Human Leg','L',1,1,1,1,1,1,1),(2,'Human Arm','A',1,1,1,1,1,1,1),(3,'Human Torso','T',1,1,1,1,1,1,1),(4,'Human Head','H',1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `bodyparts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creatureparts`
--

DROP TABLE IF EXISTS `creatureparts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `creatureparts` (
  `creature_id` int(11) DEFAULT NULL,
  `part_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creatureparts`
--

LOCK TABLES `creatureparts` WRITE;
/*!40000 ALTER TABLE `creatureparts` DISABLE KEYS */;
INSERT INTO `creatureparts` VALUES (1,4),(1,3),(1,2),(1,2),(1,2),(1,1);
/*!40000 ALTER TABLE `creatureparts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creatures`
--

DROP TABLE IF EXISTS `creatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `creatures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `hp` int(11) DEFAULT NULL,
  `hardness` int(11) DEFAULT NULL,
  `dodge` int(11) DEFAULT NULL,
  `armour` int(11) DEFAULT NULL,
  `sAttack` int(11) DEFAULT NULL,
  `hAttack` int(11) DEFAULT NULL,
  `piercing` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creatures`
--

LOCK TABLES `creatures` WRITE;
/*!40000 ALTER TABLE `creatures` DISABLE KEYS */;
INSERT INTO `creatures` VALUES (1,7,'Razoires',3,6,9,9,7,8,6);
/*!40000 ALTER TABLE `creatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endnames`
--

DROP TABLE IF EXISTS `endnames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `endnames` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endnames`
--

LOCK TABLES `endnames` WRITE;
/*!40000 ALTER TABLE `endnames` DISABLE KEYS */;
INSERT INTO `endnames` VALUES (1,'d'),(2,'ed'),(3,'ark'),(4,'arc'),(5,'es'),(6,'er'),(7,'der'),(8,'tron'),(9,'med'),(10,'ure'),(11,'zur'),(12,'cred'),(13,'mur');
/*!40000 ALTER TABLE `endnames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `midnames`
--

DROP TABLE IF EXISTS `midnames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `midnames` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `midnames`
--

LOCK TABLES `midnames` WRITE;
/*!40000 ALTER TABLE `midnames` DISABLE KEYS */;
INSERT INTO `midnames` VALUES (1,'air'),(2,'ir'),(3,'mi'),(4,'sor'),(5,'mee'),(6,'clo'),(7,'red'),(8,'cra'),(9,'ark'),(10,'arc'),(11,'miri'),(12,'lori'),(13,'cres'),(14,'mur'),(15,'zer'),(16,'marac'),(17,'zoir'),(18,'slamar'),(19,'salmar'),(20,'urak');
/*!40000 ALTER TABLE `midnames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `bucks` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'test',0),(7,'The_Real_Cooper',0);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `startnames`
--

DROP TABLE IF EXISTS `startnames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `startnames` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `startnames`
--

LOCK TABLES `startnames` WRITE;
/*!40000 ALTER TABLE `startnames` DISABLE KEYS */;
INSERT INTO `startnames` VALUES (1,'Kr'),(2,'Ca'),(3,'Ra'),(4,'Mrok'),(5,'Cru'),(6,'Ray'),(7,'Bre'),(8,'Zed'),(9,'Drak'),(10,'Mor'),(11,'Jag'),(12,'Mer'),(13,'Jar'),(14,'Mjol');
/*!40000 ALTER TABLE `startnames` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-18 23:47:30
