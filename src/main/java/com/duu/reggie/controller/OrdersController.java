package com.duu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duu.reggie.common.R;
import com.duu.reggie.entity.OrderDetail;
import com.duu.reggie.entity.Orders;
import com.duu.reggie.entity.OrdersDto;
import com.duu.reggie.service.OrderDetailService;
import com.duu.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailService orderDetailService;
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return R.success("下单成功");
    }

    @Transactional
    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize) {
        Page<Orders> pageinfo = new Page<>(page, pageSize);
        Page<OrdersDto> ordersDtoPage = new Page<>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOrderTime);
        ordersService.page(pageinfo, queryWrapper);
        BeanUtils.copyProperties(pageinfo, ordersDtoPage, "records");
        List<Orders> records = pageinfo.getRecords();
        List<OrdersDto> list = records.stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item, ordersDto);
            Long Id = item.getId();
            //根据id查分类对象
            Orders orders = ordersService.getById(Id);
            String number = orders.getNumber();
            LambdaQueryWrapper<OrderDetail> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(OrderDetail::getOrderId,number);
            List<OrderDetail> orderDetailList = orderDetailService.list(lambdaQueryWrapper);
            int num=0;
            for(OrderDetail l:orderDetailList){
                num+=l.getNumber().intValue();
            }
            ordersDto.setSumNum(num);
            return ordersDto;
            }
        ).collect(Collectors.toList());
        ordersDtoPage.setRecords(list);
        return R.success(ordersDtoPage);
    }
}
