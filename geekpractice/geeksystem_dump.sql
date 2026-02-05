-- MySQL dump 10.13  Distrib 9.4.0, for macos15.4 (arm64)
--
-- Host: 127.0.0.1    Database: geeksystem
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BATCH_JOB_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `CREATE_TIME` datetime(6) NOT NULL,
  `START_TIME` datetime(6) DEFAULT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION`
--

LOCK TABLES `BATCH_JOB_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (1,2,1,'2026-01-22 17:24:00.244401','2026-01-22 17:24:00.307790','2026-01-22 17:24:05.100431','COMPLETED','COMPLETED','','2026-01-22 17:24:05.101045'),(2,2,2,'2026-01-23 18:27:36.521164','2026-01-23 18:27:36.571349','2026-01-23 18:27:36.896183','COMPLETED','COMPLETED','','2026-01-23 18:27:36.896617'),(3,2,3,'2026-01-30 17:24:00.148426','2026-01-30 17:24:00.195425','2026-01-30 17:24:01.951366','COMPLETED','COMPLETED','','2026-01-30 17:24:01.951887');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_JOB_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (1,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMi40eA==',NULL),(2,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMi40eA==',NULL),(3,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMi40eA==',NULL);
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_PARAMS`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_PARAMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `PARAMETER_NAME` varchar(100) NOT NULL,
  `PARAMETER_TYPE` varchar(100) NOT NULL,
  `PARAMETER_VALUE` varchar(2500) DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_PARAMS`
--

LOCK TABLES `BATCH_JOB_EXECUTION_PARAMS` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (1,'time','java.lang.Long','1769070240119','Y'),(2,'time','java.lang.Long','1769160456412','Y'),(3,'time','java.lang.Long','1769761440025','Y');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_JOB_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_SEQ` VALUES (3,'0');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_INSTANCE`
--

DROP TABLE IF EXISTS `BATCH_JOB_INSTANCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_INSTANCE` (
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_INSTANCE`
--

LOCK TABLES `BATCH_JOB_INSTANCE` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (1,0,'sampleJob','88361fa31480e36f85516d799c4da504'),(2,0,'sampleJob','991f75db89a5deacf28a6666f7ec9336'),(3,0,'sampleJob','1d2dc77239fd7023cd223d5451004af6');
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_SEQ`
--

LOCK TABLES `BATCH_JOB_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_SEQ` VALUES (3,'0');
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `CREATE_TIME` datetime(6) NOT NULL,
  `START_TIME` datetime(6) DEFAULT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint DEFAULT NULL,
  `READ_COUNT` bigint DEFAULT NULL,
  `FILTER_COUNT` bigint DEFAULT NULL,
  `WRITE_COUNT` bigint DEFAULT NULL,
  `READ_SKIP_COUNT` bigint DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint DEFAULT NULL,
  `ROLLBACK_COUNT` bigint DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION`
--

LOCK TABLES `BATCH_STEP_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (1,3,'step1',1,'2026-01-22 17:24:00.331728','2026-01-22 17:24:00.346808','2026-01-22 17:24:05.065972','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2026-01-22 17:24:05.083194'),(2,3,'step1',2,'2026-01-23 18:27:36.588699','2026-01-23 18:27:36.597709','2026-01-23 18:27:36.885049','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2026-01-23 18:27:36.887881'),(3,3,'step1',3,'2026-01-30 17:24:00.217368','2026-01-30 17:24:00.229104','2026-01-30 17:24:01.933684','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2026-01-30 17:24:01.941929');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_STEP_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (1,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAADdAARYmF0Y2gudGFza2xldFR5cGV0ACRjb20uZXhhbXBsZS5kZW1vLmJhdGNoLlNhbXBsZVRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4yLjR0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4',NULL),(2,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAADdAARYmF0Y2gudGFza2xldFR5cGV0ACRjb20uZXhhbXBsZS5kZW1vLmJhdGNoLlNhbXBsZVRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4yLjR0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4',NULL),(3,'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAADdAARYmF0Y2gudGFza2xldFR5cGV0ACRjb20uZXhhbXBsZS5kZW1vLmJhdGNoLlNhbXBsZVRhc2tsZXR0AA1iYXRjaC52ZXJzaW9udAAFNS4yLjR0AA5iYXRjaC5zdGVwVHlwZXQAN29yZy5zcHJpbmdmcmFtZXdvcmsuYmF0Y2guY29yZS5zdGVwLnRhc2tsZXQuVGFza2xldFN0ZXB4',NULL);
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_STEP_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_SEQ` VALUES (3,'0');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `makers_id` int NOT NULL,
  `small_category_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `cost_price` int NOT NULL,
  `retail_price` varchar(255) NOT NULL,
  `sales_price` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_goods_id` (`id`),
  KEY `makers_id` (`makers_id`),
  KEY `small_category_id` (`small_category_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`makers_id`) REFERENCES `makers` (`id`),
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`small_category_id`) REFERENCES `small_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,1,1,'冷蔵庫くん','冷蔵庫くんの説明',50000,'58000.00','59000.00','2025-11-06 07:26:59',NULL,'barista.jpg',NULL,0),(2,2,6,'ドラ洗くん','ドラム式洗濯乾燥機',100000,'118000.00','119000.00','2025-11-06 07:26:59',NULL,'food4.jpg',NULL,0),(3,8,14,'掃除くん','サイクロン式掃除機',30000,'38000.00','39000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','1764319499025_restaurant2.jpg',NULL,0),(4,3,18,'レンジくん','電子レンジ',40000,'48000.00','49000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(5,3,21,'炊飯くん','炊飯器',10000,'18000.00','19000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(6,7,20,'オーブンレンジくん','スチームオーブンレンジ',80000,'88000.00','89000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(7,6,38,'テレビくん','液晶テレビ',70000,'78000.00','79000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(8,9,16,'ロボ掃除くん','ロボット掃除機',60000,'68000.00','69000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(9,4,21,'THE炊飯くん','炊飯器',5000,'5800.00','5900.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(10,5,22,'保温くん','保温ジャー',20000,'28000.00','29000.00','2025-11-06 07:26:59','2025-11-26 00:40:15','',NULL,0),(14,1,1,'テストくん０号','１号の説明',111,'111','111',NULL,NULL,'1764319363383_food4.jpg',NULL,1),(15,1,1,'テストくん１号','説明',111,'111','222',NULL,NULL,'1768378567546_restaurant2.jpg',NULL,0),(16,1,1,'テストくん２号','テスト説明',111,'222','333',NULL,NULL,'1764560282583_food3.jpg',NULL,1),(18,12,4,'テスト4号店','４号の説明',111,'111','111',NULL,NULL,'1764560520738_food3.jpg',NULL,1),(19,1,4,'テストくん','説明',111,'111','111',NULL,NULL,'1764668306754_food3.jpg',NULL,1),(20,1,10,'aa','ああの説明',111,'111','222',NULL,NULL,'1764670174699_food4.jpg',NULL,1),(21,1,1,'て','あ',111,'1','111',NULL,NULL,NULL,NULL,1),(22,1,1,'テストくん','あああああ',11,'111','111',NULL,NULL,NULL,NULL,1),(23,4,6,'テスト２くん','２号の説明',222,'222','222',NULL,NULL,'1768455439956_food2.jpg',NULL,1);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `large_category`
--

DROP TABLE IF EXISTS `large_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `large_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_large_category_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `large_category`
--

LOCK TABLES `large_category` WRITE;
/*!40000 ALTER TABLE `large_category` DISABLE KEYS */;
INSERT INTO `large_category` VALUES (1,'冷蔵庫・洗濯機・掃除機','2025-11-06 07:06:40','2025-11-06 07:06:40'),(2,'電子レンジ・炊飯器','2025-11-06 07:06:40','2025-11-06 07:06:40'),(3,'エアコン・空調','2025-11-06 07:06:40','2025-11-06 07:06:40'),(4,'テレビ・レコーダー','2025-11-06 07:06:40','2025-11-06 07:06:40');
/*!40000 ALTER TABLE `large_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `makers`
--

