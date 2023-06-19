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

 Date: 19/06/2023 17:10:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for schedules
-- ----------------------------
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `classes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班次',
  `start_time` datetime NOT NULL COMMENT '发车时间',
  `start_station` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '起点站',
  `end_station` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终点站',
  `travel_time` double(11, 2) NULL DEFAULT NULL COMMENT '行车时间',
  `capacity` int(11) NULL DEFAULT NULL COMMENT '额定载量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(10) NOT NULL DEFAULT 0 COMMENT '逻辑删除,1为删除',
  PRIMARY KEY (`id`, `classes`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班次信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedules
-- ----------------------------
INSERT INTO `schedules` VALUES (2, '004', '2023-06-21 16:05:00', '南宁', '柳州', 5.50, 100, '2023-06-15 16:05:17', '2023-06-16 20:35:57', 0);
INSERT INTO `schedules` VALUES (3, '006', '2023-06-15 00:00:00', '贵港', '深圳', 1.50, 100, '2023-06-15 16:56:01', '2023-06-16 17:18:41', 0);
INSERT INTO `schedules` VALUES (4, '007', '2023-06-15 00:00:00', '南宁', '柳州', 1.50, 60, '2023-06-15 17:42:02', '2023-06-16 17:18:43', 0);
INSERT INTO `schedules` VALUES (5, '002', '2023-06-15 17:23:00', '南宁', '杭州', 6.20, 66, '2023-06-15 17:46:28', '2023-06-16 17:18:49', 0);
INSERT INTO `schedules` VALUES (23, '008', '2023-06-17 17:23:00', '北京', '重庆', 7.30, 180, '2023-06-16 17:25:39', '2023-06-16 17:25:39', 0);
INSERT INTO `schedules` VALUES (24, '001', '2023-06-17 17:23:00', '北京', '柳州', 6.20, 188, '2023-06-16 17:54:15', '2023-06-16 17:54:15', 0);
INSERT INTO `schedules` VALUES (25, '005', '2023-06-16 17:00:00', '柳州', '上海', 9.38, 150, '2023-06-16 19:36:10', '2023-06-16 19:36:10', 0);
INSERT INTO `schedules` VALUES (26, '003', '2023-06-18 13:16:00', '上海', '南宁', 9.50, 70, '2023-06-18 19:06:28', '2023-06-18 19:06:28', 0);
INSERT INTO `schedules` VALUES (27, '009', '2023-06-20 13:16:00', '贵港', '深圳', 6.20, 65, '2023-06-18 19:07:30', '2023-06-18 19:07:30', 0);
INSERT INTO `schedules` VALUES (28, '010', '2023-06-20 15:00:00', '贵港', '梧州', 0.60, 188, '2023-06-18 19:07:56', '2023-06-18 19:07:56', 0);

SET FOREIGN_KEY_CHECKS = 1;
