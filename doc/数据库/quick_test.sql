SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_people
-- ----------------------------
DROP TABLE IF EXISTS `demo_people`;
CREATE TABLE `demo_people`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` int(0) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别：1=男，0=女',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '到期时间，留空则永久',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试人员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_people
-- ----------------------------
INSERT INTO `demo_people` VALUES ('1', '2024-04-22 21:25:25', '2024-04-23 21:28:49', 1, NULL, 'daeny.cn', 'pop.daeny.cn', '0', NULL);
INSERT INTO `demo_people` VALUES ('2', '2024-04-22 21:26:45', '2024-04-22 21:26:47', 1, NULL, '163.com', 'pop.163.com', '0', NULL);
INSERT INTO `demo_people` VALUES ('274f9dabe36c995b249a91678e0a7723', '2024-09-08 19:51:05', '2024-09-08 19:51:05', 0, NULL, '李四2', NULL, '0', NULL);
INSERT INTO `demo_people` VALUES ('2b75cbad466dbc90c049a463d6867413', '2024-08-01 18:46:40', '2024-08-01 18:46:40', 1, NULL, 'dfjfeuylys.com', 'mail.dfjfeuylys.com', '0', NULL);
INSERT INTO `demo_people` VALUES ('2c6fe7356233695519af7f2681516aaf', '2024-09-08 19:46:58', '2024-09-08 19:46:58', 0, '济南的', '张三', '18', '0', '2024-09-08 19:45:37');
INSERT INTO `demo_people` VALUES ('4', '2024-04-26 22:03:04', '2024-04-26 22:03:04', 1, '微软', 'outlook.com', 'outlook.office365.com', '1', NULL);
INSERT INTO `demo_people` VALUES ('5', '2024-06-19 19:52:18', '2024-06-19 19:52:18', 1, NULL, '126.com', 'pop.126.com', '0', NULL);
INSERT INTO `demo_people` VALUES ('6', '2024-05-14 19:58:16', '2024-05-14 19:58:16', 1, NULL, 'gmail.com', 'pop.gmail.com', '1', NULL);
INSERT INTO `demo_people` VALUES ('635610f9150a9dcd630a51a714c3f2a7', '2024-07-18 20:21:28', '2024-07-18 20:21:28', 1, NULL, 'op.pl', 'pop3.poczta.onet.pl', '1', NULL);
INSERT INTO `demo_people` VALUES ('64f25be2a305697d609f5021b9f74089', '2024-09-08 19:51:05', '2024-09-08 19:51:05', 0, '济南的', '张三1', NULL, '1', '2024-09-09 19:45:37');
INSERT INTO `demo_people` VALUES ('7', '2024-04-26 22:03:42', '2024-04-26 22:03:42', 1, 'hotmail', 'hotmail.com', 'outlook.office365.com', '1', NULL);
INSERT INTO `demo_people` VALUES ('8', '2024-04-26 22:04:07', '2024-04-26 22:04:07', 1, NULL, 'outlook.jp', 'pop.outlook.com', '1', NULL);
INSERT INTO `demo_people` VALUES ('d2d68f3054c60365374aec0f18ce597d', '2024-07-18 20:55:04', '2024-07-18 20:55:04', 1, NULL, 'gmx.com', 'pop.gmx.com', '1', NULL);
INSERT INTO `demo_people` VALUES ('eea92734fc31166ec5adef8224f75dd8', '2024-09-08 19:47:46', '2024-09-08 19:47:46', 0, NULL, '李四', NULL, '0', NULL);

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` int(0) NULL DEFAULT 0,
  `manager_api_lock` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台管理API开关，0=开启，1=关闭',
  `manager_api_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台管理API秘钥',
  `back_api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后端API地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('1', '2024-04-01 19:31:18', '2024-07-18 21:31:15', 0, '0', '111', 'https://43.249.193.55:34046/email');

SET FOREIGN_KEY_CHECKS = 1;
