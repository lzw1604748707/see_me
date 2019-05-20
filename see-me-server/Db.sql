/*
Navicat MySQL Data Transfer

Source Server         : 小徐同学
Source Server Version : 50638
Source Database       : jxt

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2017-12-26 15:14:55
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `gradeId` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `motto` varchar(250) DEFAULT NULL COMMENT '班训',
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------


-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(50) NOT NULL COMMENT '反馈人姓名',
  `account` varchar(50) NOT NULL COMMENT '账号',
  `accountType` tinyint(4) NOT NULL COMMENT '反馈账号类型 1-学校 2-教师 3-家长',
  `accountId` int(11) NOT NULL,
  `remark` varchar(500) NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '反馈建议 1-优化 2-平台问题反馈 3-合作 4-其他',
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `schoolId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------


-- ----------------------------
-- Table structure for parent_account
-- ----------------------------
DROP TABLE IF EXISTS `parent_account`;
CREATE TABLE `parent_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-正常 0-禁用',
  `lastLoginAt` datetime NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parent_account
-- ----------------------------

-- ----------------------------
-- Table structure for parent_student
-- ----------------------------
DROP TABLE IF EXISTS `parent_student`;
CREATE TABLE `parent_student` (
  `parentId` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `createAt` datetime NOT NULL,
  PRIMARY KEY (`parentId`,`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parent_student
-- ----------------------------

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminId` int(11) DEFAULT NULL COMMENT '管理员ID',
  `logo` varchar(500) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `address` varchar(250) NOT NULL COMMENT '地址',
  `area` varchar(20) NOT NULL COMMENT '所在区域',
  `contact` varchar(20) NOT NULL COMMENT '联系方式',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1 -正常 0-停用 99-删除',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注信息',
  `leader` varchar(50) NOT NULL COMMENT '负责人',
  `parentCount` int(8) unsigned NOT NULL DEFAULT '0',
  `classCount` int(8) unsigned NOT NULL DEFAULT '0',
  `studentCount` int(8) unsigned NOT NULL DEFAULT '0',
  `teacherCount` int(8) unsigned NOT NULL DEFAULT '0',
  `joinAt` datetime NOT NULL COMMENT '入驻时间',
  `nextPayAt` datetime NOT NULL COMMENT '下次缴费时间',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  `updateAt` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------

-- ----------------------------
-- Table structure for school_account
-- ----------------------------
DROP TABLE IF EXISTS `school_account`;
CREATE TABLE `school_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `isAdmin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否为最高管理员 1-是 0-否',
  `schoolId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '昵称',
  `username` varchar(150) NOT NULL COMMENT '账号',
  `password` varchar(150) NOT NULL COMMENT '密码',
  `ip` varchar(50) DEFAULT NULL,
  `salt` varchar(150) NOT NULL COMMENT '盐',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-禁用',
  `lastLoginAt` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  `updateAt` datetime NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of school_account
-- ----------------------------

-- ----------------------------
-- Table structure for school_introduction
-- ----------------------------
DROP TABLE IF EXISTS `school_introduction`;
CREATE TABLE `school_introduction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) NOT NULL,
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0-待审核 1-通过 2-不通过',
  `content` text NOT NULL COMMENT '学校介绍',
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_introduction
-- ----------------------------

-- ----------------------------
-- Table structure for sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `sensitive_words`;
CREATE TABLE `sensitive_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(32) NOT NULL DEFAULT '',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `word_pinyin` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sensitive_words
-- ----------------------------

-- ----------------------------
-- Table structure for start_page
-- ----------------------------
DROP TABLE IF EXISTS `start_page`;
CREATE TABLE `start_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  `cover` varchar(500) NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '1 - 家长app启动 2-教师app启动页',
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of start_page
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像、照片',
  `englishName` varchar(50) DEFAULT NULL COMMENT '英文名',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `sex` tinyint(2) NOT NULL COMMENT '性别 1-男 2-女',
  `studentCode` varchar(50) NOT NULL COMMENT '学籍号',
  `schoolId` int(11) NOT NULL COMMENT '学校Id',
  `gradeId` int(11) NOT NULL COMMENT '年纪Id',
  `classId` int(11) NOT NULL COMMENT '班级Id',
  `parentCount` int(11) NOT NULL DEFAULT '0' COMMENT '家长数量',
  `goldCardNum` varchar(32) DEFAULT NULL COMMENT '金卡号码',
  `studentId` varchar(32) DEFAULT NULL COMMENT '学生证编号',
  `isWriteCard` tinyint(2) NOT NULL COMMENT '是否写卡   0-否  1-是',
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '昵称',
  `roleId` int(11) NOT NULL,
  `username` varchar(150) NOT NULL COMMENT '账号',
  `password` varchar(150) NOT NULL COMMENT '密码',
  `ip` varchar(50) DEFAULT NULL,
  `salt` varchar(150) NOT NULL COMMENT '盐',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-禁用',
  `lastLoginAt` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `createAt` datetime NOT NULL COMMENT '创建时间',
  `updateAt` datetime NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', '張德明', '0', 'admin', 'b0498af53191eae099c232e43d6386868c547037fc3141c262a746ae4dc686f6', '127.0.0.1', 'Rygz5cocLVXL2zXyudVMXMn6cJOTx5C9', '1', null, '2017-12-21 10:01:18', '2017-12-21 17:02:33');

-- ----------------------------
-- Table structure for sys_adv
-- ----------------------------
DROP TABLE IF EXISTS `sys_adv`;
CREATE TABLE `sys_adv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `url` varchar(500) DEFAULT NULL,
  `cover` varchar(255) NOT NULL,
  `account` varchar(50) NOT NULL,
  `accountId` int(11) NOT NULL,
  `readCount` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-上架 0-下架',
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_adv
-- ----------------------------

-- ----------------------------
-- Table structure for sys_adv_push_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_adv_push_school`;
CREATE TABLE `sys_adv_push_school` (
  `advId` int(11) NOT NULL,
  `schoolId` int(11) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`advId`,`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员-资讯推送范围表';

-- ----------------------------
-- Records of sys_adv_push_school
-- ----------------------------

-- ----------------------------
-- Table structure for sys_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `cover` varchar(255) NOT NULL,
  `account` varchar(50) NOT NULL,
  `accountId` int(11) NOT NULL,
  `content` text,
  `readCount` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-上架 0-下架',
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_info_ext_cover
-- ----------------------------
DROP TABLE IF EXISTS `sys_info_ext_cover`;
CREATE TABLE `sys_info_ext_cover` (
  `infoId` int(11) NOT NULL,
  `url` varchar(500) NOT NULL,
  `createAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员-资讯配图表';

-- ----------------------------
-- Records of sys_info_ext_cover
-- ----------------------------

-- ----------------------------
-- Table structure for sys_info_push_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_info_push_school`;
CREATE TABLE `sys_info_push_school` (
  `infoId` int(11) NOT NULL,
  `schoolId` int(11) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`infoId`,`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员-资讯推送范围表';

-- ----------------------------
-- Records of sys_info_push_school
-- ----------------------------

-- ----------------------------
-- Table structure for sys_info_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_info_school`;
CREATE TABLE `sys_info_school` (
  `infoId` int(11) NOT NULL,
  `schoolId` int(11) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  PRIMARY KEY (`infoId`,`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员-资讯推送范围表';

-- ----------------------------
-- Records of sys_info_school
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `accountId` int(11) NOT NULL,
  `loginAt` datetime NOT NULL,
  `ip` varchar(100) DEFAULT NULL,
  KEY `accountId_index` (`accountId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '1', '0', '用戶管理', '/admin/user', '1', '1', '1', '2017-12-20 18:14:59');
INSERT INTO `sys_menu` VALUES ('2', '0', '1', '學校列表', '/admin/user/school', '2', '1', '1', '2017-12-20 18:16:02');
INSERT INTO `sys_menu` VALUES ('3', '0', '1', '學校介紹審核', '/admin/user/schoolcheck', '2', '2', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('4', '0', '1', '學生列表', '/admin/user/student', '2', '3', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('5', '0', '1', '教師列表', '/admin/user/teacher', '2', '4', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('6', '0', '1', '家長列表', '/admin/user/parent', '2', '5', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('7', '1', '0', '信息管理', '/admin/info', '1', '2', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('8', '0', '7', '資訊列表', '/admin/info/infolist', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('9', '0', '7', '啟動頁管理', '/admin/info/startpage', '2', '2', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('10', '0', '7', '廣告列表', '/admin/info/adv', '2', '3', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('11', '1', '0', '意見反饋', '/admin/feeback', '1', '3', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('12', '0', '11', '意見列表', '/admin/feeback', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('13', '1', '0', '管理員管理', '/admin/auth', '1', '4', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('14', '0', '13', '管理員列表', '/admin/auth/account', '2', '1', '1', '2017-12-20 18:17:16');
INSERT INTO `sys_menu` VALUES ('15', '0', '13', '權限管理', '/admin/auth/role', '2', '2', '1', '2017-12-20 18:17:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
  KEY `fk_sys_role_menu_sys_menu_1` (`menuId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE `sys_session` (
  `id` varchar(64) NOT NULL,
  `accountId` int(11) NOT NULL,
  `expireAt` bigint(20) NOT NULL,
  `careateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_session
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) NOT NULL,
  `teacherCode` varchar(50) NOT NULL,
  `avatar` varchar(500) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `title` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `startTeachDate` datetime NOT NULL COMMENT '入职时间',
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
