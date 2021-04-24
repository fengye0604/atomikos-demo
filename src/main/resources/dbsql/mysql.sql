DROP TABLE IF EXISTS `trade_user`;
CREATE TABLE `trade_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_status` char(2) DEFAULT NULL COMMENT '状态（1：正常，2：锁定）',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of trade_user
-- ----------------------------
INSERT INTO `trade_user` VALUES ('1', 'qqq', 'qq', null, null, null);
INSERT INTO `trade_user` VALUES ('2', '张三', 'zhangsan', null, null, null);
INSERT INTO `trade_user` VALUES ('12', '1152x', 'ec5-d0cb-4b71-ac1b-6bdd45954dd5', null, null, null);
INSERT INTO `trade_user` VALUES ('15', '1153x', '4fe-a818-4d3e-a47d-fdbebb78f3e7', null, null, null);
INSERT INTO `trade_user` VALUES ('16', '1154x', '919-d8b3-4622-8eb3-545cd2daa102', null, null, null);