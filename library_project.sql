-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: library_project
-- ------------------------------------------------------
-- Server version	8.0.32

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
CREATE TABLE `books`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `active`      bit(1)                                  DEFAULT NULL,
    `create_at`   datetime(6) DEFAULT NULL,
    `create_by`   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at`   datetime(6) DEFAULT NULL,
    `update_by`   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `author`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `book_title`  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `image`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `price`       bigint                                  DEFAULT NULL,
    `quantity`    int                                     DEFAULT NULL,
    `slug`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `category_id` bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `FKleqa3hhc0uhfvurq6mil47xk0` (`category_id`),
    CONSTRAINT `FKleqa3hhc0uhfvurq6mil47xk0` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK
TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `active`        bit(1)                                  DEFAULT NULL,
    `create_at`     datetime(6) DEFAULT NULL,
    `create_by`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at`     datetime(6) DEFAULT NULL,
    `update_by`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `category_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `slug`          varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK
TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories`
VALUES (1, _binary '', '2023-04-12 21:46:08.146000', 'Librarian', '2023-04-12 21:46:08.147000', NULL, 'Truyện tranh',
        'truyen-tranh'),
       (2, _binary '', '2023-04-12 21:46:08.187000', 'Librarian', '2023-04-12 21:46:08.187000', NULL, 'Sử thi',
        'Su-thi'),
       (3, _binary '', '2023-04-12 21:46:08.194000', 'Librarian', '2023-04-12 21:46:08.194000', NULL, 'Hài kịch',
        'hai-kich'),
       (4, _binary '', '2023-04-12 21:46:08.202000', 'Librarian', '2023-04-12 21:46:08.202000', NULL, 'Chánh trị',
        'chanh-tri'),
       (5, _binary '', '2023-04-12 21:46:08.213000', 'Librarian', '2023-04-12 21:46:08.213000', NULL, 'Truyện ngắn',
        'truyen-ngan'),
       (6, _binary '', '2023-04-12 21:46:08.223000', 'Librarian', '2023-04-12 21:46:08.223000', NULL, 'Truyện hài',
        'truyen-hai'),
       (7, _binary '', '2023-04-12 21:46:08.234000', 'Librarian', '2023-04-12 21:46:08.234000', NULL, 'Lịch sử',
        'lich-su'),
       (8, _binary '', '2023-04-12 21:46:08.244000', 'Librarian', '2023-04-12 21:46:08.244000', NULL, 'Truyện thám tử',
        'truyen-tham-tu'),
       (9, _binary '', '2023-04-12 21:46:08.250000', 'Librarian', '2023-04-12 21:46:08.250000', NULL, 'Triết học',
        'triet-hoc');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `active`           bit(1)                                  DEFAULT NULL,
    `create_at`        datetime(6) DEFAULT NULL,
    `create_by`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at`        datetime(6) DEFAULT NULL,
    `update_by`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `book_id`          bigint                                  DEFAULT NULL,
    `date_due`         datetime(6) DEFAULT NULL,
    `date_of_checkout` datetime(6) DEFAULT NULL,
    `date_returned`    datetime(6) DEFAULT NULL,
    `quantity`         int                                     DEFAULT NULL,
    `status`           varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `user_id`          bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `FK6xxlcjc0rqtn5nq28vjnx5t9d` (`user_id`),
    CONSTRAINT `FK6xxlcjc0rqtn5nq28vjnx5t9d` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK
TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `refresh_tokens`
--

DROP TABLE IF EXISTS `refresh_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_tokens`
(
    `id`            bigint                                  NOT NULL AUTO_INCREMENT,
    `active`        bit(1)                                  DEFAULT NULL,
    `create_at`     datetime(6) DEFAULT NULL,
    `create_by`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at`     datetime(6) DEFAULT NULL,
    `update_by`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `expiry_date`   datetime(6) NOT NULL,
    `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `user_id`       bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_1yihy5j142kjit22kgccjixro` (`refresh_token`),
    KEY             `FK1lih5y2npsf8u5o3vhdb9y0os` (`user_id`),
    CONSTRAINT `FK1lih5y2npsf8u5o3vhdb9y0os` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_tokens`
--

LOCK
TABLES `refresh_tokens` WRITE;
/*!40000 ALTER TABLE `refresh_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `refresh_tokens` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles`
(
    `id`        bigint                                  NOT NULL AUTO_INCREMENT,
    `active`    bit(1)                                  DEFAULT NULL,
    `create_at` datetime(6) DEFAULT NULL,
    `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at` datetime(6) DEFAULT NULL,
    `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `role_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `slug`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK
TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles`
VALUES (1, _binary '', '2023-04-12 21:46:08.287000', 'anonymousUser', '2023-04-12 21:46:08.287000', NULL, 'admin',
        'admin'),
       (2, _binary '', '2023-04-12 21:46:08.294000', 'anonymousUser', '2023-04-12 21:46:08.294000', NULL, 'member',
        'member'),
       (3, _binary '', '2023-04-12 21:46:08.300000', 'anonymousUser', '2023-04-12 21:46:08.300000', NULL, 'librarian',
        'librarian');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `id`        bigint                                                        NOT NULL AUTO_INCREMENT,
    `active`    bit(1)                                  DEFAULT NULL,
    `create_at` datetime(6) DEFAULT NULL,
    `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `update_at` datetime(6) DEFAULT NULL,
    `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `avatar`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `email`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `password`  varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `username`  varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK
TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY       `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
    CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK
TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13  8:29:28
