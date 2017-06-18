/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : bookStore

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 06/18/2017 16:56:41 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(50) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `introduce` varchar(500) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `isbn` bigint(20) DEFAULT NULL,
  `repertory` int(11) DEFAULT NULL,
  `cover` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collectorID` int(11) DEFAULT NULL,
  `bookID` int(11) DEFAULT NULL,
  `bookName` varchar(50) DEFAULT NULL,
  `bookPrice` float DEFAULT NULL,
  `bookCover` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `collecterID` (`collectorID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`collectorID`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookID` int(11) DEFAULT NULL,
  `buyerID` int(11) DEFAULT NULL,
  `commentTime` date DEFAULT NULL,
  `content` varchar(400) DEFAULT NULL,
  `buyerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookID` (`bookID`),
  KEY `buyerID` (`buyerID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`buyerID`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `indent`
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyerID` int(11) DEFAULT NULL,
  `bookID` int(11) DEFAULT NULL,
  `bargainTime` datetime DEFAULT NULL,
  `buyerAddress` varchar(300) DEFAULT NULL,
  `bookName` varchar(50) DEFAULT NULL,
  `bookCover` varchar(400) DEFAULT NULL,
  `bookPrice` float DEFAULT NULL,
  `bookPublisher` varchar(100) DEFAULT NULL,
  `commentState` int(11) DEFAULT NULL,
  `buyerMobile` bigint(20) DEFAULT NULL,
  `buyerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `buyerID` (`buyerID`),
  KEY `bookID` (`bookID`),
  KEY `buyerID_2` (`buyerID`),
  KEY `bookID_2` (`bookID`),
  CONSTRAINT `indent_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `indent_ibfk_3` FOREIGN KEY (`buyerID`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `mobile` bigint(20) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
