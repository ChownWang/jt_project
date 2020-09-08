package com.jt.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
@Service
public class DubboCartServiceImpl implements DubboCartService{
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
	}

	@Override
	public void updateCart(Cart cart) {
		Cart cartTemp = new Cart();
		cartTemp.setNum(cart.getNum())
				.setUpdated(new Date());
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id",cart.getUserId());
		updateWrapper.eq("item_id",cart.getItemId());
		cartMapper.update(cartTemp,updateWrapper);
	}

	@Override
	public void saveCart(Cart cart) {
		/**
		 * 判断tb_cart表中是否有数据
		 */
		QueryWrapper<Cart> queryWrapper = new QueryWrapper();
		queryWrapper.eq("item_id",cart.getItemId());
		queryWrapper.eq("user_id",cart.getUserId());
		Cart cartOne = cartMapper.selectOne(queryWrapper);
		if(cartOne==null){
			/**
			 * 第一次向库中增加数据
			 */
			cart.setCreated(new Date()).setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			/**
			 * 不是第一次则需要更新item的数量
			 */
			Cart cartUp = new Cart();
			cartUp.setId(cartOne.getId())
				   	.setNum(cartOne.getNum()+cart.getNum())
					.setUpdated(new Date());
			cartMapper.updateById(cartUp);
		}
	}

	/**
	 * 业务:删除购物车操作
	 * url地址: http://www.jt.com/cart/delete/562379.html
	 * 参数问题: 562379
	 * 返回值结果: 重定向到购物车列表页面
	 */
	@Override
	public void deleteCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>(cart);
		cartMapper.delete(queryWrapper);
	}

}
