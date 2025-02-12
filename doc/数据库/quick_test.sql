

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
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态，0=正常，1=禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试人员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_people
-- ----------------------------
INSERT INTO `demo_people` VALUES ('274f9dabe36c995b249a91678e0a7723', '2024-09-08 19:51:05', '2025-02-12 22:36:13', 0, NULL, '李四2', NULL, '0', NULL, '1');
INSERT INTO `demo_people` VALUES ('2c6fe7356233695519af7f2681516aaf', '2024-09-08 19:46:58', '2024-09-08 19:46:58', 0, '济南的', '张三', '18', '0', '2024-09-08 19:45:37', '0');
INSERT INTO `demo_people` VALUES ('64f25be2a305697d609f5021b9f74089', '2024-09-08 19:51:05', '2025-02-12 22:36:04', 0, '济南的', '张三1', NULL, '1', '2024-09-09 19:45:37', '0');
INSERT INTO `demo_people` VALUES ('c22b2b4325d391e214d21e37418e48b1', '2025-02-12 22:36:52', '2025-02-12 22:37:04', 0, NULL, '阿萨德', '23', '0', NULL, '1');
INSERT INTO `demo_people` VALUES ('dbd0ca482667cc004d65bc42c5f2d3a5', '2025-02-12 22:37:39', '2025-02-12 22:37:39', 0, NULL, '你好', NULL, '0', NULL, '1');
INSERT INTO `demo_people` VALUES ('eea92734fc31166ec5adef8224f75dd8', '2024-09-08 19:47:46', '2024-09-08 19:47:46', 0, NULL, '李四', NULL, '0', NULL, '0');
-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` int(0) NULL DEFAULT 0,
  `config_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数值',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('1', '2024-04-01 19:31:18', '2024-09-09 15:32:48', 0, 'back_api_url', '', '后端API地址');
INSERT INTO `system_config` VALUES ('2', '2024-04-01 19:31:18', '2024-09-09 15:32:48', 0, 'manager_api_lock', '1', '后台管理API开关，0=开启，1=关闭');
INSERT INTO `system_config` VALUES ('3', '2024-04-01 19:31:18', '2024-09-09 15:32:48', 0, 'manager_api_key', '123123', '后台管理API秘钥');

SET FOREIGN_KEY_CHECKS = 1;
