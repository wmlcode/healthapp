
DROP DATABASE IF EXISTS `healthapp`;
CREATE DATABASE `healthapp`;
USE `healthapp`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `alternate_contact_number` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `state_code` varchar(20) DEFAULT NULL,
  `country_code` varchar(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'doc@ha.ru','123','Dr. Evil',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,'2018-11-21 10:00:01','2018-11-21 10:00:01',1),(2,'user@ha.ru','123','Mini Me',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,'2018-11-21 10:00:02','2018-11-21 10:00:02',0),(3,'doc1@ha.ru','123','Dr. Watson',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,'2018-11-21 10:00:01','2018-11-21 10:00:01',1),(4,'user1@ha.ru','123','Bill Gates',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,'2018-11-21 10:00:02','2018-11-21 10:00:02',0);
UNLOCK TABLES;

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `speciality_code` varchar(20) NOT NULL DEFAULT 'physician',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `doctor` WRITE;
INSERT INTO `doctor` VALUES (1,'PHYSICIAN','2018-11-21 10:00:01','2018-11-21 10:00:01',1);
INSERT INTO `doctor` VALUES (2,'PHYSICIAN','2018-11-21 10:00:01','2018-11-21 10:00:01',3);
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_access_token` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_approvals` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_client_details` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_client_token` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_code` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `oauth_refresh_token` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `rx`;
CREATE TABLE `rx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `doctor_id` bigint(20) NOT NULL,
  `symptoms` varchar(250) DEFAULT NULL,
  `medicine` varchar(250) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `rx_user_id_fk` (`user_id`),
  KEY `rx_doctor_id_fk` (`doctor_id`),
  CONSTRAINT `rx_doctor_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `rx_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `rx` WRITE;
INSERT INTO `rx` VALUES (1,2,1,'cold & fever','cetrizine','2018-11-21 10:00:02','2018-11-21 10:00:02');
INSERT INTO `rx` VALUES (2,4,1,'headaches','aspirin','2018-11-21 10:00:02','2018-11-21 10:00:02');
UNLOCK TABLES;
