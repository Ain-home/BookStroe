/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : javaweb

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/05/2020 16:15:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  INDEX `category_id_FK`(`category_id`) USING BTREE,
  CONSTRAINT `category_id_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('0bed8fd0-59c1-434e-90be-cb080c895c0a', 'Java技术核心卷', 'Java书籍', '艾弗茨', 78, 'book2.jpg', '3ca7b244-191b-4ff8-86c4-8c1474953522');
INSERT INTO `book` VALUES ('469fb22d-6cbd-4503-85db-04d1bf6990a1', '算法竞赛', '苏纳法书', '酱豆腐', 50, 'book3.jpg', 'ee1fe44a-0a6a-43ef-b0f8-1f58f088df4a');
INSERT INTO `book` VALUES ('4a8c4b44-bfe2-4d2f-8021-fc1af209ad10', 'Java技术', 'Java技术概述', '㤁', 30, 'book5.jpg', '3ca7b244-191b-4ff8-86c4-8c1474953522');
INSERT INTO `book` VALUES ('68a08379-c7fd-4b66-a1ff-f7fe6beb22e5', '算法入门', '算法入门基础教材', '王岐山', 42.6, 'book7.jpg', 'ee1fe44a-0a6a-43ef-b0f8-1f58f088df4a');
INSERT INTO `book` VALUES ('81e2f948-70db-42fd-b7db-f8931627fe6d', '算法导论', '算法学习书籍', '阿斯顿', 98.8, 'book1.jpg', 'ee1fe44a-0a6a-43ef-b0f8-1f58f088df4a');
INSERT INTO `book` VALUES ('ab1ea587-b71e-4483-8d7e-cc79983ef6fb', 'Java基础', 'Java零基础入门书籍', '零门槛', 36.6, 'book4.jpg', '3ca7b244-191b-4ff8-86c4-8c1474953522');
INSERT INTO `book` VALUES ('d1f769a7-4a96-42c1-b4f6-7825d92bad4a', 'Java概论', 'Java概论介绍', '阿斯旺', 60.1, 'book6.jpg', '3ca7b244-191b-4ff8-86c4-8c1474953522');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('3ca7b244-191b-4ff8-86c4-8c1474953522', 'Java', 'Java系列书籍，看一看');
INSERT INTO `category` VALUES ('ee1fe44a-0a6a-43ef-b0f8-1f58f088df4a', '算法', '算法类别书籍');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `cellphone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `preference` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('060ca77f-42ab-4284-b356-163adfbc5e03', '夜晚', '男', '2000-01-01', '18366667777', '82718@163.com', '唱歌,跳舞,打代码', 'VIP', 'ww');
INSERT INTO `customer` VALUES ('073a97e4-1a20-44a6-80e1-839a6df13cdc', 'aa', '女', '1990-01-01', 'aa', 'aa', '唱歌,跳舞', '普通客户', 'aa');
INSERT INTO `customer` VALUES ('0fc84dbd-68a9-4140-b4fd-5f6f65fd4d49', 'nn', '女', '1990-01-01', 'nn', 'nn', '唱歌', 'VIP', 'nn');
INSERT INTO `customer` VALUES ('10703225-af5f-44fd-a443-00509e22647d', '66', '女', '1990-01-01', '66', '66', '跳舞,打代码', 'VIP', '66');
INSERT INTO `customer` VALUES ('127e9229-9d25-436a-8a03-ec0adc79b44a', '22', '女', '1998-07-15', '22', '22', '跳舞,打代码', 'VIP', '22');
INSERT INTO `customer` VALUES ('25fd8ae8-4ac8-41ac-b595-49461470093c', 'ff', '女', '1990-01-01', 'ff', 'ff', '唱歌,跳舞', '普通客户', 'ff');
INSERT INTO `customer` VALUES ('3bd8c3d8-9d3b-4cfc-b288-2fe9a7f1413e', 'll', '男', '1990-01-01', 'll', 'll', '跳舞,打代码', '普通客户', 'll');
INSERT INTO `customer` VALUES ('45b1361e-c997-470f-8149-32db1091f64d', 'tt', '男', '1990-01-01', 'tt', 'tt', '唱歌,打代码', 'VIP', 'tt');
INSERT INTO `customer` VALUES ('4e019053-fb58-4c28-ba3e-d7ee54f095ee', 'cc', '女', '2005-09-13', 'cc', 'ccd', '跳舞,打代码', 'VIP', 'cc');
INSERT INTO `customer` VALUES ('4f7ba033-6420-4d4d-a1c9-6f9c1c7ee701', '77', '女', '1990-01-01', '77', '77', '唱歌', 'VIP', '77');
INSERT INTO `customer` VALUES ('4fe08665-bbf3-4c89-b9ae-afb421b6e456', 'dd', '女', '2003-01-01', 'dd', 'dd', '唱歌,跳舞,打代码', 'VIP', 'dd');
INSERT INTO `customer` VALUES ('70cc522b-9f0d-4138-a7ce-381d49c3caaa', '99', '女', '1990-01-01', '99', '99', '唱歌', 'VIP', '99');
INSERT INTO `customer` VALUES ('73a41c0e-6725-48d9-8847-626d6dea9932', 'hh', '女', '1990-01-01', 'hh', 'hh', '跳舞', 'VIP', 'hh');
INSERT INTO `customer` VALUES ('77061db2-f9cc-4841-bd51-b8e71b39ebd9', 'kk', '女', '1990-01-01', 'kk', 'kk', '唱歌,跳舞', 'VIP', 'kk');
INSERT INTO `customer` VALUES ('79443a58-8148-4581-aae6-d50c89026ee9', '33', '女', '1990-01-01', '33', '33', '唱歌,跳舞', 'VIP', '33');
INSERT INTO `customer` VALUES ('7e08861c-9196-43bc-a9b2-b41ee0ca1621', '88', '男', '1990-01-01', '88', '88', '跳舞', '普通客户', '88');
INSERT INTO `customer` VALUES ('8e546176-ab73-40cb-89b7-9b391ddfddbd', 'e\'e', '女', '1990-01-01', 'eee\'e', 'e\'e', '唱歌,跳舞', 'VIP', 'eee\'e');
INSERT INTO `customer` VALUES ('8e7ef35e-7cf5-4fd0-a3e5-0675cb8c137f', 'oo', '女', '1990-01-01', 'oo', 'oo', '唱歌,跳舞', '普通客户', 'oo');
INSERT INTO `customer` VALUES ('912f4bff-d6cf-43d7-b0a3-aeac58d42125', 'gg', '女', '1990-01-01', 'gg', 'gg', '跳舞,打代码', '普通客户', 'gg');
INSERT INTO `customer` VALUES ('9580e3ca-40db-4061-9a96-447c24ebc5c7', '44', '女', '1990-01-01', '44', '44', '唱歌', 'VIP', '44');
INSERT INTO `customer` VALUES ('9bb2baf4-266f-4f83-b112-ddb33f2eac67', '11', '女', '1991-01-01', '11', '11', '唱歌,跳舞,打代码', 'VIP', 'ww');
INSERT INTO `customer` VALUES ('9cdd4d8e-f16f-4b2e-a35f-821a4b34b2be', '55', '女', '1990-01-01', '55', '55', '跳舞', '普通客户', '55');
INSERT INTO `customer` VALUES ('9fb5d08c-fdf4-41f8-b7b0-b7bd624fbebf', 'jj', '女', '1990-01-01', 'jj', 'jj', '唱歌,跳舞', '普通客户', 'jj');
INSERT INTO `customer` VALUES ('a2a5b4b6-c8cd-425b-ad79-6ccd25f880d4', 'rr', '女', '1990-01-01', 'rr', 'rr', '唱歌,跳舞', 'VIP', 'rr');
INSERT INTO `customer` VALUES ('b4f2440f-108c-4e1e-98a1-da0da916696e', 'xx', '女', '1990-01-01', 'xx', 'xx', '唱歌,跳舞', '普通客户', 'xx');
INSERT INTO `customer` VALUES ('b5b31c91-2cc2-4fae-9e1b-463f46c3f6f9', 'mm', '女', '1990-01-01', 'mm', 'mm', '唱歌,跳舞', 'VIP', 'mm');
INSERT INTO `customer` VALUES ('c2b624ec-2f89-436e-8d06-1b48e4821762', 'pp', '男', '1990-01-01', 'pp', 'pp', '唱歌,打代码', 'VIP', 'pp');
INSERT INTO `customer` VALUES ('c5aac0a6-fb54-4cfd-9542-0203e0321227', 'yy', '女', '1990-01-01', 'yy', 'yy', '唱歌,跳舞', '普通客户', 'yy');
INSERT INTO `customer` VALUES ('c99f0f2e-36af-486a-9af5-0e9feba67fb2', 'ss', '女', '1990-01-01', 'ss', 'ss', '唱歌', 'VIP', 'ss');
INSERT INTO `customer` VALUES ('d7a37060-e999-423b-8031-5c4be2a586ac', 'qq', '女', '1990-01-01', 'qq', 'qq', '唱歌,跳舞', 'VIP', 'qq');
INSERT INTO `customer` VALUES ('f35f6b5d-03fc-49aa-b86c-c64da91582f8', 'ii', '女', '1990-01-01', 'ii', 'ii', '唱歌,跳舞', 'VIP', 'ii');
INSERT INTO `customer` VALUES ('f8b2a1f0-80c3-4e22-b8a5-2e4b8bf8b215', 'bb', '女', '1990-01-01', 'bb', 'bb', '唱歌,跳舞', '普通客户', 'bb');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cost` double DEFAULT NULL,
  `quantity` int(0) DEFAULT NULL,
  `order_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `book_id_FK`(`book_id`) USING BTREE,
  INDEX `order_id_FK`(`order_id`) USING BTREE,
  CONSTRAINT `book_id_FK` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_id_FK` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('12470f89-1e34-4479-979c-e4ac323088b1', 78, 1, 'f514d802-7b7e-4087-b162-b11d90426e99', '0bed8fd0-59c1-434e-90be-cb080c895c0a');
INSERT INTO `orderitem` VALUES ('50361edf-4f97-4b6f-b77a-73035ba79d89', 98.8, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '81e2f948-70db-42fd-b7db-f8931627fe6d');
INSERT INTO `orderitem` VALUES ('53cb132f-fdf6-45ed-a0ec-e0e862281913', 50, 1, 'f514d802-7b7e-4087-b162-b11d90426e99', '469fb22d-6cbd-4503-85db-04d1bf6990a1');
INSERT INTO `orderitem` VALUES ('7dd9da7e-5dbe-4ac3-8d20-ab934e870dba', 78, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '0bed8fd0-59c1-434e-90be-cb080c895c0a');
INSERT INTO `orderitem` VALUES ('99389f10-6fed-477a-a5f0-bedb4a9bca7c', 36.6, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', 'ab1ea587-b71e-4483-8d7e-cc79983ef6fb');
INSERT INTO `orderitem` VALUES ('a461a2ef-a54e-48f6-ba96-4ea7ec8fe792', 30, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '4a8c4b44-bfe2-4d2f-8021-fc1af209ad10');
INSERT INTO `orderitem` VALUES ('afd45f0b-6bfb-4ba8-966b-ae47b76eed22', 42.6, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '68a08379-c7fd-4b66-a1ff-f7fe6beb22e5');
INSERT INTO `orderitem` VALUES ('d2f51beb-27d9-45fe-b30e-334a546ce593', 60.1, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', 'd1f769a7-4a96-42c1-b4f6-7825d92bad4a');
INSERT INTO `orderitem` VALUES ('f710bdc1-60ff-484f-8d30-037a145763f4', 50, 1, 'cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '469fb22d-6cbd-4503-85db-04d1bf6990a1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` date NOT NULL,
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `users_id_FK`(`user_id`) USING BTREE,
  CONSTRAINT `users_id_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('cdfb245e-9d1d-4ab6-bd20-54df8d8ab9b9', '2020-04-30', '5b06ae13-21ef-40e1-9d47-f449a56403b6', 1, 396.1);
INSERT INTO `orders` VALUES ('f514d802-7b7e-4087-b162-b11d90426e99', '2020-04-30', '5b06ae13-21ef-40e1-9d47-f449a56403b6', 1, 128);

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('00dd7346-9fd1-4004-b0d3-00cb5a910f5a', '删除角色', '删除角色');
INSERT INTO `privilege` VALUES ('2841c38d-bbc7-425c-8642-a3fafe88644f', '修改用户信息', '修改用户信息');
INSERT INTO `privilege` VALUES ('43fd55a0-bc7a-4567-9ce1-4aaf5ddc1248', '删除用户', '删除用户');
INSERT INTO `privilege` VALUES ('60ccd7a7-e78d-4ac1-b8c8-b615ab14d03d', '给角色授予权限', '给角色授权');
INSERT INTO `privilege` VALUES ('a51def88-96e9-4d5f-b563-dc1d9c2619ba', '授权角色', '为用户授权角色');
INSERT INTO `privilege` VALUES ('ab2fbaf3-6e7b-44e3-bcfd-da0ef18a9046', '添加权限', '添加权限');
INSERT INTO `privilege` VALUES ('d081b75d-0cd6-434b-b792-17b5059c366b', '删除权限', '删除权限');
INSERT INTO `privilege` VALUES ('eabd96ec-faf2-4c31-9d4c-98a32b43d6cd', '添加用户', '添加用户');
INSERT INTO `privilege` VALUES ('f4fce8b4-9509-476d-8038-2e347339a4a4', '添加角色', '添加角色');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1b6bb328-d82c-46e7-b2d7-ca47106fabeb', '普通用户', '拥有基本的用户权限');
INSERT INTO `role` VALUES ('431d6a93-3f2e-4d49-8953-2b379dd8dc28', '管理员', '管理维护系统');
INSERT INTO `role` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', '超级管理员', '拥有系统的最高权限');

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege`  (
  `role_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `privilege_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`, `privilege_id`) USING BTREE,
  INDEX `privilege_id_FK`(`privilege_id`) USING BTREE,
  CONSTRAINT `privilege_id_FK` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id_FK1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', '00dd7346-9fd1-4004-b0d3-00cb5a910f5a');
INSERT INTO `role_privilege` VALUES ('1b6bb328-d82c-46e7-b2d7-ca47106fabeb', '2841c38d-bbc7-425c-8642-a3fafe88644f');
INSERT INTO `role_privilege` VALUES ('431d6a93-3f2e-4d49-8953-2b379dd8dc28', '2841c38d-bbc7-425c-8642-a3fafe88644f');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', '2841c38d-bbc7-425c-8642-a3fafe88644f');
INSERT INTO `role_privilege` VALUES ('431d6a93-3f2e-4d49-8953-2b379dd8dc28', '43fd55a0-bc7a-4567-9ce1-4aaf5ddc1248');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', '43fd55a0-bc7a-4567-9ce1-4aaf5ddc1248');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', '60ccd7a7-e78d-4ac1-b8c8-b615ab14d03d');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', 'a51def88-96e9-4d5f-b563-dc1d9c2619ba');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', 'ab2fbaf3-6e7b-44e3-bcfd-da0ef18a9046');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', 'd081b75d-0cd6-434b-b792-17b5059c366b');
INSERT INTO `role_privilege` VALUES ('431d6a93-3f2e-4d49-8953-2b379dd8dc28', 'eabd96ec-faf2-4c31-9d4c-98a32b43d6cd');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', 'eabd96ec-faf2-4c31-9d4c-98a32b43d6cd');
INSERT INTO `role_privilege` VALUES ('c2cb51e1-fc9c-4624-a1dd-373df9c8997c', 'f4fce8b4-9509-476d-8038-2e347339a4a4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('08e95fb6-9bf2-4678-bec9-b035337aca09', 'a用户', '123456');
INSERT INTO `user` VALUES ('2b76cdb6-dfdf-4f64-831d-f5c21cd3d1e5', 'asd', '123456');
INSERT INTO `user` VALUES ('dd13d180-3d29-4cc5-b499-9da07bd86bdb', 'c超级管理员', '123456');
INSERT INTO `user` VALUES ('f396af6e-1797-4f85-8b5a-e45198440e95', 'b管理员', '123456');
INSERT INTO `user` VALUES ('f79382d4-c6ec-444f-8e1a-62305446a5b8', 'asd', '123456');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id_FK`(`role_id`) USING BTREE,
  CONSTRAINT `role_id_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('08e95fb6-9bf2-4678-bec9-b035337aca09', '1b6bb328-d82c-46e7-b2d7-ca47106fabeb');
INSERT INTO `user_role` VALUES ('f396af6e-1797-4f85-8b5a-e45198440e95', '431d6a93-3f2e-4d49-8953-2b379dd8dc28');
INSERT INTO `user_role` VALUES ('dd13d180-3d29-4cc5-b499-9da07bd86bdb', 'c2cb51e1-fc9c-4624-a1dd-373df9c8997c');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('5b06ae13-21ef-40e1-9d47-f449a56403b6', 'asd', '123456');

SET FOREIGN_KEY_CHECKS = 1;
