CREATE DATABASE  IF NOT EXISTS GOOGLE_API
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;
USE GOOGLE_API;
--
-- Table structure for table role
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'User');
INSERT INTO role VALUES (3,'Manager');
INSERT INTO role VALUES (5,'Approver');
UNLOCK TABLES;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dummy data user
INSERT INTO user VALUES (1,'thiendn8','$2a$10$KVpDB4r.zgJm.Oi.izwggunBNd/Z7h6BP0HGtPKjKMB4PTFdJx0cy');
--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dummy data for user_role
INSERT INTO user_role (user_id, role_id) VALUES (1,3);
--
-- Table structure for table `cre_timesheet`
--
DROP TABLE IF EXISTS cre_timesheet;
CREATE TABLE cre_timesheet(
`id` int(11) NOT NULL auto_increment,
`code` varchar(16) NOT NULL,
`name` varchar(30) NOT NULL,
type_request varchar(10) NOT NULL,
time_from varchar(50) NOT NULL,
time_to varchar(50) NOT NULL,
group_lead varchar(30) NOT NULL,
team_lead varchar(30) NOT NULL,
`status` varchar(30) NULL,
reason_request varchar(500) NULL,
time_offset varchar(50) NULL,
PRIMARY KEY (`id`)
);
