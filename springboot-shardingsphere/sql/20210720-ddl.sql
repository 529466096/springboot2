
DROP TABLE IF EXISTS t_order_0  ;
CREATE TABLE `t_order_0` (
                             `order_id` BIGINT(20) NOT NULL,
                             `goods_id` BIGINT(20) DEFAULT NULL,
                             `pay_status` SMALLINT(6) DEFAULT NULL,
                             `user_id` INT(11) DEFAULT NULL,
                             PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_order_1  ;
CREATE TABLE `t_order_1` (
                             `order_id` BIGINT(20) NOT NULL,
                             `goods_id` BIGINT(20) DEFAULT NULL,
                             `pay_status` SMALLINT(6) DEFAULT NULL,
                             `user_id` INT(11) DEFAULT NULL,
                             PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


SELECT * FROM t_order_0;
SELECT * FROM t_order_1;