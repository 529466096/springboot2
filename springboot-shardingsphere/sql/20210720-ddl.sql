CREATE TABLE `t_order_0` (
                             `order_id` bigint(20) NOT NULL,
                             `goods_id` bigint(20) DEFAULT NULL,
                             `pay_status` smallint(6) DEFAULT NULL,
                             `user_id` int(11) DEFAULT NULL,
                             PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_order_1` (
                             `order_id` bigint(20) NOT NULL,
                             `goods_id` bigint(20) DEFAULT NULL,
                             `pay_status` smallint(6) DEFAULT NULL,
                             `user_id` int(11) DEFAULT NULL,
                             PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;