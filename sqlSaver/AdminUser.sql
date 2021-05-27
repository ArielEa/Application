SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `owner_code` varchar(20) NOT NULL COMMENT '用户唯一编码',
    `admin_name` varchar(50) NOT NULL COMMENT '用户名, 可重复',
    `user_identity` int NOT NULL DEFAULT '1' COMMENT '用户类型: 1 超级用户, 0 普通用户',
    `user_status` int NOT NULL DEFAULT '1' COMMENT '用户状态, 1 正常用户，0 锁定用户',
    `register_time` datetime(2) NOT NULL COMMENT '注册时间',
    `update_time` datetime(2) NOT NULL COMMENT '更新时间',
    `login_time` datetime(2) DEFAULT NULL COMMENT '登录时间',
    `login_out` datetime(2) DEFAULT NULL COMMENT '登出时间',
    `token` varchar(50) DEFAULT NULL COMMENt 'token',
    PRIMARY KEY (`id`),
    UNIQUE KEY `owner_code` (`owner_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
