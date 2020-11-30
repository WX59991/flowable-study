/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-11-30 09:34:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_holiday
-- ----------------------------
DROP TABLE IF EXISTS `wx_holiday`;
CREATE TABLE `wx_holiday` (
  `holiday_id` int(11) NOT NULL AUTO_INCREMENT,
  `holiday_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请假人姓名',
  `holiday_count` int(3) NOT NULL DEFAULT '0' COMMENT '请假天数',
  `holiday_reson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请假原因',
  `task_key` varchar(255) NOT NULL COMMENT '创建的任务模板的id,用于匹配对应的流程',
  `process_id` varchar(20) NOT NULL DEFAULT '' COMMENT '请假实例进程的id',
  PRIMARY KEY (`holiday_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='请假表';
