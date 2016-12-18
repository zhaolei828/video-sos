/*
SQLyog Community v12.17 (64 bit)
MySQL - 5.6.35-log : Database - video_sos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`video_sos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `video_sos`;

/*Table structure for table `emrg_contact` */

DROP TABLE IF EXISTS `emrg_contact`;

CREATE TABLE `emrg_contact` (
  `ID` bigint(20) NOT NULL,
  `EMRG_CONTACT_NAME` varchar(50) DEFAULT NULL,
  `ENRG_CONTACT_EMAIL` varchar(255) DEFAULT NULL,
  `EMRG_CONTACT_PHONE` varchar(20) DEFAULT NULL,
  `BAND_USER` bigint(20) DEFAULT NULL,
  `ENABLE_FLAG` varchar(1) DEFAULT 'Y',
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `USER_EMAIL` varchar(255) DEFAULT NULL,
  `USER_PHONE` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `USER_GROUP` varchar(1) DEFAULT '1' COMMENT '0:管理员；1：普通用户',
  `ENABLE_FLAG` varchar(1) DEFAULT 'Y',
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `ID` bigint(20) NOT NULL,
  `VIDEO_NAME` varchar(50) DEFAULT NULL,
  `VIDEO_PATH` varchar(255) DEFAULT NULL,
  `UPLOAD_TIME` datetime DEFAULT NULL,
  `UPLOAD_ADDRESS_JSON` varchar(500) DEFAULT NULL,
  `ENABLE_FLAG` varchar(1) DEFAULT 'Y',
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
