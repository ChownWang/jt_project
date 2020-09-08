package com.jt.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public String saveOrder(Order order) {
		/**
		 * 1.根据用户名id和当前时间毫秒值生成订单id(orderId)
		 * 获取当前日期方便后面对pojo设置创建时间和更新时间
		 */
		String orderId = ""+order.getUserId()+System.currentTimeMillis();
		Date date = new Date();
		/**
		 * 2.设置orderId、更新时间、创建时间并入库订单商品表
		 */
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			orderItem.setUpdated(date);
			orderItem.setCreated(date);
			orderItemMapper.insert(orderItem);
		}
		/**
		 * 3.设置orderId、更新时间、创建时间并入库订单物流表
		 */
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setUpdated(date);
		orderShipping.setCreated(date);
		orderShippingMapper.insert(orderShipping);
		/**
		 * 4.设置orderId、更新时间、创建时间并入库订单表
		 */
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setUpdated(date);
		order.setCreated(date);
		orderMapper.insert(order);
		/**
		 * 5.当所有新增操作都成功后，随便返回个Integer值
		 */
		return orderId;
	}

	/**
	 * 根据orderId查询Order信息
	 * @param id 订单id
	 * @return
	 */
	@Override
	public Order findOrderById(String id) {
		/**
		 * 1.根据订单id查询订单信息
		 */
		Order order = orderMapper.selectById(id);
		/**
		 * 2.根据订单id查询订单物流信息
		 */
		QueryWrapper<OrderShipping> shippingQueryWrapper = new QueryWrapper<>();
		shippingQueryWrapper.eq("order_id",id);
		OrderShipping orderShipping = orderShippingMapper.selectOne(shippingQueryWrapper);
		/**
		 * 3.根据订单id查询订单商品信息
		 */
		QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
		orderItemQueryWrapper.eq("order_id",id);
		List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);
		/**
		 * 4.返回订单
		 */
		return order.setOrderShipping(orderShipping).setOrderItems(orderItems);
	}
}
