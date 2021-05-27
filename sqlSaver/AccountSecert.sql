SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_secret
-- ----------------------------
DROP TABLE IF EXISTS `account_secret`;
CREATE TABLE `account_secret` (
    `id` int NOT NULL AUTO_INCREMENT,
    `account_code` varchar(20) NOT NULL COMMENT '账户编码',
    `account_secret` varchar(50) NOT NULL COMMENT '账户唯一辨识码，只用于系统，不用于页面展示，多个系统独立存在，使用账户码区分',
    `daily_status` smallint NOT NULL DEFAULT 1 COMMENT '使用状态 1 使用中， 2 废弃',
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_secret` (`account_secret`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