DROP TABLE IF EXISTS `makers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `makers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_makers_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `makers`
--

LOCK TABLES `makers` WRITE;
/*!40000 ALTER TABLE `makers` DISABLE KEYS */;
INSERT INTO `makers` VALUES (1,'三角電機','2025-11-06 07:26:56','2025-11-06 07:26:56'),(2,'夕立','2025-11-06 07:26:56','2025-11-06 07:26:56'),(3,'Fanasonic','2025-11-06 07:26:56','2025-11-06 07:26:56'),(4,'マイリス・トーヤマ','2025-11-06 07:26:57','2025-11-06 07:26:57'),(5,'虎印','2025-11-06 07:26:57','2025-11-06 07:26:57'),(6,'西芝','2025-11-06 07:26:57','2025-11-06 07:26:57'),(7,'DALNUDA','2025-11-06 07:26:57','2025-11-06 07:26:57'),(8,'ダンソン','2025-11-06 07:26:57','2025-11-06 07:26:57'),(9,'gRobot','2025-11-06 07:26:57','2025-11-06 07:26:57'),(10,'テスト１社','2025-11-20 16:10:54','2025-11-20 16:10:54'),(11,'テスト２社','2025-11-21 14:20:03','2025-11-21 14:20:03'),(12,'テスト３社','2025-12-01 10:37:54','2025-12-01 10:37:54'),(13,'ああああ','2025-12-02 19:03:01','2025-12-02 19:03:01'),(14,'いいいい','2026-01-14 17:15:21','2026-01-14 17:15:21'),(15,'い','2026-01-14 17:15:24','2026-01-14 17:15:24'),(16,'い','2026-01-14 17:15:24','2026-01-14 17:15:24');
/*!40000 ALTER TABLE `makers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middle_category`
--

DROP TABLE IF EXISTS `middle_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `middle_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `large_category_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_middle` (`large_category_id`,`name`),
  KEY `idx_middle_category_id` (`id`),
  CONSTRAINT `middle_category_ibfk_1` FOREIGN KEY (`large_category_id`) REFERENCES `large_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle_category`
--

LOCK TABLES `middle_category` WRITE;
/*!40000 ALTER TABLE `middle_category` DISABLE KEYS */;
INSERT INTO `middle_category` VALUES (1,1,'冷蔵庫・冷凍庫','2025-11-06 07:07:55','2025-11-06 07:07:55'),(2,1,'洗濯機・洗濯乾燥機','2025-11-06 07:07:55','2025-11-06 07:07:55'),(3,1,'掃除機・クリーナー','2025-11-06 07:07:55','2025-11-06 07:07:55'),(4,2,'オーブンレンジ・電子レンジ','2025-11-06 07:09:53','2025-11-06 07:09:53'),(5,2,'炊飯器','2025-11-06 07:09:53','2025-11-06 07:09:53'),(6,3,'エアコン・窓用エアコン','2025-11-06 07:09:53','2025-11-06 07:09:53'),(7,3,'扇風機・サーキュレーター','2025-11-06 07:09:53','2025-11-06 07:09:53'),(8,3,'暖房器具','2025-11-06 07:09:53','2025-11-06 07:09:53'),(9,4,'テレビ','2025-11-06 07:09:53','2025-11-06 07:09:53'),(10,4,'レコーダー','2025-11-06 07:09:53','2025-11-06 07:09:53'),(11,4,'プロジェクター','2025-11-06 07:09:53','2025-11-06 07:09:53');
/*!40000 ALTER TABLE `middle_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orders_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `quantity` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_order_details_id` (`id`),
  KEY `orders_id` (`orders_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,1,1,'2025-11-28 03:14:48','2025-11-28 03:14:48'),(2,2,2,10,'2025-11-28 03:16:00','2025-11-28 03:16:00'),(3,3,4,11,'2025-11-28 03:18:46','2025-11-28 03:18:46'),(4,4,6,2,'2025-11-28 03:27:32','2025-11-28 03:27:32'),(5,9,1,1,'2025-11-28 05:44:55','2025-11-28 05:44:55'),(6,10,14,10,'2025-11-28 05:55:59','2025-11-28 05:55:59'),(7,11,15,10,'2025-11-28 05:58:07','2025-11-28 05:58:07'),(8,12,6,2,'2025-11-28 07:55:36','2025-11-28 07:55:36'),(9,13,1,2,'2025-12-01 01:49:04','2025-12-01 01:49:04'),(10,14,16,1,'2025-12-01 03:18:05','2025-12-01 03:18:05'),(11,15,16,1,'2025-12-01 03:38:35','2025-12-01 03:38:35'),(12,16,18,1,'2025-12-01 03:42:19','2025-12-01 03:42:19'),(13,17,1,1,'2025-12-02 06:25:36','2025-12-02 06:25:36'),(14,18,19,2,'2025-12-02 10:12:43','2025-12-02 10:12:43'),(15,19,1,1,'2025-12-02 10:18:30','2025-12-02 10:18:30'),(16,20,1,1,'2026-01-14 08:14:37','2026-01-14 08:14:37'),(17,21,10,5,'2026-01-15 05:33:34','2026-01-15 05:33:34'),(18,22,1,1,'2026-01-27 08:29:51','2026-01-27 08:29:51');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `users_id` int NOT NULL,
  `stores_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_orders_id` (`id`),
  KEY `stores_id` (`stores_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`stores_id`) REFERENCES `stores` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2,1,'2025-11-28 03:14:48','2025-11-28 03:14:48'),(2,2,1,'2025-11-28 03:16:00','2025-11-28 03:16:00'),(3,2,1,'2025-11-28 03:18:46','2025-11-28 03:18:46'),(4,2,1,'2025-11-28 03:27:32','2025-11-28 03:27:32'),(5,2,1,'2025-11-28 05:32:19','2025-11-28 05:32:19'),(9,2,1,'2025-11-28 05:44:55','2025-11-28 05:44:55'),(10,2,1,'2025-11-28 05:55:59','2025-11-28 05:55:59'),(11,6,3,'2025-11-28 05:58:07','2025-11-28 05:58:07'),(12,6,3,'2025-11-28 07:55:36','2025-11-28 07:55:36'),(13,2,1,'2025-12-01 01:49:04','2025-12-01 01:49:04'),(14,4,1,'2025-12-01 03:18:05','2025-12-01 03:18:05'),(15,2,1,'2025-12-01 03:38:35','2025-12-01 03:38:35'),(16,2,1,'2025-12-01 03:42:19','2025-12-01 03:42:19'),(17,2,1,'2025-12-02 06:25:36','2025-12-02 06:25:36'),(18,2,1,'2025-12-02 10:12:43','2025-12-02 10:12:43'),(19,4,1,'2025-12-02 10:18:30','2025-12-02 10:18:30'),(20,2,1,'2026-01-14 08:14:37','2026-01-14 08:14:37'),(21,2,1,'2026-01-15 05:33:34','2026-01-15 05:33:34'),(22,2,1,'2026-01-27 08:29:51','2026-01-27 08:29:51');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `permissions_code` varchar(255) NOT NULL,
  `permissions_description` varchar(255) NOT NULL,
  `permissions_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `idx_permissions_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'管理者','admin','管理者権限','2025-10-31 07:17:45','2025-11-06 09:52:02','admin','管理者権限','管理者'),(2,'一般','general','一般権限','2025-11-06 06:53:04','2025-11-06 09:52:02','general','一般権限','一般');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `roles_id` varchar(255) NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  `roles_description` varchar(255) NOT NULL,
  `permissions_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_roles_id` (`id`),
  KEY `FKc753gcho9xx7eo9jaxlxg8psw` (`permissions_id`),
  CONSTRAINT `FKc753gcho9xx7eo9jaxlxg8psw` FOREIGN KEY (`permissions_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'店長','店長です','2025-10-31 07:13:46','2025-11-10 06:03:42','1','店長','店長です',1),(2,'副店長','副店長です','2025-11-06 06:50:22','2025-11-10 06:03:42','2','副店長','副店長です',1),(3,'マネージャー','マネージャーです','2025-11-06 06:50:22','2025-11-10 06:03:42','3','マネージャー','マネージャーです',1),(4,'一般従業員','一般従業員です','2025-11-06 06:50:22','2025-11-10 06:03:42','4','一般従業員','一般従業員です',2),(5,'パート・アルバイト','パート・アルバイトです','2025-11-06 06:50:22','2025-11-10 06:03:42','5','パート・アルバイト','パート・アルバイトです',2);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `small_category`
--

DROP TABLE IF EXISTS `small_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `small_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `middle_category_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_small` (`middle_category_id`,`name`),
  KEY `idx_small_category_id` (`id`),
  CONSTRAINT `small_category_ibfk_1` FOREIGN KEY (`middle_category_id`) REFERENCES `middle_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `small_category`
--

LOCK TABLES `small_category` WRITE;
/*!40000 ALTER TABLE `small_category` DISABLE KEYS */;
INSERT INTO `small_category` VALUES (1,1,'冷蔵庫','2025-11-06 07:22:23','2025-11-06 07:22:23'),(2,1,'冷凍庫','2025-11-18 03:55:11','2025-11-18 03:55:11'),(3,1,'保冷・冷温ボックス','2025-11-18 03:55:11','2025-11-18 03:55:11'),(4,1,'製氷機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(5,1,'冷蔵庫関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(6,2,'ドラム式洗濯乾燥機','2025-11-06 07:22:23','2025-11-06 07:22:23'),(7,2,'縦型洗濯機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(8,2,'2槽式洗濯機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(9,2,'ハンディ・小型洗濯機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(10,2,'衣類乾燥機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(11,2,'洗濯機関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(12,2,'衣類乾燥機・関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(13,3,'スティッククリーナー','2025-11-18 03:55:11','2025-11-18 03:55:11'),(14,3,'サイクロン式掃除機','2025-11-06 07:22:23','2025-11-06 07:22:23'),(15,3,'紙パック式掃除機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(16,3,'ロボット掃除機','2025-11-06 07:22:23','2025-11-06 07:22:23'),(17,3,'ハンディクリーナー','2025-11-18 03:55:11','2025-11-18 03:55:11'),(18,4,'電子レンジ','2025-11-06 07:22:23','2025-11-06 07:22:23'),(19,4,'オーブンレンジ','2025-11-18 03:55:11','2025-11-18 03:55:11'),(20,4,'スチームオーブンレンジ','2025-11-06 07:22:23','2025-11-06 07:22:23'),(21,5,'炊飯器','2025-11-06 07:22:23','2025-11-06 07:22:23'),(22,5,'保温ジャー','2025-11-06 07:22:23','2025-11-06 07:22:23'),(23,5,'ガス炊飯器','2025-11-18 03:55:11','2025-11-18 03:55:11'),(24,6,'エアコン','2025-11-18 03:55:11','2025-11-18 03:55:11'),(25,6,'窓用エアコン','2025-11-18 03:55:11','2025-11-18 03:55:11'),(26,6,'エアコン関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(27,7,'リビング扇風機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(28,7,'タワー型扇風機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(29,7,'羽根無し扇風機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(30,7,'サーキュレーター','2025-11-18 03:55:11','2025-11-18 03:55:11'),(31,7,'携帯型扇風機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(32,7,'卓上型扇風機','2025-11-18 03:55:11','2025-11-18 03:55:11'),(33,8,'電気ファンヒーター','2025-11-18 03:55:11','2025-11-18 03:55:11'),(34,8,'電気ストーブ','2025-11-18 03:55:11','2025-11-18 03:55:11'),(35,8,'セラミックヒーター','2025-11-18 03:55:11','2025-11-18 03:55:11'),(36,8,'こたつ・こたつ布団','2025-11-18 03:55:11','2025-11-18 03:55:11'),(37,8,'ホットカーペット・関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(38,9,'液晶テレビ','2025-11-06 07:22:23','2025-11-06 07:22:23'),(39,9,'有機ELテレビ','2025-11-18 03:55:11','2025-11-18 03:55:11'),(40,9,'ポータブルテレビ','2025-11-18 03:55:11','2025-11-18 03:55:11'),(41,9,'テレビ関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(42,10,'ブルーレイレコーダー','2025-11-18 03:55:11','2025-11-18 03:55:11'),(43,10,'HDDレコーダー','2025-11-18 03:55:11','2025-11-18 03:55:11'),(44,10,'レコーダー関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(45,11,'プロジェクター本体','2025-11-18 03:55:11','2025-11-18 03:55:11'),(46,11,'プロジェクター関連品','2025-11-18 03:55:11','2025-11-18 03:55:11'),(47,11,'プロジェクタースクリーン','2025-11-18 03:55:11','2025-11-18 03:55:11'),(48,11,'プロジェクタースクリーン関連品','2025-11-18 03:55:11','2025-11-18 03:55:11');
/*!40000 ALTER TABLE `small_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_stocks`
--

DROP TABLE IF EXISTS `store_stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_stocks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stores_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `quantity` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_stock` (`stores_id`,`goods_id`),
  UNIQUE KEY `UKg7qumkrjeuts4gi2rkbl2xpeg` (`stores_id`,`goods_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `store_stocks_ibfk_1` FOREIGN KEY (`stores_id`) REFERENCES `stores` (`id`) ON DELETE CASCADE,
  CONSTRAINT `store_stocks_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_stocks`
--

LOCK TABLES `store_stocks` WRITE;
/*!40000 ALTER TABLE `store_stocks` DISABLE KEYS */;
INSERT INTO `store_stocks` VALUES (1,1,1,8,'2025-11-28 03:27:08','2026-01-27 17:29:52'),(2,1,2,10,'2025-11-28 03:27:08','2025-11-28 03:27:08'),(3,1,6,2,'2025-11-28 12:27:33','2025-11-28 12:27:33'),(4,1,14,10,'2025-11-28 14:55:59','2025-11-28 14:55:59'),(5,3,15,10,'2025-11-28 14:58:08','2025-11-28 14:58:08'),(6,3,6,2,'2025-11-28 16:55:37','2025-11-28 16:55:37'),(7,1,16,2,'2025-12-01 12:18:05','2025-12-01 12:38:36'),(8,1,18,1,'2025-12-01 12:42:19','2025-12-01 12:42:19'),(9,1,19,2,'2025-12-02 19:12:44','2025-12-02 19:12:44'),(10,1,10,5,'2026-01-15 14:33:34','2026-01-15 14:33:34');
/*!40000 ALTER TABLE `store_stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_stores_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'Geek電機 渋谷店','東京都渋谷区Geek坂 1-1-1','2025-10-31 07:20:01','2026-01-27 17:29:19'),(2,'Geek電機 新宿店','東京都新宿区西Geek 1-1-1','2025-11-06 06:40:23','2025-11-06 09:50:52'),(3,'Geek電機 池袋店','東京都豊島区GeekShine通り 1-1-1','2025-11-06 06:40:23','2025-11-06 09:50:52'),(4,'テスト１号店','テスト住所１','2025-11-14 13:21:14','2025-11-14 13:21:14'),(5,'テスト２号店','テスト住所２','2025-11-14 15:40:20','2025-11-14 15:40:20'),(6,'テスト３号店','３丁目','2025-11-28 16:54:48','2025-11-28 16:54:48'),(7,'テスト４号店','４丁目','2025-12-01 10:24:50','2025-12-01 10:24:50'),(8,'テスト１号店','東京都渋谷区Geek坂 1-1-1','2025-12-01 12:05:32','2025-12-01 12:05:32'),(9,'あいうえお','あ丁目２','2025-12-02 18:58:37','2025-12-02 18:58:37'),(10,'いい','いい','2025-12-02 19:00:57','2025-12-02 19:00:57');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stores_id` int NOT NULL,
  `roles_id` int NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_users_id` (`id`),
  KEY `fk_users_store` (`stores_id`),
  KEY `FKbgvg7xuekkcqmpvi3tgkxk85j` (`roles_id`),
  CONSTRAINT `fk_users_store` FOREIGN KEY (`stores_id`) REFERENCES `stores` (`id`),
  CONSTRAINT `FKbgvg7xuekkcqmpvi3tgkxk85j` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,1,'テスト','０号',25,'test00@gmail.com','$2a$10$H9UT56nFjC3wzqKrLzVUe.qQgvMlFVhkTSVEy/6s3ddXqCYMqzgyy','2025-10-31 07:23:06','2025-11-06 14:54:56','000'),(2,1,1,'テスト','１号渋谷',25,'test01@gmail.com','$2a$10$BSeugdpfYbBKOCRiyd5R/eGdLmpFAZ/a3ThwjCGVWQrMYenlnzEB2','2025-11-06 23:32:23','2025-11-27 08:57:51','00011112222'),(3,2,1,'テスト','２号新宿',25,'test02@gmail.com','$2a$10$0OBo5C2.PdzB5CcsOmcwv.wo.kh4N6UbVO5Cz5zXbNZQVInWkrr4W','2025-11-06 23:32:23','2025-11-06 14:55:36','00011112222'),(4,1,4,'テスト','３号',25,'test03@gmail.com','$2a$10$dtCx8LnA0FkHxoXxGzz7Je1jLlJVvSb0AU6d.1rivpNJzjVvTh8Zu','2025-11-07 02:05:51','2025-11-07 02:05:51','00011112222'),(5,2,1,'テスト','４号新宿',25,'test04@gmail.com','$2a$10$n5mEbfyID6bqzXLAVL495.O52/mnEbkwlvgfpgYqh/FnAqYZQixv6','2025-11-10 15:11:37','2025-11-10 15:11:37','00011112222'),(6,3,1,'テスト','５号池袋',25,'test05@gmail.com','$2a$10$21isPkXIx3i1dPvuO3AF0uDuEn8ScPzicA.rIA3j3NLevf1erkWxG','2025-11-27 09:03:42','2025-11-27 09:03:42','00011112222'),(7,1,3,'テスト','６号',25,'test06@gmail.com','$2a$10$tLqvbziAhei9MReFdOsWcubVEt0j2ud2PKB.L6IIlijToEmtQN382','2025-11-28 10:46:07','2025-11-28 10:46:07','00011112222'),(8,4,2,'テスト','７号',25,'test07@gmail.com','$2a$10$doztRsNYoHC2h1iZBDdlAeJZDrcXN/8s5NC7LqtBmOJiIh8z3O7xe','2025-12-01 10:40:25','2025-12-01 10:40:25','7777'),(9,9,3,'テスト','８号',8,'test08@gmail.com','$2a$10$KYRuHfONFSj6IKeAXuNRL.zfMLvpwHJXzMaqpFIZI9JP8jWHYaz5S','2025-12-02 19:04:27','2025-12-02 19:04:27','8888'),(10,1,4,'テスト','９号',9,'test09@gmail.com','$2a$10$Ny7C/Q.u9wpPAo4R9ZL6uuN10hDJ65acDWwaQehOUkVUpxuHnKZmC','2026-01-08 18:39:05','2026-01-08 18:39:05','9999'),(11,1,5,'テスト','１０号',10,'test10@gmail.com','$2a$10$OCyWle9ugSThok8uHLeOQetTxFNtVtdmlNIHelpwmsCBRg3SqjqpG','2026-01-13 15:00:49','2026-01-13 15:00:49','1010'),(12,1,1,'テスト','１１号',11,'test11@gmail.com','$2a$10$IKppCSYTBXYTTKLEi/PEse4XeCcTlE9HG76dRVAZGr82vJRa7l4Zy','2026-01-13 15:54:50','2026-01-13 15:54:50','1111'),(13,1,4,'テスト','１２号',12,'test12@gmail.com','$2a$10$DSlTRRfjSBTppmmc/1pHyeoK9aqNsfBEC1Uz2JBDTvQTHTPMXA6gq','2026-01-13 16:29:33','2026-01-13 16:29:33','1212'),(14,1,5,'テスト','１３号',13,'test13@gmail.com','$2a$10$zsO.FjGGcLCLWdSEzr6U0eNIS8gy4AAdwITCIbc1PeDdet.j6ZQom','2026-01-13 16:31:11','2026-01-13 16:31:11','1313'),(15,1,1,'テスト','１４号',14,'test14@gmail.com','$2a$10$h/dQRck2vHe5PlL1W0sFY.357IteVKIawoEqF57GWkiX.IFN5.T0G','2026-01-13 16:32:24','2026-01-13 16:32:24','1414'),(16,1,4,'テスト','１５号',15,'test15@gmail.com','$2a$10$8JsqSs9B6oO8xhZk.z.5uefHscA0MUJ3IIi3p1kurMR954KMqOOtm','2026-01-13 16:35:05','2026-01-13 16:35:05','1515'),(17,1,1,'テスト','１６号',16,'test16@gmail.com','$2a$10$DVu64rSZhk1EMoQp2UJPDudr6fLKkKOloxt1Bu47wQp3FS4wCNNSm','2026-01-15 14:10:59','2026-01-20 14:17:30','000-0000-1616'),(18,1,1,'テスト','17号',17,'test17@gmail.com','$2a$10$IDHLOih2SmkkNNAQST1vTe/JDnR7w6PyUPzdSE4wwHGr36FLbQgDi','2026-01-28 11:07:37','2026-01-28 11:07:37','000-0000-1717');
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

-- Dump completed on 2026-02-05 21:16:49
