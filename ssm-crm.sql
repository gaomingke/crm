/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.37 : Database - ssm_crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm_crm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm_crm`;

/*Table structure for table `role_user` */

DROP TABLE IF EXISTS `role_user`;

CREATE TABLE `role_user` (
  `roleid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`roleid`,`userid`),
  KEY `fk_t_role_has_t_user_t_user1_idx` (`userid`),
  KEY `fk_t_role_has_t_user_t_role_idx` (`roleid`),
  CONSTRAINT `fk_t_role_has_t_user_t_role` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_role_has_t_user_t_user1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_user` */

insert  into `role_user`(`roleid`,`userid`) values (1,1),(1,8),(2,8),(3,9),(3,10),(3,11),(1,12),(2,12),(3,12),(2,13);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`rolename`) values (1,'管理员'),(2,'经理'),(3,'员工');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL COMMENT '员工姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `tel` varchar(45) DEFAULT NULL COMMENT '手机号码',
  `createtime` varchar(45) DEFAULT NULL COMMENT '创建时间',
  `state` varchar(45) DEFAULT NULL COMMENT '员工状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`tel`,`createtime`,`state`) values (1,'admin','f76a319dbd7ad4d3c153c775ad503f37','138','2016-04-28 09:51','正常'),(2,'张小龙','f76a319dbd7ad4d3c153c775ad503f37','13800000001','2016-04-28 09:51','正常'),(3,'刘莉莉','f76a319dbd7ad4d3c153c775ad503f37','13800000002','2016-04-28 09:51','正常'),(4,'刘潇潇','f76a319dbd7ad4d3c153c775ad503f37','13800000003','2016-04-28 09:51','正常'),(5,'费希特','f76a319dbd7ad4d3c153c775ad503f37','13800000004','2016-04-28 09:51','禁用'),(6,'赵玲玲','f76a319dbd7ad4d3c153c775ad503f37','13800000005','2016-04-28 09:51','禁用'),(7,'马历涛','f76a319dbd7ad4d3c153c775ad503f37','13800000006','2016-04-28 09:51','正常'),(8,'李四','f76a319dbd7ad4d3c153c775ad503f37','18900000000','2016-04-29 15:05','正常'),(9,'王武武','f76a319dbd7ad4d3c153c775ad503f37','13700000000','2016-04-29 15:09','正常'),(10,'王溜溜','f76a319dbd7ad4d3c153c775ad503f37','1870000000','2016-04-29 15:11','正常'),(11,'jack','f76a319dbd7ad4d3c153c775ad503f37','190','2016-04-29 15:13','正常'),(12,'rose','f76a319dbd7ad4d3c153c775ad503f37','111','2016-04-29 15:13','正常'),(13,'郑哲丽','f76a319dbd7ad4d3c153c775ad503f37','1300000000','2016-04-29 15:14','禁用');

/*Table structure for table `t_user_log` */

DROP TABLE IF EXISTS `t_user_log`;

CREATE TABLE `t_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logintime` varchar(45) DEFAULT NULL,
  `loginip` varchar(45) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_log_t_user1_idx` (`userid`),
  CONSTRAINT `fk_t_user_log_t_user1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
