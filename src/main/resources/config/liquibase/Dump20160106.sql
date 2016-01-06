CREATE DATABASE  IF NOT EXISTS `hongjie` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hongjie`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: hongjie
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `brand_story`
--

DROP TABLE IF EXISTS `brand_story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_story` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `slide1` varchar(255) DEFAULT NULL,
  `slide2` varchar(255) DEFAULT NULL,
  `slide3` varchar(255) DEFAULT NULL,
  `detail_info` varchar(5000) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `order_tag` varchar(45) DEFAULT NULL,
  `placeholder1` varchar(255) DEFAULT NULL,
  `placeholder2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_story`
--

LOCK TABLES `brand_story` WRITE;
/*!40000 ALTER TABLE `brand_story` DISABLE KEYS */;
INSERT INTO `brand_story` VALUES (22,'关于我们','关于我们','1452073733682_7873bd5e-4b6d-42c9-8f9f-eee619f22e97_slide-us.png',NULL,NULL,'<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1452073787287_ff41d023-370a-42c1-ab57-98f25dee97f4_us.png	\" style=\"height:800px; margin-left:160px; margin-right:160px; width:972px\" /></p>\n',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `brand_story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2015-11-20 16:10:35',1,'EXECUTED','7:db3ea54c411eee3e3a699d954d8ce557','createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint','',NULL,'3.4.1',NULL,NULL),('20151120074754','jhipster','classpath:config/liquibase/changelog/20151120074754_added_entity_Dictionary.xml','2015-11-20 16:10:40',2,'EXECUTED','7:5126f2ff161838342f30fe1cb7241e90','createTable','',NULL,'3.4.1',NULL,NULL);
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'','2015-12-28 15:25:50','WERN11150 (10.28.71.93)');
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionary`
--

DROP TABLE IF EXISTS `dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identifer` varchar(255) DEFAULT NULL,
  `ovalue` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary`
--

