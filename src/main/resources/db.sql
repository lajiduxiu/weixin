/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41-0ubuntu0.22.04.1)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41-0ubuntu0.22.04.1)
 File Encoding         : 65001

 Date: 27/02/2025 23:47:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `goods_comment`;
CREATE TABLE `goods_comment`  (
                                  `comment_id` int NOT NULL AUTO_INCREMENT,
                                  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `goods_id` int NULL DEFAULT NULL,
                                  `comment_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_banner
-- ----------------------------
DROP TABLE IF EXISTS `sys_banner`;
CREATE TABLE `sys_banner`  (
                               `ban_id` int NOT NULL AUTO_INCREMENT COMMENT '广告id',
                               `goods_id` int NULL DEFAULT NULL COMMENT '菜品id',
                               `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
                               `images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片',
                               `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0:下架 1:上架）',
                               `order_num` int NULL DEFAULT NULL COMMENT '序号',
                               PRIMARY KEY (`ban_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_banner
-- ----------------------------
INSERT INTO `sys_banner` VALUES (1, 9, '999朵玫瑰', 'http://localhost:8081/images/efbf6da9-b491-4d50-b626-8130366dff74.jpg', '0', 1);
INSERT INTO `sys_banner` VALUES (2, 10, '多少啊', 'http://localhost:8081/images/901f7702-4319-4f31-8356-20063c9ecdcb.jpg', '0', 1);

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
                                 `category_id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
                                 `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
                                 `order_num` int NULL DEFAULT NULL COMMENT '序号',
                                 PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES (1, '玫瑰', 1);
INSERT INTO `sys_category` VALUES (2, '百合', 2);
INSERT INTO `sys_category` VALUES (3, '栀子花', 3);

-- ----------------------------
-- Table structure for sys_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_goods`;
CREATE TABLE `sys_goods`  (
                              `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '鲜花id',
                              `category_id` int NULL DEFAULT NULL COMMENT '分类id',
                              `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '鲜花名称',
                              `goods_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '鲜花路径',
                              `goods_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '鲜花详情',
                              `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0:默认 1:热推品类）',
                              `goods_unit` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '单位',
                              `order_num` int NULL DEFAULT 1 COMMENT '序号',
                              `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
                              `goods_status` tinyint NULL DEFAULT 0 COMMENT '商品状态 0 未上架 1 已上架',
                              PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_goods
-- ----------------------------
INSERT INTO `sys_goods` VALUES (9, 1, '一生所爱', 'http://localhost:8081/images/5cb376d9-e8ef-4b1c-bafd-080f622cfefb.jpg', '<p>爱他送她一生所爱</p>', '1', '1', 1, 1999.00, 0);
INSERT INTO `sys_goods` VALUES (10, 2, '一个侄子百合花', 'http://localhost:8081/images/dfaae296-c159-482f-9482-af13b20558cd.jpg', '<p>11111</p>', '1', '1', 1, 199.00, 0);
INSERT INTO `sys_goods` VALUES (11, 3, '一束栀子', 'http://localhost:8081/images/a8d8aa44-5ac3-4b67-a272-c0d01d3d0105.jpg', '<p>11111</p>', '1', '1', 1, 1111.00, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
                             `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账户',
                             `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                             `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
                             `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                             PRIMARY KEY (`user_id`) USING BTREE,
                             UNIQUE INDEX `username`(`username` ASC) USING BTREE,
                             UNIQUE INDEX `phone`(`phone` ASC) USING BTREE,
                             UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
                                 `address_id` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
                                 `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小程序openid',
                                 `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人姓名',
                                 `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话',
                                 `area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域',
                                 `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详细地址',
                                 `status` tinyint NULL DEFAULT 0 COMMENT '是否默认地址（1:是 0:否）',
                                 PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for user_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect`  (
                                 `collect_id` int NOT NULL AUTO_INCREMENT COMMENT '收藏id',
                                 `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小程序openid',
                                 `goods_id` int NOT NULL COMMENT '商品id',
                                 PRIMARY KEY (`collect_id`) USING BTREE,
                                 UNIQUE INDEX `idx_unique_collect`(`openid` ASC, `goods_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collect
-- ----------------------------

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
                               `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
                               `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序openid',
                               `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
                               `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
                               `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配送地址',
                               `price` decimal(10, 2) NOT NULL COMMENT '订单总金额',
                               `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '订单状态（0:待发货 1:已发货 2:已收货 3:取消）',
                               PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (3, NULL, '', '', ',', 199.00, '2025-02-27 15:20:47', '1');
INSERT INTO `user_order` VALUES (4, NULL, '', '', ',', 1999.00, '2025-02-27 15:21:05', '0');
INSERT INTO `user_order` VALUES (5, NULL, '', '', ',', 1999.00, '2025-02-27 15:27:26', '0');

-- ----------------------------
-- Table structure for user_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_order_detail`;
CREATE TABLE `user_order_detail`  (
                                      `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
                                      `order_id` int NULL DEFAULT NULL COMMENT '订单id',
                                      `goods_id` int NULL DEFAULT NULL COMMENT '商品id',
                                      `goods_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
                                      `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
                                      `goods_unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
                                      `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
                                      `num` int NULL DEFAULT NULL COMMENT '数量',
                                      `specs_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格名称',
                                      PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order_detail
-- ----------------------------
INSERT INTO `user_order_detail` VALUES (3, 3, 10, 'http://localhost:8081/images/dfaae296-c159-482f-9482-af13b20558cd.jpg', '一个侄子百合花', '1', NULL, 1, '');
INSERT INTO `user_order_detail` VALUES (4, 4, 9, 'http://localhost:8081/images/5cb376d9-e8ef-4b1c-bafd-080f622cfefb.jpg', '一生所爱', '1', NULL, 1, '');
INSERT INTO `user_order_detail` VALUES (5, 5, 9, 'http://localhost:8081/images/5cb376d9-e8ef-4b1c-bafd-080f622cfefb.jpg', '一生所爱', '1', NULL, 1, '');

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
                            `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户唯一标识（openid）',
                            `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                            `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
                            PRIMARY KEY (`openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
