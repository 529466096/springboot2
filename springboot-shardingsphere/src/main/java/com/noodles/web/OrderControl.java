package com.noodles.web;

import io.swagger.annotations.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.noodles.common.Result;
import com.noodles.dao.OrderDao;
import com.noodles.entity.OrderEntity;

@Api(tags ="订单管理")
@RestController
@RequestMapping("/order")
public class OrderControl {


    @Autowired
    private OrderDao orderDao;

    /**
     * 插入
     */
    @PostMapping("/save")
    public void save(){
        for(int i=0;i<10;i++){
            OrderEntity entity = new OrderEntity();
            entity.setUserId(i);
            entity.setGoodsId(1000L);
            entity.setPayStatus((short)0);
            orderDao.insert(entity);
        }
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result list(){
        Wrapper<OrderEntity> qryWrapper = new QueryWrapper<>();
        List<OrderEntity> list = orderDao.selectList(qryWrapper);
        return Result.ok(list);
    }
}