LOCK TABLES `dictionary` WRITE;
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
INSERT INTO `dictionary` VALUES (3,'Level','Level 1','Level 1'),(4,'Level','Level 2','Level 2');
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gift` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `detail_info` varchar(5000) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `order_tag` varchar(45) DEFAULT NULL,
  `placeholder1` varchar(255) DEFAULT NULL,
  `placeholder2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift`
--

LOCK TABLES `gift` WRITE;
/*!40000 ALTER TABLE `gift` DISABLE KEYS */;
INSERT INTO `gift` VALUES (22,'红酒专用酒刀','红酒专用酒刀',39.60,'1452071967532_7a6fad50-43d4-40b1-b8c4-a353d3b45cab_gift.png','<h1>进口红酒专用酒刀 开瓶器 红酒工具 卡门酒刀</h1>\n\n<p>鸿杰获奖红酒专营 原瓶进口 礼品红酒专家</p>\n','<ul>\n	<li>商品名称：进口红酒专用酒刀 开瓶器 红酒工具 卡门酒刀</li>\n	<li>商品编号：1056597498</li>\n	<li>店铺：&nbsp;<a href=\"http://hongjie.jd.com/\" target=\"_blank\">鸿杰获奖红酒专营店</a></li>\n	<li>上架时间：2015-09-08 09:52:42</li>\n	<li>商品毛重：1.5kg</li>\n	<li>商品产地：法国</li>\n	<li>原产地：法国</li>\n	<li>特性：酒杯/酒具</li>\n	<li>类型：红葡萄酒</li>\n	<li>葡萄品种：赤霞珠（Cabernet Sauvig</li>\n</ul>\n',1,'0',NULL,NULL);
/*!40000 ALTER TABLE `gift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qr_code` varchar(255) DEFAULT NULL,
  `wechat_subscribe_code` varchar(255) DEFAULT NULL,
  `wechat_service_code` varchar(255) DEFAULT NULL,
  `weibo_url` varchar(255) DEFAULT NULL,
  `qq_url` varchar(255) DEFAULT NULL,
  `placeholder1` varchar(255) DEFAULT NULL,
  `placeholder2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES (7,'1452071138268_c783f517-f1ec-4c78-a826-8e2d4d165701_1055531162683199992.jpg','1452071143194_36bfb33c-9748-4290-902c-31c25864630a__F5C0306.jpg','1452071145363_db12ea4c-6f0b-45de-9011-aabb0f07db5f_IMG_20121111_122416.jpg',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(255) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2015-11-20 08:10:51','AUTHENTICATION_SUCCESS'),(2,'admin','2015-11-20 08:10:51','AUTHENTICATION_SUCCESS'),(3,'admin','2015-11-20 08:10:51','AUTHENTICATION_SUCCESS'),(4,'admin','2015-11-20 08:25:57','AUTHENTICATION_SUCCESS'),(5,'admin','2015-11-23 08:03:07','AUTHENTICATION_SUCCESS'),(6,'admin','2015-11-23 08:50:33','AUTHENTICATION_SUCCESS'),(7,'admin','2015-11-23 09:49:35','AUTHENTICATION_SUCCESS'),(8,'admin','2015-11-23 09:49:36','AUTHENTICATION_SUCCESS'),(9,'admin','2015-11-23 09:49:36','AUTHENTICATION_SUCCESS'),(10,'admin','2015-11-24 09:00:51','AUTHENTICATION_SUCCESS'),(11,'admin','2015-11-24 09:23:23','AUTHENTICATION_SUCCESS'),(12,'admin','2015-11-24 09:23:23','AUTHENTICATION_SUCCESS'),(13,'admin','2015-11-24 09:23:24','AUTHENTICATION_SUCCESS'),(14,'admin','2015-11-24 09:25:27','AUTHENTICATION_SUCCESS'),(15,'admin','2015-11-24 09:25:27','AUTHENTICATION_SUCCESS'),(16,'admin','2015-11-24 09:25:27','AUTHENTICATION_SUCCESS'),(17,'admin','2015-11-24 10:32:37','AUTHENTICATION_SUCCESS'),(18,'admin','2015-11-26 03:06:16','AUTHENTICATION_SUCCESS'),(19,'admin','2015-11-26 03:20:03','AUTHENTICATION_SUCCESS'),(20,'admin','2015-11-26 03:27:08','AUTHENTICATION_SUCCESS'),(21,'admin','2015-11-26 06:04:50','AUTHENTICATION_SUCCESS'),(22,'admin','2015-11-26 06:39:53','AUTHENTICATION_SUCCESS'),(23,'admin','2015-11-26 06:56:15','AUTHENTICATION_SUCCESS'),(24,'admin','2015-11-26 08:01:23','AUTHENTICATION_SUCCESS'),(25,'admin','2015-11-26 08:38:08','AUTHENTICATION_SUCCESS'),(26,'admin','2015-11-26 08:47:21','AUTHENTICATION_SUCCESS'),(27,'admin','2015-11-26 08:47:21','AUTHENTICATION_SUCCESS'),(28,'admin','2015-11-26 08:47:21','AUTHENTICATION_SUCCESS'),(29,'admin','2015-11-26 09:11:57','AUTHENTICATION_SUCCESS'),(30,'admin','2015-11-26 09:50:28','AUTHENTICATION_SUCCESS'),(31,'admin','2015-11-27 07:15:27','AUTHENTICATION_SUCCESS'),(32,'admin','2015-11-30 06:30:35','AUTHENTICATION_SUCCESS'),(33,'admin','2015-11-30 06:58:54','AUTHENTICATION_SUCCESS'),(34,'admin','2015-11-30 07:06:58','AUTHENTICATION_SUCCESS'),(35,'admin','2015-11-30 07:12:28','AUTHENTICATION_SUCCESS'),(36,'admin','2015-11-30 07:12:28','AUTHENTICATION_SUCCESS'),(37,'admin','2015-11-30 07:12:28','AUTHENTICATION_SUCCESS'),(38,'admin','2015-11-30 07:47:50','AUTHENTICATION_SUCCESS'),(39,'admin','2015-11-30 07:47:50','AUTHENTICATION_SUCCESS'),(40,'admin','2015-11-30 07:47:50','AUTHENTICATION_SUCCESS'),(41,'admin','2015-11-30 07:55:25','AUTHENTICATION_SUCCESS'),(42,'admin','2015-11-30 09:10:48','AUTHENTICATION_SUCCESS'),(43,'admin','2015-11-30 09:10:48','AUTHENTICATION_SUCCESS'),(44,'admin','2015-11-30 09:10:48','AUTHENTICATION_SUCCESS'),(45,'admin','2015-12-01 03:43:10','AUTHENTICATION_SUCCESS'),(46,'admin','2015-12-01 06:34:50','AUTHENTICATION_SUCCESS'),(47,'admin','2015-12-01 07:20:36','AUTHENTICATION_SUCCESS'),(48,'admin','2015-12-02 06:17:52','AUTHENTICATION_SUCCESS'),(49,'admin','2015-12-02 06:17:53','AUTHENTICATION_SUCCESS'),(50,'admin','2015-12-02 06:17:55','AUTHENTICATION_SUCCESS'),(51,'admin','2015-12-03 09:52:30','AUTHENTICATION_SUCCESS'),(52,'admin','2015-12-03 09:52:30','AUTHENTICATION_SUCCESS'),(53,'admin','2015-12-03 09:52:31','AUTHENTICATION_SUCCESS'),(54,'admin','2015-12-04 06:19:29','AUTHENTICATION_SUCCESS'),(55,'admin','2015-12-04 06:19:29','AUTHENTICATION_SUCCESS'),(56,'admin','2015-12-04 06:19:29','AUTHENTICATION_SUCCESS'),(57,'admin','2015-12-07 08:31:57','AUTHENTICATION_SUCCESS'),(58,'admin','2015-12-07 09:02:05','AUTHENTICATION_SUCCESS'),(59,'admin','2015-12-07 09:29:15','AUTHENTICATION_SUCCESS'),(60,'admin','2015-12-08 06:31:54','AUTHENTICATION_SUCCESS'),(61,'admin','2015-12-08 06:43:37','AUTHENTICATION_SUCCESS'),(62,'admin','2015-12-08 09:14:18','AUTHENTICATION_SUCCESS'),(63,'admin','2015-12-08 09:29:14','AUTHENTICATION_SUCCESS'),(64,'admin','2015-12-09 05:55:25','AUTHENTICATION_SUCCESS'),(65,'admin','2015-12-09 07:03:31','AUTHENTICATION_SUCCESS'),(66,'admin','2015-12-09 09:57:55','AUTHENTICATION_SUCCESS'),(67,'admin','2015-12-09 09:57:55','AUTHENTICATION_SUCCESS'),(68,'admin','2015-12-10 09:32:37','AUTHENTICATION_SUCCESS'),(69,'admin','2015-12-14 05:53:29','AUTHENTICATION_SUCCESS'),(70,'admin','2015-12-14 05:53:30','AUTHENTICATION_SUCCESS'),(71,'admin','2015-12-14 08:50:31','AUTHENTICATION_SUCCESS'),(72,'admin','2015-12-14 09:35:18','AUTHENTICATION_SUCCESS'),(73,'admin','2015-12-16 08:23:03','AUTHENTICATION_SUCCESS'),(74,'admin','2015-12-17 03:28:45','AUTHENTICATION_SUCCESS'),(75,'admin','2015-12-17 05:44:50','AUTHENTICATION_SUCCESS'),(76,'admin','2015-12-17 07:05:16','AUTHENTICATION_SUCCESS'),(77,'admin','2015-12-17 08:27:05','AUTHENTICATION_SUCCESS'),(78,'admin','2015-12-17 08:27:05','AUTHENTICATION_SUCCESS'),(79,'admin','2015-12-17 09:08:12','AUTHENTICATION_SUCCESS'),(80,'admin','2015-12-17 09:08:12','AUTHENTICATION_SUCCESS'),(81,'admin','2015-12-17 09:21:21','AUTHENTICATION_SUCCESS'),(82,'admin','2015-12-17 09:21:22','AUTHENTICATION_SUCCESS'),(83,'admin','2015-12-17 09:23:13','AUTHENTICATION_SUCCESS'),(84,'admin','2015-12-17 09:23:13','AUTHENTICATION_SUCCESS'),(85,'admin','2015-12-17 10:18:07','AUTHENTICATION_SUCCESS'),(86,'admin','2015-12-17 10:18:07','AUTHENTICATION_SUCCESS'),(87,'admin','2015-12-18 03:06:34','AUTHENTICATION_SUCCESS'),(88,'admin','2015-12-18 03:06:34','AUTHENTICATION_SUCCESS'),(89,'admin','2015-12-18 06:29:18','AUTHENTICATION_SUCCESS'),(90,'admin','2015-12-18 08:25:30','AUTHENTICATION_SUCCESS'),(91,'admin','2015-12-18 09:53:12','AUTHENTICATION_SUCCESS'),(92,'admin','2015-12-18 09:53:12','AUTHENTICATION_SUCCESS'),(93,'admin','2015-12-21 07:21:38','AUTHENTICATION_SUCCESS'),(94,'admin','2015-12-21 07:21:38','AUTHENTICATION_SUCCESS'),(95,'admin','2015-12-21 08:32:18','AUTHENTICATION_SUCCESS'),(96,'admin','2015-12-21 08:49:57','AUTHENTICATION_SUCCESS'),(97,'admin','2015-12-21 09:03:35','AUTHENTICATION_SUCCESS'),(98,'admin','2015-12-21 10:01:53','AUTHENTICATION_SUCCESS'),(99,'admin','2015-12-28 03:30:34','AUTHENTICATION_SUCCESS'),(100,'admin','2015-12-28 06:43:09','AUTHENTICATION_SUCCESS'),(101,'admin','2015-12-28 07:26:14','AUTHENTICATION_SUCCESS'),(102,'admin','2015-12-28 07:39:53','AUTHENTICATION_SUCCESS'),(103,'admin','2015-12-28 07:43:10','AUTHENTICATION_SUCCESS'),(104,'admin','2015-12-28 07:59:55','AUTHENTICATION_SUCCESS'),(105,'admin','2015-12-28 08:59:52','AUTHENTICATION_SUCCESS'),(106,'admin','2015-12-28 08:59:52','AUTHENTICATION_SUCCESS'),(107,'admin','2015-12-29 02:07:48','AUTHENTICATION_SUCCESS'),(108,'admin','2015-12-29 02:07:48','AUTHENTICATION_SUCCESS'),(109,'admin','2015-12-29 02:47:36','AUTHENTICATION_SUCCESS'),(110,'admin','2015-12-29 05:32:42','AUTHENTICATION_SUCCESS'),(111,'admin','2015-12-29 07:01:57','AUTHENTICATION_SUCCESS'),(112,'admin','2015-12-30 02:18:55','AUTHENTICATION_SUCCESS'),(113,'admin','2015-12-30 08:39:43','AUTHENTICATION_SUCCESS'),(114,'admin','2015-12-30 08:39:43','AUTHENTICATION_SUCCESS'),(115,'admin','2016-01-06 06:53:32','AUTHENTICATION_SUCCESS'),(116,'admin','2016-01-06 06:53:33','AUTHENTICATION_SUCCESS'),(117,'admin','2016-01-06 07:18:58','AUTHENTICATION_SUCCESS'),(118,'admin','2016-01-06 08:45:33','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'remoteAddress','0:0:0:0:0:0:0:1'),(1,'sessionId','5E8D2AADD98C07A7C5CC39AC1C4A38C3'),(2,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'sessionId','5E8D2AADD98C07A7C5CC39AC1C4A38C3'),(3,'remoteAddress','0:0:0:0:0:0:0:1'),(3,'sessionId','5E8D2AADD98C07A7C5CC39AC1C4A38C3'),(4,'remoteAddress','0:0:0:0:0:0:0:1'),(4,'sessionId','11434CD6E51B4AFBE8B90BB1BDB91B7F'),(5,'remoteAddress','0:0:0:0:0:0:0:1'),(5,'sessionId','8FFE3960A1C9F673287A440F3F9BDBC1'),(6,'remoteAddress','0:0:0:0:0:0:0:1'),(6,'sessionId','C0E591C79684BDB730099364829088C4'),(7,'remoteAddress','0:0:0:0:0:0:0:1'),(7,'sessionId','99D22BE20FF31BFD7C155E5CB1F9E7A4'),(8,'remoteAddress','0:0:0:0:0:0:0:1'),(8,'sessionId','99D22BE20FF31BFD7C155E5CB1F9E7A4'),(9,'remoteAddress','0:0:0:0:0:0:0:1'),(9,'sessionId','99D22BE20FF31BFD7C155E5CB1F9E7A4'),(10,'remoteAddress','0:0:0:0:0:0:0:1'),(10,'sessionId','0FEC269CCE92BD33E1CFF16F77C51526'),(11,'remoteAddress','0:0:0:0:0:0:0:1'),(11,'sessionId','8C1E9ADC2396B400FAC63E6480054CFC'),(12,'remoteAddress','0:0:0:0:0:0:0:1'),(12,'sessionId','8C1E9ADC2396B400FAC63E6480054CFC'),(13,'remoteAddress','0:0:0:0:0:0:0:1'),(13,'sessionId','8C1E9ADC2396B400FAC63E6480054CFC'),(14,'remoteAddress','0:0:0:0:0:0:0:1'),(14,'sessionId','BEB72D5E4FA4F39B516FEC585789D39B'),(15,'remoteAddress','0:0:0:0:0:0:0:1'),(15,'sessionId','BEB72D5E4FA4F39B516FEC585789D39B'),(16,'remoteAddress','0:0:0:0:0:0:0:1'),(16,'sessionId','BEB72D5E4FA4F39B516FEC585789D39B'),(17,'remoteAddress','0:0:0:0:0:0:0:1'),(17,'sessionId','DC43229CB80B242B58F0E4D1F225DDAB'),(18,'remoteAddress','0:0:0:0:0:0:0:1'),(18,'sessionId','14272F13CEA3CA350C826968347BF493'),(19,'remoteAddress','0:0:0:0:0:0:0:1'),(19,'sessionId','C1544216669BD3DE9AF530B6CA792302'),(20,'remoteAddress','0:0:0:0:0:0:0:1'),(20,'sessionId','045C7D39D5F5848A19C508D413206D7D'),(21,'remoteAddress','0:0:0:0:0:0:0:1'),(21,'sessionId','B8A9D9524FFBC0C1D14468037B4BB0F6'),(22,'remoteAddress','0:0:0:0:0:0:0:1'),(22,'sessionId','18366423B2B8F80C53DD725CF6CF3329'),(23,'remoteAddress','0:0:0:0:0:0:0:1'),(23,'sessionId','9F90E689C743DEB43901DB8B4C91C935'),(24,'remoteAddress','0:0:0:0:0:0:0:1'),(24,'sessionId','33BA381B3822AB9CFB38B9124B06829A'),(25,'remoteAddress','0:0:0:0:0:0:0:1'),(25,'sessionId','0462DA33A8A24DB980B484A2EACC01E7'),(26,'remoteAddress','0:0:0:0:0:0:0:1'),(26,'sessionId','0CD87F9571F766641D075D10453CAE3A'),(27,'remoteAddress','0:0:0:0:0:0:0:1'),(27,'sessionId','0CD87F9571F766641D075D10453CAE3A'),(28,'remoteAddress','0:0:0:0:0:0:0:1'),(28,'sessionId','0CD87F9571F766641D075D10453CAE3A'),(29,'remoteAddress','0:0:0:0:0:0:0:1'),(29,'sessionId','822A3F5E2154C20E41ABC4320C7EC796'),(30,'remoteAddress','0:0:0:0:0:0:0:1'),(30,'sessionId','FF5C6A5741AE925BC309E3039CA1E98B'),(31,'remoteAddress','0:0:0:0:0:0:0:1'),(31,'sessionId','BB0D29CAD40DF24A67664D83BD3FC02A'),(32,'remoteAddress','0:0:0:0:0:0:0:1'),(32,'sessionId','3DD925EFDF29F6F23F592CF4768D43CF'),(33,'remoteAddress','0:0:0:0:0:0:0:1'),(33,'sessionId','C6B376ED7038D116E6AFAD610B197264'),(34,'remoteAddress','0:0:0:0:0:0:0:1'),(34,'sessionId','6E1AC70A1560A421B1E41A52AF6CE84D'),(35,'remoteAddress','0:0:0:0:0:0:0:1'),(35,'sessionId','28969F83E7B968B839C25ECAE43CA33F'),(36,'remoteAddress','0:0:0:0:0:0:0:1'),(36,'sessionId','28969F83E7B968B839C25ECAE43CA33F'),(37,'remoteAddress','0:0:0:0:0:0:0:1'),(37,'sessionId','28969F83E7B968B839C25ECAE43CA33F'),(38,'remoteAddress','0:0:0:0:0:0:0:1'),(38,'sessionId','54CF91F589049C861D12539DCC083D59'),(39,'remoteAddress','0:0:0:0:0:0:0:1'),(39,'sessionId','54CF91F589049C861D12539DCC083D59'),(40,'remoteAddress','0:0:0:0:0:0:0:1'),(40,'sessionId','54CF91F589049C861D12539DCC083D59'),(41,'remoteAddress','0:0:0:0:0:0:0:1'),(41,'sessionId','A32CD49E05A536DF3EF8DE604732E341'),(42,'remoteAddress','0:0:0:0:0:0:0:1'),(42,'sessionId','6C2728996F88CB3247CD244F0183E2DB'),(43,'remoteAddress','0:0:0:0:0:0:0:1'),(43,'sessionId','6C2728996F88CB3247CD244F0183E2DB'),(44,'remoteAddress','0:0:0:0:0:0:0:1'),(44,'sessionId','6C2728996F88CB3247CD244F0183E2DB'),(45,'remoteAddress','0:0:0:0:0:0:0:1'),(45,'sessionId','825CA5998534AD02EB040263A3185E6E'),(46,'remoteAddress','0:0:0:0:0:0:0:1'),(46,'sessionId','0038526415E8554D9A2EA922DEA834BE'),(47,'remoteAddress','0:0:0:0:0:0:0:1'),(47,'sessionId','2A19DDCD4756805E6E5C93CFFA7D1C6C'),(48,'remoteAddress','0:0:0:0:0:0:0:1'),(48,'sessionId','DD119BED0B0FA4430CDF26615CF05FB0'),(49,'remoteAddress','0:0:0:0:0:0:0:1'),(49,'sessionId','DD119BED0B0FA4430CDF26615CF05FB0'),(50,'remoteAddress','0:0:0:0:0:0:0:1'),(50,'sessionId','DD119BED0B0FA4430CDF26615CF05FB0'),(51,'remoteAddress','0:0:0:0:0:0:0:1'),(51,'sessionId','7BF5425F952969471DF74329173D3DBB'),(52,'remoteAddress','0:0:0:0:0:0:0:1'),(52,'sessionId','7BF5425F952969471DF74329173D3DBB'),(53,'remoteAddress','0:0:0:0:0:0:0:1'),(53,'sessionId','7BF5425F952969471DF74329173D3DBB'),(54,'remoteAddress','0:0:0:0:0:0:0:1'),(54,'sessionId','6E1873C773EE7E2F26949C3C13E5B874'),(55,'remoteAddress','0:0:0:0:0:0:0:1'),(55,'sessionId','6E1873C773EE7E2F26949C3C13E5B874'),(56,'remoteAddress','0:0:0:0:0:0:0:1'),(56,'sessionId','6E1873C773EE7E2F26949C3C13E5B874'),(57,'remoteAddress','0:0:0:0:0:0:0:1'),(58,'remoteAddress','0:0:0:0:0:0:0:1'),(59,'remoteAddress','0:0:0:0:0:0:0:1'),(60,'remoteAddress','0:0:0:0:0:0:0:1'),(61,'remoteAddress','0:0:0:0:0:0:0:1'),(62,'remoteAddress','0:0:0:0:0:0:0:1'),(63,'remoteAddress','0:0:0:0:0:0:0:1'),(64,'remoteAddress','0:0:0:0:0:0:0:1'),(65,'remoteAddress','0:0:0:0:0:0:0:1'),(66,'remoteAddress','0:0:0:0:0:0:0:1'),(67,'remoteAddress','0:0:0:0:0:0:0:1'),(68,'remoteAddress','0:0:0:0:0:0:0:1'),(69,'remoteAddress','0:0:0:0:0:0:0:1'),(70,'remoteAddress','0:0:0:0:0:0:0:1'),(71,'remoteAddress','0:0:0:0:0:0:0:1'),(72,'remoteAddress','0:0:0:0:0:0:0:1'),(73,'remoteAddress','0:0:0:0:0:0:0:1'),(74,'remoteAddress','0:0:0:0:0:0:0:1'),(75,'remoteAddress','0:0:0:0:0:0:0:1'),(76,'remoteAddress','0:0:0:0:0:0:0:1'),(77,'remoteAddress','0:0:0:0:0:0:0:1'),(78,'remoteAddress','0:0:0:0:0:0:0:1'),(79,'remoteAddress','0:0:0:0:0:0:0:1'),(80,'remoteAddress','0:0:0:0:0:0:0:1'),(81,'remoteAddress','0:0:0:0:0:0:0:1'),(82,'remoteAddress','0:0:0:0:0:0:0:1'),(83,'remoteAddress','0:0:0:0:0:0:0:1'),(84,'remoteAddress','0:0:0:0:0:0:0:1'),(85,'remoteAddress','0:0:0:0:0:0:0:1'),(86,'remoteAddress','0:0:0:0:0:0:0:1'),(87,'remoteAddress','0:0:0:0:0:0:0:1'),(88,'remoteAddress','0:0:0:0:0:0:0:1'),(89,'remoteAddress','0:0:0:0:0:0:0:1'),(90,'remoteAddress','0:0:0:0:0:0:0:1'),(91,'remoteAddress','0:0:0:0:0:0:0:1'),(92,'remoteAddress','0:0:0:0:0:0:0:1'),(93,'remoteAddress','0:0:0:0:0:0:0:1'),(94,'remoteAddress','0:0:0:0:0:0:0:1'),(95,'remoteAddress','0:0:0:0:0:0:0:1'),(96,'remoteAddress','0:0:0:0:0:0:0:1'),(97,'remoteAddress','0:0:0:0:0:0:0:1'),(98,'remoteAddress','0:0:0:0:0:0:0:1'),(99,'remoteAddress','0:0:0:0:0:0:0:1'),(100,'remoteAddress','0:0:0:0:0:0:0:1'),(101,'remoteAddress','0:0:0:0:0:0:0:1'),(102,'remoteAddress','0:0:0:0:0:0:0:1'),(103,'remoteAddress','0:0:0:0:0:0:0:1'),(104,'remoteAddress','0:0:0:0:0:0:0:1'),(105,'remoteAddress','0:0:0:0:0:0:0:1'),(106,'remoteAddress','0:0:0:0:0:0:0:1'),(107,'remoteAddress','0:0:0:0:0:0:0:1'),(108,'remoteAddress','0:0:0:0:0:0:0:1'),(109,'remoteAddress','0:0:0:0:0:0:0:1'),(110,'remoteAddress','0:0:0:0:0:0:0:1'),(111,'remoteAddress','0:0:0:0:0:0:0:1'),(112,'remoteAddress','0:0:0:0:0:0:0:1'),(113,'remoteAddress','0:0:0:0:0:0:0:1'),(114,'remoteAddress','0:0:0:0:0:0:0:1'),(115,'remoteAddress','0:0:0:0:0:0:0:1'),(116,'remoteAddress','0:0:0:0:0:0:0:1'),(117,'remoteAddress','0:0:0:0:0:0:0:1'),(118,'remoteAddress','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_token`
--

DROP TABLE IF EXISTS `jhi_persistent_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_token` (
  `series` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(255) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`series`),
  KEY `fk_user_persistent_token` (`user_id`),
  CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_token`
--

LOCK TABLES `jhi_persistent_token` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_token` DISABLE KEYS */;
INSERT INTO `jhi_persistent_token` VALUES ('h4OTA9Fw4Hm4+vpKQ9kAYw==',3,'IFyS28yEA+B+PL1nptkmOQ==','2015-12-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36'),('m4fO7zkCthddnM1+lD6ClA==',3,'hK11NpsLbgbr8ks37+wVMw==','2015-12-17','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36'),('oPhPh6j0whiSnUs6OAvVeQ==',3,'RGix+8HSKMYKK0E/12UfLQ==','2015-12-03','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36'),('PtUx/KkaAnEPk1MDLcezqw==',3,'cJwJoIB4MESnFbbZpRlyOA==','2015-11-30','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36'),('z/qOYt1/pZiiGNrPEK8f8g==',3,'w7eavuI0GgXhawMmwFR9gg==','2016-01-06','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36');
/*!40000 ALTER TABLE `jhi_persistent_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `PASSWORD` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','en',NULL,NULL,'system','2015-11-20 08:10:31',NULL,NULL,NULL),(2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','en',NULL,NULL,'system','2015-11-20 08:10:31',NULL,NULL,NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','en',NULL,NULL,'system','2015-11-20 08:10:31',NULL,NULL,NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','en',NULL,NULL,'system','2015-11-20 08:10:31',NULL,NULL,NULL);
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_page`
--

DROP TABLE IF EXISTS `menu_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `detail_info` varchar(5000) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `order_tag` varchar(45) DEFAULT NULL,
  `slide1` varchar(255) DEFAULT NULL,
  `slide2` varchar(255) DEFAULT NULL,
  `slide3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_page`
--

LOCK TABLES `menu_page` WRITE;
/*!40000 ALTER TABLE `menu_page` DISABLE KEYS */;
INSERT INTO `menu_page` VALUES (18,'酒庄探索','front/products/byregionss','<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375066391_9619b270-120c-4a7c-8762-a5618a4c081f_jiuzhuang.png	\" style=\"height:399px; width:1280px\" /></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:22px\"><strong>初见克雷布斯庄园</strong></span></p>\n\n<p><span style=\"font-size:14px\"><span style=\"line-height:16px\">鸿杰采酒小分队顺利抵达德国克雷布斯酒庄。滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答答。</span></span></p>\n\n<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375086218_e44a77d0-8f7d-49e2-9be9-f40cdf7eb89c_jiuzhuang-1.png	\" style=\"height:410px; width:1001px\" /></p>\n\n<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375099133_a120078f-5d69-4585-9975-2f5447853e36_jiuzhuang-2.png	\" style=\"height:416px; width:1000px\" /></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:22px\"><strong>初见克雷布斯庄园</strong></span></p>\n\n<p><span style=\"font-size:14px\">鸿杰采酒小分队顺利抵达德国克雷布斯酒庄。滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答答。</span></p>\n\n<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375108581_42116a25-42e9-432b-8946-1fff86655d36_jiuzhuang-3.png	\" style=\"height:402px; width:1002px\" /></p>\n\n<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375117845_436ebd39-65f3-4d85-bc89-2fa4a680a959_jiuzhuang-4.png	\" style=\"height:402px; width:1001px\" /></p>\n\n<p><span style=\"font-size:22px\"><strong>初见克雷布斯庄园</strong></span></p>\n\n<p><span style=\"font-size:14px\">鸿杰采酒小分队顺利抵达德国克雷布斯酒庄。滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答滴答答。</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:14px\"><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1451375126414_7bedf2d7-b1e2-43ba-8834-1f3d0875fa09_jiuzhuang-5.png	\" style=\"height:399px; width:1001px\" /></span></p>\n\n<p>&nbsp;</p>\n',1,'1',NULL,NULL,NULL),(20,'红酒知识',NULL,'this is a test',1,'2',NULL,NULL,NULL);
/*!40000 ALTER TABLE `menu_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (10,NULL,'1450072962102_b6ddcd52-cb5e-41f0-999c-6ed288519aff_depuis.png',NULL),(11,NULL,'1450072968638_b86e616e-be1c-4b88-8e25-d9d1a270ad9d_1.png',NULL),(12,NULL,'1450072973473_e6e81342-7738-4c9c-8ab2-701b1439fd73_2.png',NULL),(13,NULL,'1450072979842_c6c2043f-2a83-4786-ace2-3c018d6fbbf6_3.png',NULL),(14,NULL,'1450422869553_ac48e46c-12ec-4963-8407-2ffcc101482b_carousel-003.png',NULL),(15,NULL,'1450423118253_0f2f543b-7c26-4834-9a93-79ccdb73d198_carousel-003.png',NULL),(16,'酒庄','1451375066391_9619b270-120c-4a7c-8762-a5618a4c081f_jiuzhuang.png',NULL),(17,'酒庄1','1451375086218_e44a77d0-8f7d-49e2-9be9-f40cdf7eb89c_jiuzhuang-1.png',NULL),(18,'酒庄2','1451375099133_a120078f-5d69-4585-9975-2f5447853e36_jiuzhuang-2.png',NULL),(19,'酒庄3','1451375108581_42116a25-42e9-432b-8946-1fff86655d36_jiuzhuang-3.png',NULL),(20,'酒庄4','1451375117845_436ebd39-65f3-4d85-bc89-2fa4a680a959_jiuzhuang-4.png',NULL),(21,'酒庄5','1451375126414_7bedf2d7-b1e2-43ba-8834-1f3d0875fa09_jiuzhuang-5.png',NULL),(22,'酒庄6','1451375136193_56484135-ebbc-4e5a-9bf2-9a791fb4d816_jiuzhuang-6.png',NULL),(23,'slide 3','1451443917966_23aa653a-2da4-42b5-bd3c-29713f6fab6c_slide-home3.png',NULL),(24,'我们','1452073787287_ff41d023-370a-42c1-ab57-98f25dee97f4_us.png',NULL),(25,NULL,'1452074093543_f6dceb4e-8b8f-4394-b85e-03c56c421589_us.png',NULL);
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `produce_date` date DEFAULT NULL,
  `producer` varchar(255) DEFAULT NULL,
  `favorate` int(11) DEFAULT '0',
  `news` tinyint(1) DEFAULT '1',
  `image` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `award1` varchar(255) DEFAULT NULL,
  `award2` varchar(255) DEFAULT NULL,
  `award3` varchar(255) DEFAULT NULL,
  `description_title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `jdurl` varchar(255) DEFAULT NULL,
  `detail_info` varchar(5000) DEFAULT NULL,
  `types` varchar(255) DEFAULT NULL,
  `variety` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `origin_country` varchar(255) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `pick_year` varchar(255) DEFAULT NULL,
  `alcohol` varchar(255) DEFAULT NULL,
  `wakeup_time` varchar(255) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `favo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'002','毫克玛歌庄园干红',249.99,'2015-10-05',NULL,9,1,'1450072763894_d24d451a-0903-4a07-9b57-3193d756e946_hongjiu.png','鸿杰酒业2015新酒推荐','波狄康卡庄园骑士精神干红葡萄酒750ml',NULL,'1450072762047_7b8a629b-08b2-4c54-926c-dc530e748089_hongjiu.png','1450072767718_4c7e8989-4440-43b7-93e5-e850eb01d8fc_award1.png','1450072778773_2d5f2f10-7d11-4e93-a9f6-f470752ec7a8_award1.png','1450072780844_7d5422d9-8ac0-4548-af12-56bbc346234a_award1.png','2012巴黎农展金奖','啊非法违法阿飞阿飞awful无法阿飞啊啊服务i骄傲激发阿飞啊分jaw免费i阿娇','http://jd.com','<h2><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1450072962102_b6ddcd52-cb5e-41f0-999c-6ed288519aff_depuis.png\" style=\"height:227px; margin-left:160px; margin-right:160px; width:872px\" /></h2>\n\n<h2 style=\"text-align:center\"><strong>CHEVAL QUANCARD</strong></h2>\n\n<h2 style=\"text-align:center\">法国波马酒业</h2>\n\n<p>波马集团所获盛誉不胜枚举，并在全球建立了良好的信誉及广泛的认可。公司对产品和包装材料实行电脑追踪系统， 使每瓶酒皆可追踪到生产者或供应商。 2003年，英国零售业联盟授予波马酒业葡萄酒生产商高标准认证，波马酒业的产品也是各个高端场所及免税店的常客.</p>\n\n<p>&nbsp;</p>\n\n<p><img alt=\"\" src=\"http://localhost:8080/assets/images/upload/1450072968638_b86e616e-be1c-4b88-8e25-d9d1a270ad9d_1.png\" style=\"height:500px; width:1001px\" /></p>\n\n<p>&nbsp;&nbsp;</p>\n\n<p>&nbsp;</p>\n','红葡萄酒','梅洛 赤霞珠','小产区','法国','波尔多','2012','13vol','20分钟以上',0.80,1),(9,'003','好白酒',69.90,NULL,NULL,6,1,'1450255553217_85f713cb-e461-4bc0-b800-8cf8c78a2fc6_hongjiu.png','白酒','好白酒好白酒','好白酒好白酒好白酒',NULL,'1450423652107_001bda44-b936-4661-956c-6573f69e45cb_award1.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'德国',NULL,NULL,NULL,NULL,NULL,1),(10,'003','美酒',NULL,NULL,NULL,7,NULL,'1450255695365_bd984470-d914-46ce-9d21-e9879d4533ba_hongjiu.png',NULL,'美酒',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'法国',NULL,NULL,NULL,NULL,NULL,1),(11,'005','佳肴',NULL,NULL,NULL,NULL,NULL,'1450255740287_4a8ce9ee-a79d-4b7c-8bd9-e28b0fe9d914_hongjiu.png',NULL,'佳肴',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(12,'006','白葡萄酒',NULL,NULL,NULL,NULL,NULL,'1450255788048_76df58e7-bda3-4efa-9b59-08ab9c6f8cce_hongjiu.png',NULL,'白葡萄酒',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(13,'007','剑南春',NULL,NULL,NULL,NULL,0,'1450338119366_c2b4af5c-ef81-4f1e-a74d-54dde38565c3_hongjiu.png',NULL,'剑南春',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_relate`
--

DROP TABLE IF EXISTS `product_relate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_relate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `relate_product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_relate_product_id` (`product_id`),
  KEY `fk_product_relate_relate_product_id_idx` (`relate_product_id`),
  CONSTRAINT `fk_product_relate_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_product_relate_relate_product_id` FOREIGN KEY (`relate_product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_relate`
--

LOCK TABLES `product_relate` WRITE;
/*!40000 ALTER TABLE `product_relate` DISABLE KEYS */;
INSERT INTO `product_relate` VALUES (1,NULL,8,12),(2,NULL,8,10);
/*!40000 ALTER TABLE `product_relate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slide`
--

DROP TABLE IF EXISTS `slide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slide` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slide`
--

LOCK TABLES `slide` WRITE;
/*!40000 ALTER TABLE `slide` DISABLE KEYS */;
INSERT INTO `slide` VALUES (9,'时间之河','1451381507123_78e163f2-8764-4138-b3ad-46202a2abd1c_slide-home1.png',NULL),(10,NULL,'1451382427013_cbb4c8fa-77f5-43d5-a113-cb687a24d370_slide-home2.png',NULL),(11,NULL,'1451444307979_77f2f5bc-6830-495f-810b-7b46faa8e995_slide-home3.png',NULL);
/*!40000 ALTER TABLE `slide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wine_side`
--

DROP TABLE IF EXISTS `wine_side`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wine_side` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `detail_info` varchar(5000) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `order_tag` varchar(45) DEFAULT NULL,
  `placeholder1` varchar(255) DEFAULT NULL,
  `placeholder2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wine_side`
--

LOCK TABLES `wine_side` WRITE;
/*!40000 ALTER TABLE `wine_side` DISABLE KEYS */;
INSERT INTO `wine_side` VALUES (22,'红酒葡萄酒原木礼盒','红酒葡萄酒原木礼盒',59.60,'1452072396498_eb4f365d-d270-48de-b7a5-c9f683f37579_box.png','<h1>红酒葡萄酒原木礼盒，法国AOC酒类专用 单支装 注：此商品不包含盒内红酒</h1>\n\n<p>鸿杰获奖红酒专营 原瓶进口 礼品红酒专家</p>\n','<ul>\n	<li>商品名称：红酒葡萄酒原木礼盒，法国AOC酒类专用 单支装 注：此商品不包含盒内红酒</li>\n	<li>商品编号：1056592177</li>\n	<li>店铺：&nbsp;<a href=\"http://hongjie.jd.com/\" target=\"_blank\">鸿杰获奖红酒专营店</a></li>\n	<li>上架时间：2015-09-08 09:52:29</li>\n	<li>商品毛重：1.5kg</li>\n	<li>商品产地：法国</li>\n	<li>原产地：法国</li>\n	<li>特性：礼盒馈赠</li>\n	<li>类型：红葡萄酒</li>\n	<li>葡萄品种：赤霞珠（Cabernet Sauvi</li>\n</ul>\n',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `wine_side` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xref`
--

DROP TABLE IF EXISTS `xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ovalue` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `active` varchar(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xref`
--

LOCK TABLES `xref` WRITE;
/*!40000 ALTER TABLE `xref` DISABLE KEYS */;
INSERT INTO `xref` VALUES (4,'Level',NULL,8,'1'),(5,'Level',NULL,9,NULL),(7,NULL,NULL,13,NULL);
/*!40000 ALTER TABLE `xref` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-06 17:58:45
