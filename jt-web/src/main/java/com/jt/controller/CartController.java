package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CartController
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/22 15:04
 * @Version 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference(check = false)
    private DubboCartService dubboCartService;

    /**
     * 业务思路: 当用户点击购物车按钮时,应该根据userId查询购物车信息,之后在列表页面中展现.
     * 页面数据展现:  利用${cartList}展现数据
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request){
        /**
         * 获取userId  利用单点登录方式动态获取userID  暂时定死
         */
        //Long userId = 7L;
        /**
         * 1.利用域对象获取用户对象信息
         */
        //User user = (User) request.getAttribute("JTUSER");
        /**
         * 2.利用当前线程ThreadLocal获取用户对象信息
         */
        User user = UserThreadLocal.get();
        Long userId = user.getId();
        /**
         * 根据userId查询购物车数据
         */
        List<Cart> cartList = dubboCartService.findCartListByUserId(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public void updateCart(Cart cart){
        Long userId = UserThreadLocal.get().getId();
        //Long userId = 7L;
        cart.setUserId(userId);
        dubboCartService.updateCart(cart);
    }

    /**
     * url地址:http://www.jt.com/cart/add/562379.html
     * 参数: 表单数据提交  cart
     * 返回值: 重定向到购物车展现页面
     */
    @RequestMapping("/add/{itemId}")
    public String addCart(Cart cart){
        Long userId = 7L;
        cart.setUserId(userId);
        dubboCartService.saveCart(cart);
        return "redirect:/cart/show.html";
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId){
        Cart cart = new Cart();
        cart.setUserId(UserThreadLocal.get().getId())
                .setItemId(itemId);
        dubboCartService.deleteCart(cart);
        return "redirect:/cart/show.html";
    }
}
