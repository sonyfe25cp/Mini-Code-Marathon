-- MySQL dump 10.13  Distrib 5.6.17, for osx10.9 (x86_64)
--
-- Host: 10.1.0.171    Database: webrisk
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `EvaluateItem`
--

DROP TABLE IF EXISTS `EvaluateItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EvaluateItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) DEFAULT NULL,
  `content` text,
  `createdAt` varchar(45) DEFAULT NULL,
  `articleType` varchar(45) DEFAULT NULL,
  `subType` varchar(45) DEFAULT NULL,
  `url` varchar(500) NOT NULL,
  `publishDate` varchar(45) DEFAULT NULL,
  `emotion` int(11) DEFAULT '0',
  `provider` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `url` (`url`(233)),
  KEY `publishDate` (`publishDate`)
) ENGINE=InnoDB AUTO_INCREMENT=132351 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `businessChannel`
--

DROP TABLE IF EXISTS `businessChannel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessChannel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `businessProcess`
--

DROP TABLE IF EXISTS `businessProcess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessProcess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `stockCode` varchar(45) DEFAULT NULL,
  `fullName` varchar(300) DEFAULT NULL,
  `englishName` varchar(300) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `personInLaw` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `logo` varchar(45) DEFAULT NULL,
  `attention` tinyint(2) DEFAULT '0',
  `registNum` varchar(64) DEFAULT NULL COMMENT '注册号',
  `foundDate` varchar(32) DEFAULT NULL,
  `operTerm` varchar(128) DEFAULT NULL COMMENT '营业期限',
  `regCaptial` varchar(64) DEFAULT NULL COMMENT '注册资本',
  `emplNum` varchar(128) DEFAULT NULL COMMENT '员工数',
  `turnover` varchar(128) DEFAULT NULL COMMENT '营业额',
  `description` text COMMENT '简介',
  `service` text COMMENT '产品或服务',
  `license` varchar(128) DEFAULT NULL COMMENT '营业执照',
  PRIMARY KEY (`id`),
  KEY `attention` (`attention`)
) ENGINE=InnoDB AUTO_INCREMENT=4395 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companyRelationShip`
--

DROP TABLE IF EXISTS `companyRelationShip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyRelationShip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstId` int(11) DEFAULT NULL,
  `secondId` int(11) DEFAULT NULL,
  `relationsJson` text,
  `groupId` int(11) DEFAULT NULL,
  `keyWordRelationsJson` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=615 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companyScore`
--

DROP TABLE IF EXISTS `companyScore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyScore` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `webScore` varchar(32) DEFAULT NULL,
  `access` varchar(32) DEFAULT NULL,
  `answerTime` varchar(32) DEFAULT NULL,
  `beauty` varchar(32) DEFAULT NULL,
  `function` varchar(32) DEFAULT NULL,
  `update` varchar(32) DEFAULT NULL,
  `leadScore` varchar(32) DEFAULT NULL,
  `leadArticle` varchar(32) DEFAULT NULL,
  `leadPos` varchar(32) DEFAULT NULL,
  `leadNeg` varchar(32) DEFAULT NULL,
  `mediaScore` varchar(32) DEFAULT NULL,
  `mediaArticle` varchar(32) DEFAULT NULL,
  `mediaPos` varchar(32) DEFAULT NULL,
  `mediaNeg` varchar(32) DEFAULT NULL,
  `chatScore` varchar(32) DEFAULT NULL,
  `chatAccounts` varchar(32) DEFAULT NULL,
  `chatPosts` varchar(32) DEFAULT NULL,
  `industryScore` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companyStrategy`
--

DROP TABLE IF EXISTS `companyStrategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyStrategy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `companyVIP`
--

DROP TABLE IF EXISTS `companyVIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyVIP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `positionArray` varchar(300) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `birth` varchar(45) DEFAULT NULL,
  `degree` varchar(45) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43965 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `aid` int(11) NOT NULL,
  `addDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyWord`
--

DROP TABLE IF EXISTS `keyWord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyWord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(45) DEFAULT NULL,
  `wordType` varchar(45) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `companyId` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `isSub` tinyint(4) DEFAULT '2',
  `parent` int(11) DEFAULT NULL,
  `filter` text,
  `sort` int(11) DEFAULT '0',
  `subType` varchar(64) DEFAULT NULL,
  `shouldShow` int(4) DEFAULT '1',
  `isLoad` int(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publishDate` varchar(45) DEFAULT NULL,
  `title` varchar(1000) NOT NULL,
  `content` mediumtext NOT NULL,
  `contentHtml` mediumtext,
  `url` varchar(1000) NOT NULL,
  `html` mediumtext,
  `provider` varchar(45) DEFAULT NULL,
  `newsType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `publishDate` (`publishDate`),
  KEY `url` (`url`(233))
) ENGINE=InnoDB AUTO_INCREMENT=116192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organizationalStructure`
--

