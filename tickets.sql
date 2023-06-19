/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50732 (5.7.32-log)
 Source Host           : localhost:3306
 Source Schema         : ticket_management

 Target Server Type    : MySQL
 Target Server Version : 50732 (5.7.32-log)
 File Encoding         : 65001

 Date: 19/06/2023 17:11:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tickets
-- ----------------------------
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `date` date NULL DEFAULT NULL COMMENT '安排日期',
  `classes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班次',
  `capacity` int(11) NULL DEFAULT NULL COMMENT '额定载量',
  `sold_tickets` int(11) NULL DEFAULT 0 COMMENT '已售（张）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`, `classes`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车票信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tickets
-- ----------------------------
INSERT INTO `tickets` VALUES (1, '2023-06-14', '001', 100, 90, '2023-06-13 17:50:20', '2023-06-16 16:23:54', 0);
INSERT INTO `tickets` VALUES (2, '2023-06-15', '004', 100, 58, '2023-06-14 23:40:16', '2023-06-16 20:36:10', 0);
INSERT INTO `tickets` VALUES (3, '2023-06-15', '006', 100, 33, '2023-06-16 17:20:43', '2023-06-16 20:36:52', 0);
INSERT INTO `tickets` VALUES (4, '2023-06-15', '007', 60, 5, '2023-06-16 17:21:42', '2023-06-16 20:45:45', 0);
INSERT INTO `tickets` VALUES (5, '2023-06-15', '002', 66, 20, '2023-06-16 15:31:41', '2023-06-16 17:19:22', 0);
INSERT INTO `tickets` VALUES (8, '2023-06-17', '008', 180, 68, '2023-06-16 17:25:39', '2023-06-16 20:57:01', 0);
INSERT INTO `tickets` VALUES (10, '2023-06-16', '005', 150, 0, '2023-06-16 19:36:10', '2023-06-16 19:36:10', 0);
INSERT INTO `tickets` VALUES (11, '2023-06-18', '003', 70, 0, '2023-06-18 19:06:29', '2023-06-18 19:06:29', 0);
INSERT INTO `tickets` VALUES (12, '2023-06-20', '009', 65, 0, '2023-06-18 19:07:30', '2023-06-18 19:07:30', 0);
INSERT INTO `tickets` VALUES (13, '2023-06-20', '010', 188, 0, '2023-06-18 19:07:56', '2023-06-18 19:07:56', 0);

SET FOREIGN_KEY_CHECKS = 1;
