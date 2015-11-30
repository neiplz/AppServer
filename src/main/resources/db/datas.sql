
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_information
-- ----------------------------
DROP TABLE IF EXISTS `app_information`;
CREATE TABLE `app_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` varchar(19) DEFAULT NULL,
  `delFlag` varchar(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `downloadUrl` varchar(1000) DEFAULT NULL,
  `name` varchar(80) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `updateTime` varchar(19) DEFAULT NULL,
  `versionCode` int(11) DEFAULT NULL,
  `versionName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_information
-- ----------------------------
INSERT INTO `app_information` VALUES ('1', '2015-11-17 14:27:51', '0', null, null, 'Pedometer', null, '2015-11-17 14:27:51', '2', '0.2');

-- ----------------------------
-- Table structure for app_pref
-- ----------------------------
DROP TABLE IF EXISTS `app_pref`;
CREATE TABLE `app_pref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `goal` int(11) NOT NULL,
  `sensitivity` float NOT NULL,
  `stride` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_pref
-- ----------------------------
INSERT INTO `app_pref` VALUES ('1', 'laosi@163.com', '111222', '45', '35');
INSERT INTO `app_pref` VALUES ('2', 'laoer@tom.com', '345', '33', '23');
INSERT INTO `app_pref` VALUES ('3', 'laoda@sohu.com', '10001', '34', '12');

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` varchar(19) DEFAULT NULL,
  `delFlag` varchar(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `height` float NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `updateTime` varchar(19) DEFAULT NULL,
  `weight` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', '2015-11-17 14:51:24', '0', 'zhangsan@gmail.com', '0', '0', 'zhangsan', 'ej1pGNwpk90=', null, null, '2015-11-17 14:51:24', '0');
INSERT INTO `app_user` VALUES ('2', '2015-11-24 12:19:47', '0', 'lisi@gmail.com', '0', '0', 'lisi', 'ej1pGNwpk90=', null, null, '2015-11-24 12:19:47', '0');
INSERT INTO `app_user` VALUES ('3', '2015-11-24 14:01:51', '0', 'wangwu@gmail.com', '0', '0', 'wangwu', 'ej1pGNwpk90=', null, null, '2015-11-24 14:01:51', '0');
INSERT INTO `app_user` VALUES ('4', '2015-11-24 14:21:22', '0', 'zhaoliu@sina.com', '0', '0', 'zhaoliu', 'ej1pGNwpk90=', null, null, '2015-11-24 14:21:22', '0');
INSERT INTO `app_user` VALUES ('5', '2015-11-24 14:25:02', '0', 'laoda@sohu.com', '0', '0', 'laoda', 'ej1pGNwpk90=', null, null, '2015-11-24 14:25:02', '0');
INSERT INTO `app_user` VALUES ('6', '2015-11-24 14:31:45', '0', 'laoer@tom.com', '0', '0', 'laoer', 'ej1pGNwpk90=', null, null, '2015-11-24 14:31:45', '0');
INSERT INTO `app_user` VALUES ('7', '2015-11-24 20:14:36', '0', 'laosan@163.com', '0', '0', 'laosan', 'ej1pGNwpk90=\n', null, null, '2015-11-24 20:14:36', '0');
INSERT INTO `app_user` VALUES ('8', '2015-11-24 20:19:40', '0', 'laosi@163.com', '0', '175', 'laosi', 'Wh7WbdsOZcA=', null, null, '2015-11-24 20:19:40', '60');
