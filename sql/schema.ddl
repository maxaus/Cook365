-- MySQL dump 10.13  Distrib 5.1.30, for Win32 (ia32)
--
-- Host: localhost    Database: ml_fld_265a
-- ------------------------------------------------------
-- Server version	5.1.30-community

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

--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `measure_unit`
--
DROP TABLE IF EXISTS `measure_unit`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `measure_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `ratio` int(6),
  `parentId` bigint(20),
  PRIMARY KEY (`id`),
  KEY `fk_measure_unit_parent` (`parentId`),
  CONSTRAINT `fk_measure_unit_parent` FOREIGN KEY (`parentId`) REFERENCES `measure_unit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product`
--
DROP TABLE IF EXISTS `product`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `liquid` bit(1) NOT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `recipe`
--
DROP TABLE IF EXISTS `recipe`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `recipe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `shortDescription` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `date` datetime NOT NULL,
  `imagePath` varchar(255),
  `imageType` varchar(100),
  `externalLink` varchar(100),
  `type` varchar(45) NOT NULL,
  `vegetarian` bit(1) NOT NULL,
  `moderated` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ingredient`
--
DROP TABLE IF EXISTS `ingredient`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `measureUnitId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  `recipeId` bigint(20) NOT NULL,
  `value` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ingredient_product` (`productId`),
  KEY `fk_ingredient_recipe` (`recipeId`),
  CONSTRAINT `fk_ingredient_measure_unit` FOREIGN KEY (`measureUnitId`) REFERENCES `measure_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_product` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_recipe` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;







