/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : itmd4515

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-10-20 21:15:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `Comment` varchar(2000) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `Event_Id` bigint(20) NOT NULL,
  `Student_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`PID`),
  KEY `FK_comments_Student_Id` (`Student_Id`),
  KEY `FK_comments_Event_Id` (`Event_Id`),
  CONSTRAINT `FK_comments_Event_Id` FOREIGN KEY (`Event_Id`) REFERENCES `event_store` (`PID`),
  CONSTRAINT `FK_comments_Student_Id` FOREIGN KEY (`Student_Id`) REFERENCES `students` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '2015-10-20 21:10:43', '0000-00-00 00:00:00', 'Hi', '1', '1', '2');
INSERT INTO `comments` VALUES ('3', '2015-10-20 21:10:43', '0000-00-00 00:00:00', 'Java EE is fun.', '1', '1', '3');
INSERT INTO `comments` VALUES ('4', '2015-10-20 21:10:43', '2015-10-20 21:10:43', '-- This is an updated value --', '2', '2', '3');

-- ----------------------------
-- Table structure for `departments`
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Description` varchar(2000) DEFAULT NULL,
  `Dept_Name` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `Dept_Name` (`Dept_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('1', 'IIT Architecture welcomes students, faculty, and guests from around the globe who share our interest in “Rethinking the Metropolis.” We will conduct research; we will analyze existing phenomena; we will learn from other disciplines.', 'College of Architecture', '1');
INSERT INTO `departments` VALUES ('2', '-- This is an updated value --', 'Kent College of Law', '2');
INSERT INTO `departments` VALUES ('3', 'IIT Armour College of Engineering (ACE) traces its roots to Armour Institute; founded in 1890. Armour Institute was founded after minister Frank W. Gunsaulus gave what is now known as the “Million Dollar Sermon.” At a time when higher education was reserved for society’s elite, Mr. Gunsaulus called for the donation of a million dollars to build a school that would prepare students of all backgrounds for leadership roles primarily as engineers in a changing industrial society. Philip Danforth Armour a prominent Chicago meat packer and grain merchant heard Gunsaulus’ call and made the donation. Armour also stipulated that Gunsaulus become the first president of the school, a position Gunsaulus held from the school’s founding in 1890 until his death in 1921. Some innovative inventions that stem from research at ACE are the cell phone, magnetic tape recording, and barcodes.', 'Armour College of Engineering', '1');
INSERT INTO `departments` VALUES ('5', 'Programs and courses at the School of Applied Technology provide a blend of theoretical content and practical application that utilizes a hands-on, reality-based approach to education. This allows students to apply what they learn in class to solve real-life problems. Students learn about new and emerging technologies and the application, integration, and administrative practices used in the effective management of these technologies.', 'School of Applied Technology', '1');

-- ----------------------------
-- Table structure for `department_offices`
-- ----------------------------
DROP TABLE IF EXISTS `department_offices`;
CREATE TABLE `department_offices` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Addr1` varchar(255) NOT NULL,
  `Addr2` varchar(255) DEFAULT NULL,
  `CState` int(11) NOT NULL,
  `City` varchar(255) NOT NULL,
  `EmailId` varchar(255) NOT NULL,
  `Phone` bigint(20) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Zip` int(11) NOT NULL,
  `Zip_Ext` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `Office_Dept_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `EmailId` (`EmailId`),
  UNIQUE KEY `Title` (`Title`),
  KEY `FK_department_offices_Office_Dept_Id` (`Office_Dept_Id`),
  CONSTRAINT `FK_department_offices_Office_Dept_Id` FOREIGN KEY (`Office_Dept_Id`) REFERENCES `departments` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_offices
-- ----------------------------
INSERT INTO `department_offices` VALUES ('2', 'Perlstein Hall', '10 W. 33rd St., Room 223', '12', 'Chicago', 'atech@iit.edu', '3125675290', 'School of Applied Technology, Main Office', '60616', '0', '1', '5');
INSERT INTO `department_offices` VALUES ('3', '565 West Adams Street', '', '12', 'Chicago', 'contact@kentlaw.iit.edu', '3129065000', 'Chicago-Kent College of Law', '60661', '3691', '1', '2');
INSERT INTO `department_offices` VALUES ('4', '6502 South Archer Road', '-- This is an updated value --', '12', 'Bedford Park', 'atech.moffet@iit.edu', '7085631576', 'School of Applied Technology, Moffet Campus', '60501', '1957', '2', '5');

-- ----------------------------
-- Table structure for `event_store`
-- ----------------------------
DROP TABLE IF EXISTS `event_store`;
CREATE TABLE `event_store` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AState` int(11) NOT NULL,
  `CDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Description` varchar(2000) DEFAULT NULL,
  `EBegin` datetime NOT NULL,
  `EEnd` datetime NOT NULL,
  `MDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `Title` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `Event_Dept_Id` bigint(20) NOT NULL,
  `Event_Venue_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `Title` (`Title`),
  KEY `FK_event_store_Event_Venue_Id` (`Event_Venue_Id`),
  KEY `FK_event_store_Event_Dept_Id` (`Event_Dept_Id`),
  CONSTRAINT `FK_event_store_Event_Dept_Id` FOREIGN KEY (`Event_Dept_Id`) REFERENCES `departments` (`PID`),
  CONSTRAINT `FK_event_store_Event_Venue_Id` FOREIGN KEY (`Event_Venue_Id`) REFERENCES `venues` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event_store
-- ----------------------------
INSERT INTO `event_store` VALUES ('1', '0', '2015-10-20 21:10:41', 'All F1 and J1 international students (both graduate and undergraduate) are required to attend International Student SOAR.', '2015-09-21 09:30:00', '2015-09-21 13:00:00', '2015-10-20 21:10:42', 'International Student Orientation', '2', '1', '2');
INSERT INTO `event_store` VALUES ('2', '0', '2015-10-20 21:10:41', '-- This is an updated value --', '2015-10-28 10:30:00', '2015-10-29 03:30:00', '2015-10-20 21:10:41', 'Homecoming Bog Party', '2', '1', '1');
INSERT INTO `event_store` VALUES ('3', '0', '2015-10-20 21:10:41', 'If you are in need professional attire, OCL will have a clothing closet in the lower level of Hermann Hall.', '2015-10-24 12:40:00', '2015-10-24 16:00:00', '0000-00-00 00:00:00', 'Fall 2015 Career Fair', '1', '5', '3');

-- ----------------------------
-- Table structure for `event_student_attends`
-- ----------------------------
DROP TABLE IF EXISTS `event_student_attends`;
CREATE TABLE `event_student_attends` (
  `Student_Id` bigint(20) NOT NULL,
  `Event_Id` bigint(20) NOT NULL,
  PRIMARY KEY (`Student_Id`,`Event_Id`),
  KEY `FK_event_student_attends_Event_Id` (`Event_Id`),
  CONSTRAINT `FK_event_student_attends_Event_Id` FOREIGN KEY (`Event_Id`) REFERENCES `event_store` (`PID`),
  CONSTRAINT `FK_event_student_attends_Student_Id` FOREIGN KEY (`Student_Id`) REFERENCES `students` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event_student_attends
-- ----------------------------
INSERT INTO `event_student_attends` VALUES ('2', '1');
INSERT INTO `event_student_attends` VALUES ('3', '2');

-- ----------------------------
-- Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EmailId` varchar(255) NOT NULL,
  `FName` varchar(255) NOT NULL,
  `Gender` char(1) NOT NULL,
  `LName` varchar(255) NOT NULL,
  `NotifyEvents` tinyint(1) NOT NULL DEFAULT '0',
  `Phone` bigint(20) NOT NULL,
  `Special` varchar(2000) DEFAULT NULL,
  `Student_Number` varchar(36) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `LoginId` int(11) NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `EmailId` (`EmailId`),
  UNIQUE KEY `Student_Number` (`Student_Number`),
  UNIQUE KEY `LoginId` (`LoginId`),
  CONSTRAINT `FK_students_LoginId` FOREIGN KEY (`LoginId`) REFERENCES `students_login` (`LOGINID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('2', 'spyrison@iit.edu', 'Scott', 'M', 'Spyrison', '1', '0', '-- This is an updated value --', '68e43a16-5421-424d-93b2-f5ef107ccfa3', '2', '2');
INSERT INTO `students` VALUES ('3', 'abhimani@hawk.iit.edu', 'Ankit', 'M', 'Bhimani', '1', '3125365229', 'Has undertaken Java EE in the first semester.', 'c32f28c5-8732-4475-8db5-ef89d842f462', '4', '1');

-- ----------------------------
-- Table structure for `students_login`
-- ----------------------------
DROP TABLE IF EXISTS `students_login`;
CREATE TABLE `students_login` (
  `LOGINID` int(11) NOT NULL AUTO_INCREMENT,
  `enc_pwd` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`LOGINID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students_login
-- ----------------------------
INSERT INTO `students_login` VALUES ('1', 'fwooa', 'abhimani');
INSERT INTO `students_login` VALUES ('2', null, 'sspyrison');
INSERT INTO `students_login` VALUES ('3', null, 'lleah');

-- ----------------------------
-- Table structure for `venues`
-- ----------------------------
DROP TABLE IF EXISTS `venues`;
CREATE TABLE `venues` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Addr1` varchar(255) NOT NULL,
  `Addr2` varchar(255) DEFAULT NULL,
  `CState` int(11) NOT NULL,
  `City` varchar(255) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Type` int(11) NOT NULL,
  `Zip` int(11) NOT NULL,
  `Zip_Ext` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `Title` (`Title`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of venues
-- ----------------------------
INSERT INTO `venues` VALUES ('1', '3241 S. Federal Street, Hermann Hall', '-- This is an updated value --', '12', 'Chicago', 'The BOG', '3', '60616', '0', '2');
INSERT INTO `venues` VALUES ('2', '3300 S. Federal Street', 'MTCC', '12', 'Chicago', 'McCormick Tribute Campus Center', '4', '60616', '3793', '1');
INSERT INTO `venues` VALUES ('3', '3241 S. Federal Street, Hermann Hall', null, '12', 'Chicago', 'Herman Hall Ballroom', '1', '60616', '0', '1');
