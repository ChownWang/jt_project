package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

/**
 * @ClassName DubboCartService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/22 15:03
 * @Version 1.0
 */
public interface DubboCartService {

    List<Cart> findCartListByUserId(Long userId);

    void updateCart(Cart cart);

    void saveCart(Cart cart);

    void deleteCart(Cart cart);
}
