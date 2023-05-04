/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : local_data

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 04/05/2023 19:17:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `balance` decimal(10, 2) NOT NULL COMMENT '余额',
  `update_time` datetime(0) NOT NULL COMMENT '更新创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('08eb44333e074e54825d241ffd55d8b9', '答题', 440.00, '2023-05-04 19:12:24');
INSERT INTO `wallet` VALUES ('0fbd01e8f20d4928a983cc2752de8cb1', '开心一下', 1000.00, '2023-05-04 17:55:54');
INSERT INTO `wallet` VALUES ('1', 'user1', 100.00, '2023-05-04 17:56:01');
INSERT INTO `wallet` VALUES ('17ac6e801f7f4f5fbd681e146e62d206', '23', 20.00, '2023-05-04 15:37:55');
INSERT INTO `wallet` VALUES ('2', 'user2', 1.00, '2023-02-15 15:59:43');
INSERT INTO `wallet` VALUES ('3', '3', 0.00, '2023-05-04 14:45:37');
INSERT INTO `wallet` VALUES ('3c4be336545040cebd9e482fa358f06b', '23', 20.00, '2023-05-04 15:35:50');
INSERT INTO `wallet` VALUES ('3ff9203c87c945709c7350c9df160031', 'user2', 20.00, '2023-05-04 15:24:00');
INSERT INTO `wallet` VALUES ('4', '4', 0.00, '2023-05-04 14:45:44');
INSERT INTO `wallet` VALUES ('69431ba16ba24e79b7aeb7437d527f3e', '测试一下', 20.00, '2023-05-04 15:44:19');
INSERT INTO `wallet` VALUES ('7756306ed6b746928df45dfc32e9ec48', '测试一下', 20.00, '2023-05-04 15:46:04');
INSERT INTO `wallet` VALUES ('7a57148b6228458f8b07dc0c91d22648', 'user2', 20.00, '2023-05-04 15:18:09');
INSERT INTO `wallet` VALUES ('9c3c7458401845e4823633d8d0baf4e7', '什么', 520.00, '2023-05-04 15:53:50');
INSERT INTO `wallet` VALUES ('b64ecaebc91d4dea92e132cdddc72e90', '测试一下', 20.00, '2023-05-04 15:47:00');
INSERT INTO `wallet` VALUES ('用户id', '用户id', 0.00, '2023-05-04 14:45:15');

-- ----------------------------
-- Table structure for wallet_details
-- ----------------------------
DROP TABLE IF EXISTS `wallet_details`;
CREATE TABLE `wallet_details`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'id',
  `change_balance` decimal(10, 2) NOT NULL COMMENT '金额变动',
  `wallet_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '钱包id',
  `balance` decimal(10, 2) NOT NULL COMMENT '钱包余额',
  `update_time` datetime(0) NOT NULL COMMENT '更新创建时间',
  `type` int(0) NOT NULL COMMENT '钱包流水类型：0为存钱，1为消费，3为退款，4为提现',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet_details
-- ----------------------------
INSERT INTO `wallet_details` VALUES ('1654049564380733442', 30.00, '0fbd01e8f20d4928a983cc2752de8cb1', 450.00, '2023-05-04 17:05:22', 0);
INSERT INTO `wallet_details` VALUES ('1654049586983837697', 30.00, '0fbd01e8f20d4928a983cc2752de8cb1', 480.00, '2023-05-04 17:05:27', 0);
INSERT INTO `wallet_details` VALUES ('1654049637336457217', 30.00, '0fbd01e8f20d4928a983cc2752de8cb1', 510.00, '2023-05-04 17:05:39', 0);
INSERT INTO `wallet_details` VALUES ('1654049666138742786', 10.00, '0fbd01e8f20d4928a983cc2752de8cb1', 520.00, '2023-05-04 17:05:46', 0);
INSERT INTO `wallet_details` VALUES ('1654050555947753474', 520.13, '0fbd01e8f20d4928a983cc2752de8cb1', 1040.13, '2023-05-04 17:09:18', 0);
INSERT INTO `wallet_details` VALUES ('1654055767701839874', 111.13, '0fbd01e8f20d4928a983cc2752de8cb1', 1151.26, '2023-05-04 17:30:01', 0);
INSERT INTO `wallet_details` VALUES ('1654058926767730690', 520.13, '0fbd01e8f20d4928a983cc2752de8cb1', 1671.39, '2023-05-04 17:42:34', 3);
INSERT INTO `wallet_details` VALUES ('1654081536322777089', 20.00, '08eb44333e074e54825d241ffd55d8b9', 440.00, '2023-05-04 19:12:24', 3);
INSERT INTO `wallet_details` VALUES ('49a82893a9ad4f2ba18a7c1aa096e4e4', 20.00, '0fbd01e8f20d4928a983cc2752de8cb1', 500.00, '2023-05-04 16:34:15', 1);
INSERT INTO `wallet_details` VALUES ('860215f998ff4256a38f18a2eda94460', 671.39, '0fbd01e8f20d4928a983cc2752de8cb1', 1000.00, '2023-05-04 17:55:54', 4);
INSERT INTO `wallet_details` VALUES ('f7321807bf374c65a875b1d731ca1eb1', 80.00, '0fbd01e8f20d4928a983cc2752de8cb1', 420.00, '2023-05-04 16:44:31', 1);
INSERT INTO `wallet_details` VALUES ('fcaa4150a04e42748dfbdd9f93cab20c', 100.00, '08eb44333e074e54825d241ffd55d8b9', 420.00, '2023-05-04 19:11:21', 1);

SET FOREIGN_KEY_CHECKS = 1;
