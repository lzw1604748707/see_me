/*
Navicat MySQL Data Transfer

Source Server         : see-me
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : see_me

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-18 22:07:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '反馈信息Id',
  `accountType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '反馈账号类型 0-游客 1-用户 2-合作商',
  `accountId` int(11) NOT NULL COMMENT '反馈人Id',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '建议内容',
  `type` tinyint(2) NOT NULL COMMENT '反馈建议 0-优化 1-平台问题反馈 2-合作 3-其他',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '建议状态 0 待确认 1 确认 2 已处理 3 回绝  4 删除',
  PRIMARY KEY (`id`),
  KEY `fk_feedback_accountId` (`accountId`),
  CONSTRAINT `fk_feedback_accountId` FOREIGN KEY (`accountId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('2', '0', '0', '多修点bug吧', '1', '2019-05-25 11:36:54', '2');
INSERT INTO `feedback` VALUES ('3', '2', '0', 'bug太多', '1', '2019-05-25 10:28:38', '3');
INSERT INTO `feedback` VALUES ('4', '0', '0', '多修点bug吧', '1', '2019-05-25 11:36:54', '3');
INSERT INTO `feedback` VALUES ('5', '3', '2', '项目详情页建议丰富一点', '1', '2019-05-26 16:28:52', '2');
INSERT INTO `feedback` VALUES ('6', '0', '0', '加油，正确做的更好', '0', '2019-05-26 09:08:25', '3');
INSERT INTO `feedback` VALUES ('7', '0', '0', '嘎嘎嘎', '1', '2019-05-28 12:39:14', '2');
INSERT INTO `feedback` VALUES ('8', '0', '0', '嘎噶', '2', '2019-05-28 12:39:25', '0');
INSERT INTO `feedback` VALUES ('9', '0', '0', 'gaga agaga', '0', '2019-05-28 15:15:45', '0');

-- ----------------------------
-- Table structure for sm_photo_collection
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_collection`;
CREATE TABLE `sm_photo_collection` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '作品集id',
  `createrId` int(100) NOT NULL COMMENT '用户id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品集标题',
  `isPraise` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否好评集 0 不是  1 是',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `isDelete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0 存在 1 删除)',
  `status` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '状态（0上架 1 下架）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_user_email` (`isPraise`),
  KEY `fk_project_createrid` (`createrId`),
  CONSTRAINT `fk_collection_createrid` FOREIGN KEY (`createrId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sm_photo_collection
-- ----------------------------
INSERT INTO `sm_photo_collection` VALUES ('1', '1', '江南', '0', '2019-05-15 20:35:41', '2019-05-28 12:12:08', '0', '1');
INSERT INTO `sm_photo_collection` VALUES ('2', '1', '枫叶', '0', '2019-05-15 20:35:41', '2019-05-24 20:35:45', '1', '0');
INSERT INTO `sm_photo_collection` VALUES ('6', '-1', '风雨同舟', '0', '2019-05-23 10:23:43', '2019-05-23 11:16:02', '0', '0');
INSERT INTO `sm_photo_collection` VALUES ('7', '-1', '照耀', '0', '2019-05-23 10:24:24', '2019-05-28 12:12:16', '0', '1');
INSERT INTO `sm_photo_collection` VALUES ('8', '-1', '风雨飘摇', '0', '2019-05-23 10:25:06', '2019-05-23 10:25:06', '0', '0');
INSERT INTO `sm_photo_collection` VALUES ('9', '-1', '神奇的好罗', '0', '2019-05-25 03:58:42', '2019-05-26 11:37:40', '0', '0');
INSERT INTO `sm_photo_collection` VALUES ('10', '-1', '和谐美满', '0', '2019-05-28 12:10:34', '2019-05-28 12:10:34', '0', '0');

-- ----------------------------
-- Table structure for sm_photo_collection_record
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_collection_record`;
CREATE TABLE `sm_photo_collection_record` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '作品集记录id',
  `collectionId` int(100) NOT NULL COMMENT '作品集id',
  `projectId` int(100) DEFAULT NULL COMMENT '项目id',
  `createDate` datetime NOT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`),
  KEY `fk_col_record_collectionid` (`collectionId`),
  KEY `fk_col_record_projectid` (`projectId`),
  CONSTRAINT `fk_col_record_collectionid` FOREIGN KEY (`collectionId`) REFERENCES `sm_photo_collection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_col_record_projectid` FOREIGN KEY (`projectId`) REFERENCES `sm_photo_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sm_photo_collection_record
-- ----------------------------
INSERT INTO `sm_photo_collection_record` VALUES ('2', '2', '2', '2019-05-22 20:47:24');
INSERT INTO `sm_photo_collection_record` VALUES ('4', '2', '1', '2019-05-22 20:47:24');
INSERT INTO `sm_photo_collection_record` VALUES ('9', '6', '1', '2019-05-23 11:16:02');
INSERT INTO `sm_photo_collection_record` VALUES ('10', '6', '5', '2019-05-23 11:16:02');
INSERT INTO `sm_photo_collection_record` VALUES ('22', '9', '1', '2019-05-26 11:37:40');
INSERT INTO `sm_photo_collection_record` VALUES ('23', '9', '3', '2019-05-26 11:37:40');
INSERT INTO `sm_photo_collection_record` VALUES ('24', '9', '4', '2019-05-26 11:37:40');
INSERT INTO `sm_photo_collection_record` VALUES ('25', '9', '6', '2019-05-26 11:37:40');
INSERT INTO `sm_photo_collection_record` VALUES ('26', '9', '5', '2019-05-26 11:37:40');
INSERT INTO `sm_photo_collection_record` VALUES ('27', '10', '6', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('28', '10', '9', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('29', '10', '5', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('30', '10', '7', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('31', '10', '12', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('32', '10', '2', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('33', '10', '8', '2019-05-28 12:10:34');
INSERT INTO `sm_photo_collection_record` VALUES ('43', '1', '1', '2019-05-28 12:12:08');
INSERT INTO `sm_photo_collection_record` VALUES ('44', '1', '2', '2019-05-28 12:12:08');
INSERT INTO `sm_photo_collection_record` VALUES ('45', '1', '4', '2019-05-28 12:12:08');
INSERT INTO `sm_photo_collection_record` VALUES ('46', '1', '5', '2019-05-28 12:12:08');
INSERT INTO `sm_photo_collection_record` VALUES ('47', '1', '6', '2019-05-28 12:12:08');
INSERT INTO `sm_photo_collection_record` VALUES ('48', '7', '3', '2019-05-28 12:12:16');

-- ----------------------------
-- Table structure for sm_photo_comment
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_comment`;
CREATE TABLE `sm_photo_comment` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `worksId` int(100) NOT NULL COMMENT '评论作品id',
  `createrId` int(100) NOT NULL COMMENT '评论人id',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comment_worksid` (`worksId`),
  KEY `fk_comment_createrid` (`createrId`),
  CONSTRAINT `fk_comment_createrid` FOREIGN KEY (`createrId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_worksid` FOREIGN KEY (`worksId`) REFERENCES `sm_photo_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sm_photo_comment
-- ----------------------------
INSERT INTO `sm_photo_comment` VALUES ('1', '1', '1', '啊发发', '2019-05-22 14:30:35');
INSERT INTO `sm_photo_comment` VALUES ('2', '1', '-1', '该项目存在问题', '2019-05-22 09:07:59');
INSERT INTO `sm_photo_comment` VALUES ('3', '2', '-1', '123123123123123', '2019-05-25 03:54:55');
INSERT INTO `sm_photo_comment` VALUES ('5', '2', '-1', '还行继续努力', '2019-05-26 11:37:54');
INSERT INTO `sm_photo_comment` VALUES ('6', '3', '-1', '包含违法信息', '2019-05-26 11:38:10');
INSERT INTO `sm_photo_comment` VALUES ('7', '7', '-1', '这个作品很棒', '2019-05-28 12:08:15');

-- ----------------------------
-- Table structure for sm_photo_field
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_field`;
CREATE TABLE `sm_photo_field` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '设计领域id',
  `fieldName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设计领域名称',
  `createDate` datetime NOT NULL COMMENT '建立时间',
  `providerId` int(10) NOT NULL COMMENT '提供者Id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态( 0 审核中 1 通过  2 未通过)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sm_photo_field
-- ----------------------------
INSERT INTO `sm_photo_field` VALUES ('1', 'Camera', '2019-05-23 15:20:18', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('2', 'photoshop', '2019-05-25 02:00:08', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('3', 'AI', '2019-05-23 13:44:41', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('5', 'illustrat', '2019-05-23 14:17:04', '-1', '2');
INSERT INTO `sm_photo_field` VALUES ('6', 'motion', '2019-05-23 14:17:10', '-1', '2');
INSERT INTO `sm_photo_field` VALUES ('7', 'interaction', '2019-05-23 14:17:14', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('8', 'D', '2019-05-23 14:17:18', '-1', '2');
INSERT INTO `sm_photo_field` VALUES ('9', 'E', '2019-05-23 14:17:21', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('10', 'F', '2019-05-23 14:17:25', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('13', 'P', '2019-05-23 14:35:08', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('14', 'Q', '2019-05-23 14:35:14', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('15', 'R', '2019-05-23 14:35:22', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('16', 'S', '2019-05-23 14:35:26', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('17', 'T', '2019-05-23 14:35:29', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('18', 'U', '2019-05-23 14:35:32', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('19', 'V', '2019-05-23 14:35:36', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('20', 'W', '2019-05-23 14:35:39', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('21', 'X', '2019-05-23 14:35:43', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('22', 'Y', '2019-05-23 14:35:46', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('23', 'Z', '2019-05-23 14:35:49', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('25', '一个平台来维护', '2019-05-28 12:13:12', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('26', 'accs', '2019-05-28 12:26:43', '-1', '1');
INSERT INTO `sm_photo_field` VALUES ('27', '手写', '2019-05-28 12:31:31', '-1', '1');

-- ----------------------------
-- Table structure for sm_photo_praise
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_praise`;
CREATE TABLE `sm_photo_praise` (
  `id` int(10) NOT NULL COMMENT '好评id',
  `giverId` int(10) NOT NULL COMMENT '赠与人id',
  `projectId` int(10) NOT NULL COMMENT '项目id',
  `createDate` datetime NOT NULL COMMENT '赠与时间',
  `status` char(1) NOT NULL COMMENT '状态（0 浏览 1好评）',
  PRIMARY KEY (`id`),
  KEY `fk_praise_giverId` (`giverId`),
  KEY `fk_praise_projectId` (`projectId`),
  CONSTRAINT `fk_praise_projectId` FOREIGN KEY (`projectId`) REFERENCES `sm_photo_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sm_photo_praise
-- ----------------------------
INSERT INTO `sm_photo_praise` VALUES ('1', '1', '1', '2019-05-22 00:59:48', '0');
INSERT INTO `sm_photo_praise` VALUES ('2', '2', '1', '2019-05-18 01:03:12', '1');
INSERT INTO `sm_photo_praise` VALUES ('3', '3', '1', '2019-05-18 01:03:12', '1');
INSERT INTO `sm_photo_praise` VALUES ('4', '2', '2', '2019-05-18 01:03:12', '1');
INSERT INTO `sm_photo_praise` VALUES ('5', '3', '2', '2019-05-18 01:03:12', '1');

-- ----------------------------
-- Table structure for sm_photo_project
-- ----------------------------
DROP TABLE IF EXISTS `sm_photo_project`;
CREATE TABLE `sm_photo_project` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `createrId` int(100) NOT NULL COMMENT '用户id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目标题',
  `cover` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目封面',
  `createFields` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创作领域',
  `tags` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目标签',
  `imagesPath` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
  `isDelete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除（0 存在 1 删除）',
  `praiseCount` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `viewCount` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数',
  `commentCount` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
  `status` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '状态（0 上架  1 下架）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_user_email` (`createFields`),
  KEY `idx_sys_user_mobile` (`tags`),
  KEY `fk_project_createrid` (`createrId`),
  CONSTRAINT `fk_project_createrid` FOREIGN KEY (`createrId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sm_photo_project
-- ----------------------------
INSERT INTO `sm_photo_project` VALUES ('1', '1', '产品1号', 'http://127.0.0.1/upload/admin/info/20/120.jpg', 'faf', 'f', 'http://127.0.0.1/upload/admin/info/20/120.jpg', '2019-05-21 01:01:56', '2019-05-22 01:01:58', '1', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('2', '1', '产品2号', '/upload/temp/QQ截图2019052501082921.png', 'faf', 'f', '/upload/temp/QQ截图201905250108297.png,/upload/temp/QQ截图201905250108299.png,/upload/temp/QQ截图2019052501082913.png', '2019-05-21 01:01:56', '2019-05-26 15:39:07', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('3', '1', '产品3号', 'http://127.0.0.1/upload/admin/info/20/120.jpg', 'faf', 'f', 'http://127.0.0.1/upload/admin/info/20/120.jpg', '2019-05-21 01:01:56', '2019-05-22 01:01:58', '0', '0', '0', '0', '1');
INSERT INTO `sm_photo_project` VALUES ('4', '1', '产品4号', 'http://127.0.0.1/upload/admin/info/20/120.jpg', 'faf', 'f', 'http://127.0.0.1/upload/admin/info/20/120.jpg', '2019-05-21 01:01:56', '2019-05-22 01:01:58', '0', '0', '0', '0', '1');
INSERT INTO `sm_photo_project` VALUES ('5', '1', '产品5号', 'http://127.0.0.1/upload/admin/info/20/120.jpg', 'faf', 'f', 'http://127.0.0.1/upload/admin/info/20/120.jpg', '2019-05-21 01:01:56', '2019-05-22 01:01:58', '0', '0', '0', '0', '1');
INSERT INTO `sm_photo_project` VALUES ('6', '1', '产品6号', 'http://127.0.0.1/upload/admin/info/20/120.jpg', 'faf', 'f', 'http://127.0.0.1/upload/admin/info/20/120.jpg,/upload/temp/suoping8.jpg', '2019-05-21 01:01:56', '2019-05-27 13:16:29', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('7', '-1', '和谐美满', '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b9.jpg', '2', null, '/upload/temp/u=466875380,1333091221&fm=27&gp=012.jpg,/upload/temp/suoping7.jpg,/upload/temp/u=3772937452,3844864413&fm=27&gp=05.jpg', '2019-05-27 13:15:10', '2019-05-27 13:15:10', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('8', '-1', '产品7号', '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b10.jpg', '2', null, '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b11.jpg,/upload/temp/suoping9.jpg,/upload/temp/suoping10.jpg,/upload/temp/u=3772937452,3844864413&fm=27&gp=06.jpg,/upload/temp/u=466875380,1333091221&fm=27&gp=013.jpg,/upload/temp/suoping11.jpg,/upload/temp/suoping12.jpg', '2019-05-27 13:18:59', '2019-05-27 13:18:59', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('9', '-1', 'gaga', '/upload/temp/suoping14.jpg', '3', null, '/upload/temp/suoping15.jpg', '2019-05-27 13:20:07', '2019-05-27 13:20:07', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('10', '-1', 'gaga', '/upload/temp/suoping16.jpg', '2', null, '/upload/temp/suoping17.jpg', '2019-05-27 13:20:17', '2019-05-27 13:20:17', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('11', '-1', 'gag', '/upload/temp/suoping18.jpg', '2', null, '/upload/temp/u=466875380,1333091221&fm=27&gp=014.jpg', '2019-05-27 13:20:26', '2019-05-27 13:20:26', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('12', '-1', 'gaga', '/upload/temp/suoping19.jpg', '2', null, '/upload/temp/u=466875380,1333091221&fm=27&gp=015.jpg', '2019-05-27 13:20:37', '2019-05-27 13:20:37', '0', '0', '0', '0', '0');
INSERT INTO `sm_photo_project` VALUES ('13', '-1', '发发', '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b15.jpg', '2', null, '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b13.jpg,/upload/temp/suoping20.jpg,/upload/temp/c4485c411e9e6c4b07b6bac9354d417b14.jpg', '2019-05-28 12:04:22', '2019-05-28 12:05:25', '0', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for sm_sys_meassage
-- ----------------------------
DROP TABLE IF EXISTS `sm_sys_meassage`;
CREATE TABLE `sm_sys_meassage` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `senderId` int(100) NOT NULL COMMENT '发送者id',
  `recipientid` int(100) NOT NULL COMMENT '接收者id',
  `content` varchar(255) NOT NULL COMMENT '消息内容',
  `createDate` datetime NOT NULL COMMENT '消息发送时间',
  PRIMARY KEY (`id`),
  KEY `fk_massage_senderid` (`senderId`),
  KEY `fk_massage_recipientid` (`recipientid`),
  CONSTRAINT `fk_massage_recipientid` FOREIGN KEY (`recipientid`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_massage_senderid` FOREIGN KEY (`senderId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sm_sys_meassage
-- ----------------------------
INSERT INTO `sm_sys_meassage` VALUES ('1', '2', '3', '给你推荐个应用', '2019-05-26 20:08:17');
INSERT INTO `sm_sys_meassage` VALUES ('2', '3', '2', '不要', '2019-05-27 20:08:17');
INSERT INTO `sm_sys_meassage` VALUES ('3', '3', '2', '不想用', '2019-05-26 20:08:17');
INSERT INTO `sm_sys_meassage` VALUES ('4', '3', '2', '拒绝', '2019-05-26 20:08:17');
INSERT INTO `sm_sys_meassage` VALUES ('5', '2', '3', '额。。', '2019-05-26 20:08:17');

-- ----------------------------
-- Table structure for sm_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sm_sys_user`;
CREATE TABLE `sm_sys_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `sex` int(1) DEFAULT NULL COMMENT '用户性别  0 女  1 男',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像路径',
  `sign` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个性签名',
  `userType` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '用户类型（0流量用户 1中坚用户  2专栏用户）',
  `pwdQuestion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `pwdQuestionAnswer` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题答案',
  `freezeDate` datetime DEFAULT NULL COMMENT '冻结时间',
  `freezeCause` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '冻结原因',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用 2冻结）',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `website` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个人网站',
  `career` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职业',
  `lastLoginDate` datetime DEFAULT NULL COMMENT '上次登录时间',
  `salt` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `updateDate` datetime NOT NULL COMMENT '上次更新时间',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除 （0 存在 1 删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_user_lc` (`account`),
  KEY `idx_sys_user_email` (`email`),
  KEY `idx_sys_user_mobile` (`mobile`),
  KEY `idx_sys_user_mt` (`userType`),
  KEY `idx_sys_user_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sm_sys_user
-- ----------------------------
INSERT INTO `sm_sys_user` VALUES ('-1', 'admin', '123456', '看否官方', '21424@qq.com', null, '1', '/upload/temp/u=3772937452,3844864413&fm=27&gp=07.jpg', null, '0', null, null, '2019-05-25 17:08:58', null, '0', '2019-05-21 08:02:21', '2r2r2', null, '码农', '2019-05-25 17:34:13', 'viGBlZVNUZPw0mMgoz6cJBnZzQWpC-Sr', '2019-05-28 12:01:53', '0');
INSERT INTO `sm_sys_user` VALUES ('0', 'tourist', 'Tourist', '游客', '1604@qq.com', null, null, '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b.jpg', null, '0', null, null, null, null, '0', '2019-05-25 18:27:09', 'MSA', null, '无业游民', null, 'sfsfs', '2019-05-26 15:54:35', '0');
INSERT INTO `sm_sys_user` VALUES ('1', 'afdasfa', '8783d85f13276ac1baf2bbf3d6edf5c6950fe3b5ca213648123b4b7f79977a51', 'smile', '1f1f1@qq.com', null, null, '/upload/temp/u=466875380,1333091221&fm=27&gp=01.jpg', null, '0', null, null, '2019-05-25 17:09:01', null, '0', '2019-05-21 08:02:21', '2r2r2', null, 'UI设计', '2019-05-23 17:34:28', 'viGBlZVNUZPw0mMgoz6cJBnZzQWpC-Sr', '2019-05-26 16:03:29', '0');
INSERT INTO `sm_sys_user` VALUES ('2', '测试儿', '8783d85f13276ac1baf2bbf3d6edf5c6950fe3b5ca213648123b4b7f79977a51', '相逢', '16041@qq.ocm', null, null, null, null, '0', null, null, '2019-05-25 17:09:04', null, '0', '2019-05-21 08:02:21', '2r2r2', null, '产品经理', '2019-05-23 17:34:33', 'viGBlZVNUZPw0mMgoz6cJBnZzQWpC-Sr', '2019-05-26 15:55:22', '0');
INSERT INTO `sm_sys_user` VALUES ('3', '测试3', '8783d85f13276ac1baf2bbf3d6edf5c6950fe3b5ca213648123b4b7f79977a51', '旧部', '1604@qq.www', null, null, null, null, '0', null, null, '2019-05-25 17:09:07', null, '0', '2019-05-21 08:02:21', '2r2r2', null, '天师', '2019-05-15 17:34:36', 'viGBlZVNUZPw0mMgoz6cJBnZzQWpC-Sr', '2019-05-26 15:55:52', '0');
INSERT INTO `sm_sys_user` VALUES ('4', '123123123123', '227f318aaa5d98f8a7735b352d418845f9eed7d9cb1aec7feb777582adf0f9d0', '123123123123', 'fqf@qq.com', null, '1', '/upload/temp/u=466875380,1333091221&fm=27&gp=0.jpg', null, '0', null, null, null, null, '0', '2019-05-24 17:06:16', '123', null, null, '2019-05-14 17:34:40', 'L6Dr0RN5qwhypWKohSryED50R-IN1BX4', '2019-05-26 15:47:44', '0');
INSERT INTO `sm_sys_user` VALUES ('5', '1234', 'fa ', '12312312344', '160474870@qq', null, null, '/upload/temp/u=3772937452,3844864413&fm=27&gp=0.jpg', null, '0', null, null, null, null, '0', '2019-05-25 02:02:11', 'fa', null, null, '2019-05-14 17:34:40', 'V2tfo9RepSOTMn4uSt3vRV6qDduUFkAq', '2019-05-26 15:49:03', '0');
INSERT INTO `sm_sys_user` VALUES ('6', 'test1', 'ec583896f660237ca0c0b38b4cd2c1d24bf758b64a9cbb6bf1c144f28a81c03b', 'test1', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:50:10', null, null, null, '2019-05-14 17:34:40', 'TaYnAf1ruchZmx-7ON8uLGFn4fMziHGP', '2019-05-25 02:50:10', '0');
INSERT INTO `sm_sys_user` VALUES ('7', 'test2', 'c63f62944fa1c44a7272c1282a281acbf10642d1a0798775e9ab655be79e5831', 'test2', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:50:18', null, null, null, '2019-05-14 17:34:40', 'WI7wBb8sf2LBtM-KykoTck-ORir191UP', '2019-05-25 02:50:18', '0');
INSERT INTO `sm_sys_user` VALUES ('8', 'test3', '98a264edacba04354dca2766e311198d5c19ea935c0786d0a0bd905b66906863', 'test3', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:50:27', null, null, null, '2019-05-14 17:34:40', 'W14rguvouEwwovzQwphGNeph8yUJY5WU', '2019-05-25 02:50:27', '0');
INSERT INTO `sm_sys_user` VALUES ('9', 'test4', '6bdeee0801d267535dc78e4718e8fb60799d06eb7d3f92291578ae9dbd1e4cfe', 'test4', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:50:34', null, null, null, '2019-05-14 17:34:40', 'LTaD7TXlaZNs1QvgriSBzA65s1CIC1Rp', '2019-05-25 02:50:34', '0');
INSERT INTO `sm_sys_user` VALUES ('10', 'tes5', '1a8a35b23fa51a82a496bba3ac18b7eda122c847609054a69c83aac4437f5848', 'tes5', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:50:43', null, null, null, '2019-05-14 17:34:40', 'LTO6oNXepPX3Mbj_oT4NHJT0Rbt3aHBJ', '2019-05-25 02:50:43', '0');
INSERT INTO `sm_sys_user` VALUES ('11', '123123', '1e71bf2d7c14bae4035f4a960c993450a5702d77da69286e3de86d776bde57d9', '123123123123', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-25 02:52:54', null, null, null, '2019-05-14 17:34:40', '46ZGtGQlkqhqMHIF3D3PmZvIWm0FoTe9', '2019-05-25 02:52:54', '0');
INSERT INTO `sm_sys_user` VALUES ('13', 'lzw1234656', '49ca4203d6a0eeb58827290e4d086f1c9e1774dfd1f9b8ba31eefa52783513fc', 'viewMap', null, null, null, null, null, '0', null, null, null, null, '2', '2019-05-27 12:22:10', null, null, null, null, '7yUr3qICP9UNY2MPu18mx7JMNUK80MLv', '2019-05-27 12:22:10', '0');
INSERT INTO `sm_sys_user` VALUES ('14', 'haha', '31e24f87e8ce2a6616f4272e15bd4189545a3d247c38b91551da1489b988dccc', '路人', '1604@qq.com', null, '0', '/upload/temp/u=466875380,1333091221&fm=27&gp=06.jpg', null, '0', null, null, null, null, '0', '2019-05-27 12:58:28', '杭州', null, '前端程序员', null, 'hIgmV5eS4K0BmH2ZQcBm9jSpuKCBnMs1', '2019-05-27 13:04:29', '0');
INSERT INTO `sm_sys_user` VALUES ('15', 'lzw123456', 'f1381c09e3270dae3824b00a6f90c00320a53644bdee6f4e76fb132848860ee8', 'admin', null, null, null, null, null, '0', null, null, null, null, '0', '2019-05-28 11:56:45', null, null, null, null, 't1fT0GRNP9vv2HIUSk1W9XgRxN6qw8Xe', '2019-05-28 11:56:45', '0');
INSERT INTO `sm_sys_user` VALUES ('16', 'lzw147', 'ff8b3ec8248c369e486e113103082087c9f256b62427daf09a35e1775ced829e', 'aba', 'fafa', null, null, '/upload/temp/suoping23.jpg', null, '0', null, null, null, null, '0', '2019-05-28 15:11:19', 'fafa', null, null, null, 'KzGvkDaGAFFgFnY7nB-FBuCuHleiIZ24', '2019-05-28 15:11:43', '0');
INSERT INTO `sm_sys_user` VALUES ('17', 'lzw', '2626f7ce19ba25352866c5c12917ca3a8889b5fd589bdcda5b46821a60c8a84a', 'fafa', null, null, null, null, null, '0', null, null, null, null, '0', '2019-06-01 17:05:20', null, null, null, null, 'kIVNEdqG1sQ7GlBqHCW3DecNpUVVHhtq', '2019-06-01 17:05:20', '0');
INSERT INTO `sm_sys_user` VALUES ('18', 'lzw1', '948d76b06a69156706378b1db99361f5ac2fe8cb57c433c43db2d67e32b88bb9', 'lzw', null, null, null, null, null, '0', '巧克力', '爱吃', null, null, '0', '2019-06-01 17:33:42', null, null, null, '2019-06-01 18:19:25', 'JzDdp9TFUkASXyWoOMxAOnAePUqvFjVt', '2019-06-01 17:33:42', '0');

-- ----------------------------
-- Table structure for sm_sys_user_attention
-- ----------------------------
DROP TABLE IF EXISTS `sm_sys_user_attention`;
CREATE TABLE `sm_sys_user_attention` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '关注信息id',
  `userId` int(100) NOT NULL COMMENT '用户id',
  `attentionId` int(100) DEFAULT NULL COMMENT '关注用户id',
  `collectionId` int(100) DEFAULT NULL COMMENT '关注作品集id',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_user_lc` (`userId`),
  KEY `fk_attention_attentionid` (`attentionId`),
  KEY `fk_attention_collectionid` (`collectionId`),
  CONSTRAINT `fk_attention_attentionid` FOREIGN KEY (`attentionId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_attention_collectionid` FOREIGN KEY (`collectionId`) REFERENCES `sm_photo_collection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_attention_userid` FOREIGN KEY (`userId`) REFERENCES `sm_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of sm_sys_user_attention
-- ----------------------------
INSERT INTO `sm_sys_user_attention` VALUES ('1', '1', '2', null, '2019-05-21 16:52:53');
INSERT INTO `sm_sys_user_attention` VALUES ('2', '2', '2', null, '2019-05-21 16:52:53');
INSERT INTO `sm_sys_user_attention` VALUES ('3', '3', null, '2', '2019-05-21 16:52:53');
INSERT INTO `sm_sys_user_attention` VALUES ('4', '4', null, '1', '2019-05-21 16:52:53');
INSERT INTO `sm_sys_user_attention` VALUES ('5', '5', null, '2', '2019-05-21 16:52:53');

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '昵称',
  `roleId` int(11) DEFAULT NULL COMMENT '权限组Id',
  `username` varchar(150) NOT NULL COMMENT '账号',
  `password` varchar(150) NOT NULL COMMENT '密码',
  `ip` varchar(50) DEFAULT NULL,
  `salt` varchar(150) NOT NULL COMMENT '盐',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-禁用',
  `lastLoginAt` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  `updateAt` datetime NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`),
  KEY `fk_account_roleId` (`roleId`),
  CONSTRAINT `fk_account_roleId` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', '刘志伟', '0', 'admin', '744dbd2d987a18fb220b55edb92b6ac18cc76d15d62f76b026afbd9b6708b7da', '127.0.0.1', 'WnvJIL1AYZ_RJX1B8UU_duvOVIXK8YPj', '1', '2019-06-01 20:17:52', '2017-12-21 10:01:18', '2019-05-24 09:21:54');
INSERT INTO `sys_account` VALUES ('4', '陈建敏', '14', 'cjm123456', '200d5c1a5cd044c6b28dc161aa945f1d3145961677845db50fa9fb59eb1d3e46', null, 'OJmvzRlqaqQMEEjQ0Exv9Hnbo5c9PX0_', '1', null, '2019-05-21 06:40:40', '2019-05-28 15:18:12');
INSERT INTO `sys_account` VALUES ('5', '笑笑', '11', 'hhhhhhhh', '13dbb376e7a7e43a927530a7300a7fecdaf27b2d3760120e3d53bd919ef9e4d4', null, 'O0TEImo06XFr5GWvw2LO7Bi9M660lnZX', '1', null, '2019-05-26 09:02:09', '2019-05-26 09:02:09');
INSERT INTO `sys_account` VALUES ('6', '雨天', '8', 'rain', 'fb860ff5e870e1175187b245a9dfae7f30bdad7d4003535d0c8527fb77dfa7f6', null, 'm8RVrNGC-M8L8CibSXdx86vq86xTbZ8H', '1', null, '2019-05-26 09:02:54', '2019-05-26 09:02:54');
INSERT INTO `sys_account` VALUES ('7', '梦想', '7', 'dream', '2f53ae43512359e4d55110fef77ee0aeccf99fc1210fc61eb8c73f92e79bda8e', null, 'lNy-8zF6lb5f-QMCUBB28NqsMgasJJ61', '1', null, '2019-05-26 09:03:38', '2019-05-26 09:03:38');
INSERT INTO `sys_account` VALUES ('8', 'gaga', '8', 'gaga', 'ce836f41a7b966670eb6400f6b7ef944f1c1cc20b356b40039b095c22027ee9b', null, 'AHaWTkjIXPP1LcRXbj7hCtsgfLx9ymP4', '1', null, '2019-05-28 12:40:08', '2019-05-28 12:40:08');
INSERT INTO `sys_account` VALUES ('9', 'abab', '13', 'lzw123', '343b2b5b304e2b433c4b3fbfb988ae833baef3e53e07af29855ad7faef911692', null, 'cLfrKZVd_RTaos_C_AVThtl5vBYYLVHt', '1', '2019-05-28 12:44:47', '2019-05-28 12:41:11', '2019-05-28 12:44:28');
INSERT INTO `sys_account` VALUES ('10', 'liu', '13', 'aaa123', '780680d41858c3fa3167dd7ef8e5c96485d948fef66997b2754f99979009906c', null, 'Kgj8JaDYhYsX38BODkh9jMcyLVzlJ5iT', '1', '2019-06-01 19:49:36', '2019-05-28 15:16:43', '2019-05-28 15:16:43');

-- ----------------------------
-- Table structure for sys_adv
-- ----------------------------
DROP TABLE IF EXISTS `sys_adv`;
CREATE TABLE `sys_adv` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告标题',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '广告外链',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告封面',
  `adminId` int(100) NOT NULL COMMENT '管理员Id',
  `clickCount` int(10) NOT NULL DEFAULT '0' COMMENT '点击量',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0 草稿 1展示  2已到期',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `isDelete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 存在  1 删除',
  `advName` varchar(255) DEFAULT NULL COMMENT '广告商',
  `fieldId` int(100) DEFAULT NULL COMMENT '展示领域id',
  PRIMARY KEY (`id`),
  KEY `fk_adv_adminid` (`adminId`),
  KEY `fk_adv_fieldId` (`fieldId`),
  CONSTRAINT `fk_adv_adminid` FOREIGN KEY (`adminId`) REFERENCES `sys_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_adv_fieldId` FOREIGN KEY (`fieldId`) REFERENCES `sm_photo_field` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_adv
-- ----------------------------
INSERT INTO `sys_adv` VALUES ('1', '爱读', 'www.baidu.com', 'http://127.0.0.1/upload/admin/info/20/120.jpg', '1', '0', '1', '2019-05-24 18:51:03', '2019-05-24 12:19:46', '0', '百度', '1');
INSERT INTO `sys_adv` VALUES ('16', '谷歌生活', 'www.google.com', '/upload/temp/QQ截图2019052501082919.png', '1', '0', '1', '2019-05-26 10:39:09', '2019-05-26 10:48:46', '0', '谷歌', '1');
INSERT INTO `sys_adv` VALUES ('17', '复联大集合', 'www.baidu.com', '/upload/temp/header1.png', '1', '0', '1', '2019-05-26 11:06:33', '2019-05-26 11:06:33', '0', '漫威', '5');
INSERT INTO `sys_adv` VALUES ('18', '哇哈哈哈', 'https://element.eleme.cn/#/zh-CN/component/select', '/upload/temp/QQ截图2019052501082920.png', '1', '0', '0', '2019-05-26 11:07:05', '2019-05-26 11:07:05', '0', '哇哈哈', '6');
INSERT INTO `sys_adv` VALUES ('19', 'notion笔记', 'https://www.notion.so/anshuye/BUG-00120e1c657540b8bb123334c35ddbc8', '/upload/temp/header2.png', '1', '0', '0', '2019-05-26 11:07:35', '2019-05-26 11:07:35', '0', 'notion', '3');
INSERT INTO `sys_adv` VALUES ('20', '百度', 'www.baidu.com', '/upload/temp/suoping22.jpg', '1', '0', '1', '2019-05-28 12:36:04', '2019-05-28 12:36:04', '0', '百度', '25');

-- ----------------------------
-- Table structure for sys_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资讯标题',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资讯封面',
  `adminId` int(11) NOT NULL COMMENT '管理员Id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '正文',
  `readCount` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态  0 草稿 1 进行中 2 过期',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `isDelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 存在 1 删除',
  `fieldId` int(100) NOT NULL COMMENT '展示领域Id',
  `imagesPath` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容图片地址',
  PRIMARY KEY (`id`),
  KEY `fk_info_adminid` (`adminId`),
  KEY `fk_info_fieldId` (`fieldId`),
  CONSTRAINT `fk_info_adminid` FOREIGN KEY (`adminId`) REFERENCES `sys_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_info_fieldId` FOREIGN KEY (`fieldId`) REFERENCES `sm_photo_field` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_info
-- ----------------------------
INSERT INTO `sys_info` VALUES ('20', '23r2rt23t23t', '/upload/admin/info/20/120.jpg', '1', '<p>2gggggggggggggggggg</p>', '0', '1', '2019-05-21 11:46:01', '2019-05-26 09:32:36', '0', '1', null);
INSERT INTO `sys_info` VALUES ('21', '图片美化', '/upload/temp/1.jpg', '1', '<p>j加油加油j加油加油j加油加油j加油加油j加油加油j加油加油</p>', '0', '1', '2019-05-26 09:50:55', '2019-05-26 09:53:00', '0', '1', '/upload/temp/11.jpg');
INSERT INTO `sys_info` VALUES ('22', '啊嘎嘎', '/upload/temp/12.jpg', '1', '<p>发发 发发大师傅阿萨的官方阿嘎噶给</p>', '0', '1', '2019-05-26 09:53:49', '2019-05-26 09:53:49', '0', '3', null);
INSERT INTO `sys_info` VALUES ('23', '撒法', '/upload/temp/13.jpg', '1', '<p>飞洒发撒法是发 发   飞洒发撒法是发 发   飞洒发撒法是发 发   </p>', '0', '0', '2019-05-26 10:02:48', '2019-05-26 10:02:48', '0', '1', null);
INSERT INTO `sys_info` VALUES ('24', 'motion生活', '/upload/temp/userLogo.jpg', '1', '<p>z这是一场盛大的活动，将带来一场丰富的motion盛宴</p>', '0', '0', '2019-05-26 10:36:22', '2019-05-26 10:36:22', '0', '6', '/upload/temp/14.jpg,/upload/temp/header.png,/upload/temp/QQ截图2019052501082918.png');
INSERT INTO `sys_info` VALUES ('25', 'ps大赛', '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b16.jpg', '1', '<blockquote><strong>a啊嘎嘎啊<u> 发阿发啊身份是是 是agaga ga ga  </u></strong></blockquote><blockquote><strong><u>嘎噶 是啊是噶</u></strong></blockquote><h6><strong><u>昂该啊噶</u></strong></h6><blockquote><strong><s><u>发发 发啊</u></s></strong></blockquote>', '0', '0', '2019-05-28 12:34:15', '2019-05-28 12:34:27', '0', '2', '/upload/temp/c4485c411e9e6c4b07b6bac9354d417b17.jpg,/upload/temp/suoping21.jpg,/upload/temp/c4485c411e9e6c4b07b6bac9354d417b18.jpg');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `accountId` int(11) DEFAULT NULL,
  `loginAt` datetime NOT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  KEY `accountId_index` (`accountId`) USING BTREE,
  CONSTRAINT `fk_loginLog_accountId` FOREIGN KEY (`accountId`) REFERENCES `sys_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 16:05:57', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 16:05:58', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 16:10:33', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:37:57', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:38:47', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:39:03', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:41:29', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:43:09', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:43:38', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:45:57', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:47:19', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:48:30', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:49:31', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:50:26', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:52:34', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:57:57', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 17:59:14', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 18:00:38', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 18:19:08', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-06-01 18:19:25', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 18:37:04', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 18:37:35', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 18:39:50', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 19:18:22', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 19:19:56', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES (null, '2019-05-30 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-31 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-31 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-31 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-30 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-30 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-30 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES (null, '2019-05-30 17:36:07', '192.168.0.115', '18');
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 19:25:14', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 19:27:18', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('10', '2019-06-01 19:42:03', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 19:49:12', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('10', '2019-06-01 19:49:36', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('10', '2019-06-01 20:03:28', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 20:03:56', '0:0:0:0:0:0:0:1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 20:09:00', '127.0.0.1', null);
INSERT INTO `sys_login_log` VALUES ('1', '2019-06-01 20:17:52', '0:0:0:0:0:0:0:1', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isParent` tinyint(1) unsigned DEFAULT NULL COMMENT '0',
  `parentId` int(11) DEFAULT NULL COMMENT '父节点',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `action` varchar(250) DEFAULT NULL,
  `level` int(2) DEFAULT NULL COMMENT '层级',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) unsigned DEFAULT '1' COMMENT '状态 1 -正常 0-禁用',
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '1', '0', '用户管理', '/admin/user', '1', '1', '1', '2017-12-20 18:14:59');
INSERT INTO `sys_menu` VALUES ('7', '1', '0', '信息管理', '/admin/info', '1', '2', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('8', '0', '7', '资讯列表', '/admin/info/infolist', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('10', '0', '7', '广告列表', '/admin/info/adv', '2', '3', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('11', '1', '0', '意见反馈', '/admin/feeback', '1', '3', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('12', '0', '11', '意见列表', '/admin/feeback', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('13', '1', '0', '管理员管理', '/admin/auth', '1', '4', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('14', '0', '13', '管理员列表', '/admin/auth/account', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('15', '0', '13', '权限管理', '/admin/auth/role', '2', '2', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('16', '0', '1', '用户列表', '/admin/user/user', '2', '1', '1', '2019-05-21 11:37:39');
INSERT INTO `sys_menu` VALUES ('17', '0', '1', '项目列表', '/admin/user/project', '2', '7', '1', '2017-12-20 18:14:59');
INSERT INTO `sys_menu` VALUES ('18', '0', '1', '作品集列表', '/admin/user/collection', '2', '8', '1', '2017-12-20 18:14:59');
INSERT INTO `sys_menu` VALUES ('19', '0', '1', '评论监管', '/admin/user/comment', '2', '9', '1', '2017-12-20 18:14:59');
INSERT INTO `sys_menu` VALUES ('20', '0', '1', '创作领域列表', '/admin/user/createfield', '2', '10', '1', '2017-12-20 18:14:59');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '超级管理员', '2019-05-20 03:39:18', '2019-05-25 12:01:41');
INSERT INTO `sys_role` VALUES ('6', 'HR', '2019-05-25 06:25:23', '2019-05-25 12:03:05');
INSERT INTO `sys_role` VALUES ('7', '运维管理员', '2019-05-26 08:33:07', '2019-05-26 08:33:07');
INSERT INTO `sys_role` VALUES ('8', '产品部', '2019-05-26 08:33:38', '2019-05-26 08:33:38');
INSERT INTO `sys_role` VALUES ('9', '客户演示', '2019-05-26 08:33:59', '2019-05-26 08:42:06');
INSERT INTO `sys_role` VALUES ('11', '编辑演示', '2019-05-26 08:48:52', '2019-05-26 08:48:52');
INSERT INTO `sys_role` VALUES ('12', '无法', '2019-05-26 10:04:02', '2019-05-26 10:04:02');
INSERT INTO `sys_role` VALUES ('13', '客户', '2019-05-28 12:42:20', '2019-06-01 19:49:27');
INSERT INTO `sys_role` VALUES ('14', '讲师', '2019-05-28 15:17:56', '2019-05-28 15:17:56');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色Id',
  `menuId` int(11) NOT NULL COMMENT '菜单Id',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_sys_role_menu_sys_role_1` (`roleId`) USING BTREE,
  KEY `fk_sys_role_menu_sys_menu_1` (`menuId`) USING BTREE,
  CONSTRAINT `fk__role_menu_roleid` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_menu_menuid` FOREIGN KEY (`menuId`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('78', '0', '1', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('79', '0', '16', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('80', '0', '17', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('81', '0', '18', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('82', '0', '19', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('83', '0', '20', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('84', '0', '7', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('85', '0', '8', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('86', '0', '10', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('87', '0', '11', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('88', '0', '12', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('89', '0', '13', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('90', '0', '14', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('91', '0', '15', '2019-05-25 12:01:41');
INSERT INTO `sys_role_menu` VALUES ('92', '6', '11', '2019-05-25 12:03:05');
INSERT INTO `sys_role_menu` VALUES ('93', '6', '12', '2019-05-25 12:03:05');
INSERT INTO `sys_role_menu` VALUES ('94', '6', '14', '2019-05-25 12:03:05');
INSERT INTO `sys_role_menu` VALUES ('95', '7', '1', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('96', '7', '16', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('97', '7', '17', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('98', '7', '18', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('99', '7', '19', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('100', '7', '20', '2019-05-26 08:33:07');
INSERT INTO `sys_role_menu` VALUES ('101', '8', '7', '2019-05-26 08:33:38');
INSERT INTO `sys_role_menu` VALUES ('102', '8', '8', '2019-05-26 08:33:38');
INSERT INTO `sys_role_menu` VALUES ('103', '8', '10', '2019-05-26 08:33:38');
INSERT INTO `sys_role_menu` VALUES ('104', '8', '11', '2019-05-26 08:33:38');
INSERT INTO `sys_role_menu` VALUES ('105', '8', '12', '2019-05-26 08:33:38');
INSERT INTO `sys_role_menu` VALUES ('136', '9', '1', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('137', '9', '16', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('138', '9', '17', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('139', '9', '18', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('140', '9', '19', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('141', '9', '20', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('142', '9', '7', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('143', '9', '8', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('144', '9', '10', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('145', '9', '11', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('146', '9', '12', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('147', '9', '13', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('148', '9', '14', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('149', '9', '15', '2019-05-26 08:42:07');
INSERT INTO `sys_role_menu` VALUES ('150', '11', '1', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('151', '11', '16', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('152', '11', '17', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('153', '11', '18', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('154', '11', '19', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('155', '11', '20', '2019-05-26 08:48:52');
INSERT INTO `sys_role_menu` VALUES ('156', '12', '1', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('157', '12', '16', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('158', '12', '17', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('159', '12', '18', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('160', '12', '19', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('161', '12', '20', '2019-05-26 10:04:02');
INSERT INTO `sys_role_menu` VALUES ('168', '14', '1', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('169', '14', '16', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('170', '14', '17', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('171', '14', '18', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('172', '14', '19', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('173', '14', '20', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('174', '14', '7', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('175', '14', '8', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('176', '14', '10', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('177', '14', '11', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('178', '14', '12', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('179', '14', '15', '2019-05-28 15:17:56');
INSERT INTO `sys_role_menu` VALUES ('180', '13', '1', '2019-06-01 19:49:27');
INSERT INTO `sys_role_menu` VALUES ('181', '13', '16', '2019-06-01 19:49:27');
INSERT INTO `sys_role_menu` VALUES ('182', '13', '17', '2019-06-01 19:49:27');
INSERT INTO `sys_role_menu` VALUES ('183', '13', '18', '2019-06-01 19:49:27');
INSERT INTO `sys_role_menu` VALUES ('184', '13', '19', '2019-06-01 19:49:27');
INSERT INTO `sys_role_menu` VALUES ('185', '13', '20', '2019-06-01 19:49:27');

-- ----------------------------
-- Table structure for sys_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE `sys_session` (
  `id` varchar(64) NOT NULL,
  `accountId` int(11) DEFAULT '-2',
  `expireAt` bigint(20) NOT NULL,
  `careateAt` datetime NOT NULL,
  `userId` int(11) DEFAULT '-2',
  PRIMARY KEY (`id`),
  KEY `fk_session_accountId` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_session
-- ----------------------------
INSERT INTO `sys_session` VALUES ('01fe39126a5539af8938541b5c4adf2918e45021e6749d420b8d39033fe2dfe5', '1', '1560009952115', '2019-06-01 16:05:52', '-2');
INSERT INTO `sys_session` VALUES ('04a9d681ef8c27eb0c771b7e9f520d356ba94bbe423f1e2f4c12577bfb35f132', '1', '1559314759227', '2019-05-24 14:59:19', '0');
INSERT INTO `sys_session` VALUES ('0a63ce161c72b9302053b15ae9672eaf60b8f06bdaac4ef207bb82070473663d', '1', '1560025071928', '2019-06-01 20:17:52', '-2');
INSERT INTO `sys_session` VALUES ('1480162b9096e11a81e831b34e2508c4eadd0b0ef64a83890021028e0e3879f1', '1', '1559305794537', '2019-05-24 12:29:55', '0');
INSERT INTO `sys_session` VALUES ('15aeea9b5d0d73321081f71a9a69bd7a58316b524ea0b72d3e44eb2b799b9d7d', '1', '1559396130622', '2019-05-25 13:35:31', '0');
INSERT INTO `sys_session` VALUES ('1b3012ffe0e62423f2610723ba9553c8f13776df5bfad69db208c512175ef3a8', '1', '1559111829431', '2019-05-22 06:37:09', '0');
INSERT INTO `sys_session` VALUES ('1e329a3de702f8589d3d10a19945701edbfb1d13339057d150ea45347f39679b', '1', '1559489876994', '2019-05-26 15:37:57', '0');
INSERT INTO `sys_session` VALUES ('1fd21d9b7dbfca279efe7afe5a7d84afabdc7a41db700da32b9f79935dbda515', '-2', '1560016038978', '2019-06-01 17:47:19', '18');
INSERT INTO `sys_session` VALUES ('22a221fbb995b3e8eb71f75d8792b49f496790ebbf7d3a4786dbbc8f5dd9d51e', '1', '1558942716060', '2019-05-20 07:38:36', '0');
INSERT INTO `sys_session` VALUES ('29fae1d68c5c1218d06574adf53f2461fa21a3b3e309254a81ec68c2ff1da078', '1', '1558936305187', '2019-05-20 05:51:45', '0');
INSERT INTO `sys_session` VALUES ('2a42425527ae60c43c007a22093569a962686fb3307516a06bbea25b2ff7332e', '1', '1560009859033', '2019-06-01 16:04:19', '-2');
INSERT INTO `sys_session` VALUES ('2b6601439547a2f43f3667da46b33ad79aec59072d2b5c109043d38b9482d4bd', '1', '1560008543516', '2019-06-01 15:42:24', null);
INSERT INTO `sys_session` VALUES ('2c3442241286bc896a0e9e74cf14a2f2b72a1996a2b9bf467761647f2f269d37', '1', '1559203017971', '2019-05-23 07:56:58', '0');
INSERT INTO `sys_session` VALUES ('2c43b02b3e7f167b1f8d6eb9d979ccbdcdcc7c78f09bc51b7ed8e5cdc01acedc', '1', '1560024540239', '2019-06-01 20:09:00', '-2');
INSERT INTO `sys_session` VALUES ('2dd60182e500343a483a80680da6069a478cf536cb5a5a4ed7014ea027b3d595', '1', '1559661453142', '2019-05-28 15:17:33', '0');
INSERT INTO `sys_session` VALUES ('359e7dfd2a0d3819b458ad46455bd36c6c9d389c93f7daec47fb57658610965b', '1', '1559299378102', '2019-05-24 10:42:58', '0');
INSERT INTO `sys_session` VALUES ('39cda999bd2714d984f04141719a92cf63b628ae873fe7fb638235685cbf5f04', '1', '1560008664092', '2019-06-01 15:44:24', null);
INSERT INTO `sys_session` VALUES ('3a0316b47e3bf1aa1adab58a2667f0292c7a3d33b0933ecd4d1eb8a353d733c7', '1', '1559394596990', '2019-05-25 13:09:57', '0');
INSERT INTO `sys_session` VALUES ('3b0535353bd98c4a167f3ff4d982c9b84277d35be7a5157e9f1d77e933860d7c', '1', '1559469621154', '2019-05-26 10:00:21', '0');
INSERT INTO `sys_session` VALUES ('3c1af49e37cb4fee6450e82e72d6d9da484674a981748e560405ca082452486a', '1', '1560008544082', '2019-06-01 15:42:24', null);
INSERT INTO `sys_session` VALUES ('463c9c7138d7e39fdf5ac01fa52e21d3226ed55a96cb6dbf5c00b9faca5fcdf7', '1', '1559393095485', '2019-05-25 12:44:55', '0');
INSERT INTO `sys_session` VALUES ('479a0e719c5b8ea29a79c9804fa1377218f184c13de7bd25f28198d63e8f9a15', '-2', '1560015543385', '2019-06-01 17:39:03', '18');
INSERT INTO `sys_session` VALUES ('4a0ba270ea1d0914c3e708e724bd76b17f9cd50dddda9af7884402ffb618911f', '1', '1559288356760', '2019-05-24 07:39:17', '0');
INSERT INTO `sys_session` VALUES ('4b600dde148d1921587d2614b0afb1294227d20cbaa1f763e6ef2632ffd28c94', '1', '1559203300809', '2019-05-23 08:01:41', '0');
INSERT INTO `sys_session` VALUES ('4c813a4b33c454e05891e67535e656b1834e5ed25700c32d66993e900667dbcd', '1', '1560008664637', '2019-06-01 15:44:25', null);
INSERT INTO `sys_session` VALUES ('4ce440b7fc4abfabdefb5e67a7fb0f491ad4ceb0cc4bca46b4612d48c4838474', '1', '1558881987851', '2019-05-19 14:46:28', '0');
INSERT INTO `sys_session` VALUES ('4ebb495818d67b93455c3b1b5d9a9344271b6d21cb59268d7cc56c7a79890a51', '1', '1560009799288', '2019-06-01 16:03:28', '-2');
INSERT INTO `sys_session` VALUES ('4ed6a01c5a71014dd8c016e94768f807948c148280b2b26bcb8a75ecfcd79d3a', '-2', '1560016354337', '2019-06-01 17:52:34', '18');
INSERT INTO `sys_session` VALUES ('52b7a9b1a3605687692811265e98c87401ba9523d4d915751c7a3f183fb97422', '1', '1559397042454', '2019-05-25 13:50:42', '0');
INSERT INTO `sys_session` VALUES ('5a2ce22a61c87b61bc28baf64a3347277822b255fbf5cda3b07324e782ebc5d8', '1', '1559205336522', '2019-05-23 08:35:37', '0');
INSERT INTO `sys_session` VALUES ('5bcbed8045bf0bc38b0774615ff7b15fd7697c487f7677ff58dfa1114fb11662', '1', '1559037319820', '2019-05-21 09:55:20', '0');
INSERT INTO `sys_session` VALUES ('61cfe933e2053e34c6ee0277d919f48719ca382c845a81943946d87fc9992fe4', '-2', '1560015818054', '2019-06-01 17:43:38', '18');
INSERT INTO `sys_session` VALUES ('6355571a58a62463b811c37b82d63ee581f1d64acf858991dec512981f13b078', '1', '1559566322202', '2019-05-27 12:52:02', '0');
INSERT INTO `sys_session` VALUES ('65ccd28054e06747a7a2c2d71ab8974c8aa2f37adc26383a6edb903ad4171b07', '-2', '1560015788986', '2019-06-01 17:43:09', '18');
INSERT INTO `sys_session` VALUES ('66a5a3571e31f8b6045aad6bb147f785a0971b10cf18d11783c71cf3d60ccbc9', '-2', '1560016676754', '2019-06-01 17:57:57', '18');
INSERT INTO `sys_session` VALUES ('671c9861e1bde068c2752022b08ddca5eaf50022d9a88536f0a3ae2978822e65', '-2', '1560016225695', '2019-06-01 17:50:26', '18');
INSERT INTO `sys_session` VALUES ('6b8fa4dd1d3fbfe991acd8f2bf950e9d363809045f73e45dff185ee0d71c6e84', '1', '1559204708305', '2019-05-23 08:25:08', '0');
INSERT INTO `sys_session` VALUES ('6caf21f208c76ed95707b771a6d60a5cbc3856986fc209be2d1fd8b8c7fd5f42', '1', '1559220712477', '2019-05-23 12:51:52', '0');
INSERT INTO `sys_session` VALUES ('7052a2feb959fd84730feae46a636b5396e9f903193b48dcca4d849263a67496', '1', '1560009485237', '2019-06-01 15:58:05', null);
INSERT INTO `sys_session` VALUES ('70b3a4cea22770dedc234d2b98c027b15f5fe2f4926100dd6a87891c3d99959d', '-2', '1560017948117', '2019-06-01 18:19:08', '18');
INSERT INTO `sys_session` VALUES ('75c873b5c022bf1e85298c8e5b3d59d4009176b647e32719314e886a6d4daf52', '1', '1559394501226', '2019-05-25 13:08:21', '0');
INSERT INTO `sys_session` VALUES ('761937173b443c278550531132bd01be2569cee906550de5e724a4410f79843a', '-2', '1560017964687', '2019-06-01 18:19:25', '18');
INSERT INTO `sys_session` VALUES ('7791c07429311b3b92c8813736bec6735f4e1e3c39126802da30f3b38f6e4448', '-2', '1560015477014', '2019-06-01 17:37:57', '18');
INSERT INTO `sys_session` VALUES ('781baaa8f504b938a402bdd95c39769ac8921b04bba0eb800e93f6a751a977f1', '1', '1559050977857', '2019-05-21 13:42:58', '0');
INSERT INTO `sys_session` VALUES ('7903c649d7bd1e7023f33fadaf71ee745ecd508f3f870c90d3dcb25a9581624c', '-2', '1560016838090', '2019-06-01 18:00:38', '18');
INSERT INTO `sys_session` VALUES ('7b977d88dc2ad9151b4d070b1c8837b9ed58f47fd1af2ecf1c3f6f58c484aef7', '1', '1560008545087', '2019-06-01 15:42:25', null);
INSERT INTO `sys_session` VALUES ('7e4620e61189902bce5526625e6466902b62efb5db1f9929c212964525b87f26', '1', '1559225461843', '2019-05-23 14:11:02', '0');
INSERT INTO `sys_session` VALUES ('8157f37e0ea3cd4b0f4906a9da805775b1febbf175c4580a9d744bf6989a49d6', '1', '1559651369527', '2019-05-28 12:29:30', '0');
INSERT INTO `sys_session` VALUES ('828c79879ebf1c2bfa6cd69cdfcbf92683101d82bf9b1a077b46e047263feea1', '-2', '1560015527158', '2019-06-01 17:38:47', '18');
INSERT INTO `sys_session` VALUES ('84922228b9a713fd8606f0bc2af29e371fb9d7b5ac67f44ecb4ad671a4c504ac', '1', '1559051093143', '2019-05-21 13:44:53', '0');
INSERT INTO `sys_session` VALUES ('866a7b049c9b52b98b2ddd29ab00fd8ff98abdf2208cd839a1047a1af25d571e', '1', '1559225985883', '2019-05-23 14:19:46', '0');
INSERT INTO `sys_session` VALUES ('878886bcfdf877b3c68c21e945331e6dba5728aa72f633141d2be4dd41ebbc52', '1', '1559375405035', '2019-05-25 07:50:05', '0');
INSERT INTO `sys_session` VALUES ('88df2feb6b26993214d2f137007995e7a8abc267722174a8917edbeef9c6607b', '1', '1559300190449', '2019-05-24 10:56:30', '0');
INSERT INTO `sys_session` VALUES ('8bc454f662d8bda32a44741814235bf0c88a89934d38adea608b13d9cf6d478e', '-2', '1560015956871', '2019-06-01 17:45:57', '18');
INSERT INTO `sys_session` VALUES ('8eb80896f2660ed26b386c4d51c703301ef38c6df5012fe1426fbfc6e38d28e8', '1', '1560009682277', '2019-06-01 16:01:22', '-2');
INSERT INTO `sys_session` VALUES ('902661718454ad987130ba2b51cbd55f82cc73958e4c8472dee9e5fbb5c4e113', '1', '1559652305080', '2019-05-28 12:45:05', '0');
INSERT INTO `sys_session` VALUES ('9236245441c2a7471d6c93dc8406915f4c3cb67a0f0253396676cabda92d22e4', '1', '1559402283407', '2019-05-25 15:18:03', '0');
INSERT INTO `sys_session` VALUES ('9258fcce093b63a2846ecf28054d81b0c5562213c7903a50849b62e5e311b96d', '1', '1559468964821', '2019-05-26 09:49:25', '0');
INSERT INTO `sys_session` VALUES ('9690fc5008ee19d9e0a25c87c1242e6cb068ebcf09af730f88b213f6903c38e8', '1', '1560009630359', '2019-06-01 16:00:30', null);
INSERT INTO `sys_session` VALUES ('977e84775caad05e4ab786c0d31a3c8a2c3232282679bf08b14d2e33e97012c5', '1', '1559225291144', '2019-05-23 14:08:11', '0');
INSERT INTO `sys_session` VALUES ('984bc9f0e312a716c63e6b080c6194f078b205e74fa3e9643df19f723ccb291a', '1', '1560009762895', '2019-06-01 16:02:43', '-2');
INSERT INTO `sys_session` VALUES ('98b3da54ee9f06ef88dd053d43d6c39d8e01e209189c3803fe7bb8c947f9e5af', '1', '1559100118144', '2019-05-22 03:21:58', '0');
INSERT INTO `sys_session` VALUES ('9d1dd4952f9a013b352d734e0b8ce51a21b6b9d204c030137b31e87c6a31cd2a', '1', '1558952215020', '2019-05-20 10:16:55', '0');
INSERT INTO `sys_session` VALUES ('9e761cc6869c76fb0d30f3897ca51d79e23709152c3be75afe6ca30deea2cb79', '1', '1559205168296', '2019-05-23 08:32:48', '0');
INSERT INTO `sys_session` VALUES ('a071252710da037b95f26e044caf9b028b0079484e92ae96a98b403d9b497d9e', '1', '1559040972365', '2019-05-21 10:56:12', '0');
INSERT INTO `sys_session` VALUES ('a1fff1228500a5ca2a624b8672265ad130d78a8bcfd1312e3494a15e86c1bd94', '1', '1559202531175', '2019-05-23 07:48:51', '0');
INSERT INTO `sys_session` VALUES ('a92cac9ce7e0847985da336a5176bfe690a935024827dbab1dbdf50984013017', '1', '1559394618073', '2019-05-25 13:10:18', '0');
INSERT INTO `sys_session` VALUES ('ac584691ccbd544bfbd2212c51ccc235ce025a4ba123683ce9ed626dff1a1839', '1', '1560019055385', '2019-06-01 18:37:35', '-2');
INSERT INTO `sys_session` VALUES ('adf88930c6e07d27ca48ee57b7adec3f565e838de12dfde288f738fed740d533', '1', '1559472504551', '2019-05-26 10:48:25', '0');
INSERT INTO `sys_session` VALUES ('b457b229f4080aa928efb92778fb760b0daad4fcf52ac591a4c319ad479ea2f2', '1', '1558952112578', '2019-05-20 10:15:13', '0');
INSERT INTO `sys_session` VALUES ('b6231e58f95635db672d55fcb6a818bdd2b55961103945051c1097a4da3aae4e', '1', '1560008542718', '2019-06-01 15:42:23', null);
INSERT INTO `sys_session` VALUES ('b9a1c84c5743c43f21755f666f84b9b8838587ff339454b4163c7a2bb250f962', '1', '1560010228682', '2019-06-01 16:10:29', '-2');
INSERT INTO `sys_session` VALUES ('ba6c74a2e0a4d8a6c35702f7641d69ebf2bdc554605e96c1901ee116a2fef9ae', '1', '1559205574631', '2019-05-23 08:39:35', '0');
INSERT INTO `sys_session` VALUES ('c24fc65ba9b233740516f8a2b967597f83da9604d9bc38e3a752b18261cdf774', '1', '1559564168749', '2019-05-27 12:16:09', '0');
INSERT INTO `sys_session` VALUES ('c4610e2793caac0d3ee8245517b430ecc422428fb23f670d5a82cca6e574a6a0', '1', '1559464851819', '2019-05-26 08:40:52', '0');
INSERT INTO `sys_session` VALUES ('c4676ff92f89fe6684453066a93603b48eccf99ccdaabe81f7a57e26e4369fed', '1', '1560019023900', '2019-06-01 18:37:04', '-2');
INSERT INTO `sys_session` VALUES ('c4f433b57445adc83f07182d660dce0718a4a543606afa42d678bdb9be511c83', '1', '1559051112105', '2019-05-21 13:45:12', '0');
INSERT INTO `sys_session` VALUES ('d2cd7344524ae92e0ecafc1d2b5be103fb6c0d1414908c893e569e43768feebd', '1', '1559400398721', '2019-05-25 14:46:39', '0');
INSERT INTO `sys_session` VALUES ('d4077b10920c2740cfed251fbaf6d0a556dfdedf6fbb101c9255f12ea55a627e', '1', '1560019189544', '2019-06-01 18:39:50', '-2');
INSERT INTO `sys_session` VALUES ('d584639a4a1d03a1a34b8f162467692775c9fd00a80cb9991f2f8a67e017b998', '-2', '1560015366206', '2019-06-01 17:36:06', '18');
INSERT INTO `sys_session` VALUES ('d861364074147588e7b1ee4da07118e2805bf9074909567d2bb71ef0968055af', '-2', '1560016754389', '2019-06-01 17:59:14', '18');
INSERT INTO `sys_session` VALUES ('d88e1904540b599bae622f21c07ed9fdc95cb87978d03758e0d3ea21e65b01d1', '1', '1559356884900', '2019-05-25 02:41:25', '0');
INSERT INTO `sys_session` VALUES ('d99b2e9ab3842fdee10da1c528c33e8fa935191322e4eafa5087ef32186ec90d', '-2', '1560016170976', '2019-06-01 17:49:31', '18');
INSERT INTO `sys_session` VALUES ('dc3b053945d1b92c61e230681d5867e7dc1d8f5ef15f22270f9e8651072e9235', '1', '1558875686030', '2019-05-19 13:01:26', '0');
INSERT INTO `sys_session` VALUES ('e85b6e88471f2304cd9c985d68f432ca48fdc9dde24c9a9afcb9acff416a9efc', '-2', '1560016110399', '2019-06-01 17:48:30', '18');
INSERT INTO `sys_session` VALUES ('e94f4b8cba656474fb65f59f1b79584edf24766ad5ddc241290392a35cf8b6ff', '1', '1559202593736', '2019-05-23 07:49:54', '0');
INSERT INTO `sys_session` VALUES ('ed4f25dcc7c4c5e8c19e69c4acfbf601412c626bcacb6110a6ac048637e3de57', '-2', '1560015688993', '2019-06-01 17:41:29', '18');
INSERT INTO `sys_session` VALUES ('f0b832b1ec7893618a370e83ec1d333101603d334f6f9f7f9162d1cb1c11ac31', '1', '1558930222824', '2019-05-20 04:10:23', '0');
INSERT INTO `sys_session` VALUES ('f3d902ef439dc226550afda191027923cddb29d68bc5390f9bd23ebbbe094616', '1', '1559205143874', '2019-05-23 08:32:24', '0');
INSERT INTO `sys_session` VALUES ('f53b7e3c43650295494b64198851f3e0d516345c48d32a0c2d4b64c0dc49b153', '1', '1559110769142', '2019-05-22 06:19:29', '0');
INSERT INTO `sys_session` VALUES ('fa23ea6a355d3d60c4b421fd9333abb7d9a0ebcbd18b55ee4a595276969e6400', '1', '1558945459877', '2019-05-20 08:24:20', '0');