DROP TABLE IF EXISTS `organizationalStructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizationalStructure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '公司名称',
  `pros` text CHARACTER SET utf8mb4 NOT NULL COMMENT '优点',
  `cons` text CHARACTER SET utf8mb4 NOT NULL COMMENT '缺点',
  `advice` text CHARACTER SET utf8mb4 NOT NULL COMMENT '对管理者的建议',
  `title` varchar(200) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '评论主题'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sentitiveWord`
--

DROP TABLE IF EXISTS `sentitiveWord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sentitiveWord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(128) NOT NULL,
  `weight` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userEmail`
--

DROP TABLE IF EXISTS `userEmail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userEmail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weiboStatus`
--

DROP TABLE IF EXISTS `weiboStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weiboStatus` (
  `sid` varchar(32) NOT NULL COMMENT 'status id',
  `mid` varchar(32) DEFAULT NULL COMMENT '微博MID',
  `uid` varchar(32) NOT NULL COMMENT '作者id',
  `createdAt` datetime NOT NULL COMMENT 'status创建时间',
  `idstr` bigint(64) DEFAULT NULL COMMENT '保留字段,请勿使用',
  `text` text COMMENT '微博内容',
  `source` varchar(256) DEFAULT NULL COMMENT '微博来源',
  `favorited` tinyint(2) DEFAULT NULL COMMENT '是否已收藏',
  `truncated` tinyint(2) DEFAULT NULL,
  `inReplyToStatusId` bigint(20) DEFAULT NULL COMMENT '回复ID',
  `inReplyToUserId` bigint(20) DEFAULT NULL COMMENT '回复人ID',
  `inReplyToScreenName` varchar(256) DEFAULT NULL COMMENT '回复人昵称',
  `thumbnailPic` varchar(256) DEFAULT NULL COMMENT '微博内容中的图片的缩略地址',
  `bmiddlePic` varchar(256) DEFAULT NULL COMMENT '中型图片',
  `originalPic` varchar(256) DEFAULT NULL COMMENT '原始图片',
  `retweetedStatus` varchar(256) DEFAULT NULL COMMENT '转发的博文，内容为status，如果不是转发，则没有此字段',
  `geo` varchar(256) DEFAULT NULL COMMENT '地理信息，保存经纬度，没有时不返回此字段',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `repostsCount` int(11) DEFAULT NULL COMMENT '转发数',
  `commentsCount` int(11) DEFAULT NULL COMMENT '评论数',
  `annotations` varchar(512) DEFAULT NULL COMMENT '数据，没有时不返回此字段',
  `mlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weiboUsers`
--

DROP TABLE IF EXISTS `weiboUsers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weiboUsers` (
  `uid` varchar(32) NOT NULL COMMENT '用户UID',
  `screenName` varchar(256) DEFAULT NULL COMMENT '微博昵称',
  `name` varchar(256) DEFAULT NULL COMMENT '友好显示名称',
  `province` int(8) DEFAULT NULL COMMENT '省份编码',
  `city` int(8) DEFAULT NULL COMMENT '城市编码',
  `location` varchar(256) DEFAULT NULL COMMENT '地址',
  `description` text COMMENT '个人描述',
  `url` varchar(256) DEFAULT NULL COMMENT '用户博客地址',
  `profileImageUrl` varchar(256) DEFAULT NULL COMMENT '自定义图像',
  `userDomain` varchar(256) DEFAULT NULL COMMENT '用户个性化URL',
  `gender` varchar(16) DEFAULT NULL COMMENT '性别',
  `followersCount` int(11) DEFAULT NULL COMMENT '粉丝数',
  `friendsCount` int(11) DEFAULT NULL COMMENT '关注数',
  `statusesCount` int(11) DEFAULT NULL COMMENT '微博数',
  `favouritesCount` int(11) DEFAULT NULL COMMENT '收藏数',
  `createdAt` datetime DEFAULT NULL COMMENT '创建时间',
  `following` tinyint(4) DEFAULT NULL COMMENT '保留字段,是否已关注',
  `verified` tinyint(4) DEFAULT NULL COMMENT '加V标示',
  `verifiedType` int(11) DEFAULT NULL COMMENT '认证类型',
  `allowAllActMsg` tinyint(4) DEFAULT NULL COMMENT '是否允许所有人给我发私信',
  `allowAllComment` tinyint(4) DEFAULT NULL COMMENT '是否允许所有人对我的微博进行评论',
  `followMe` tinyint(4) DEFAULT NULL COMMENT '此用户是否关注我',
  `avatarLarge` varchar(256) DEFAULT NULL COMMENT '大头像地址',
  `onlineStatus` tinyint(4) DEFAULT NULL COMMENT '用户在线状态',
  `biFollowersCount` int(11) DEFAULT NULL COMMENT '互粉数',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息，在查询用户关系时提供此字段',
  `lang` varchar(64) DEFAULT NULL COMMENT '用户语言版本',
  `verifiedReason` varchar(256) DEFAULT NULL COMMENT '认证原因',
  `weihao` varchar(256) DEFAULT NULL COMMENT '微號',
  `statusId` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-11 16:00:18
