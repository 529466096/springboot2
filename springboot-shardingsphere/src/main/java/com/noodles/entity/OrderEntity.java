package com.noodles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public final class OrderEntity{

    private static final long serialVersionUID = 4743102234543827854L;

    /**
     * 订单ID
     */
    @TableId(type= IdType.AUTO)
    private Long orderId;

    /**
     * 商品ID
     */
    private long goodsId;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 支付状态 1 支付完成 0 支付中
     */
    private short payStatus;

}
