package com.jt.service;

import com.jt.pojo.Order;
import com.jt.vo.SysResult;

/**
 * @ClassName DubboOrderService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/24 15:26
 * @Version 1.0
 */
public interface DubboOrderService {
    String saveOrder(Order order);
    Order findOrderById(String id);
}
