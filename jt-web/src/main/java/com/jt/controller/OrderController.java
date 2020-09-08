package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName OrderControllerr
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/24 14:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference(check = false)
    private DubboCartService cartService;
    @Reference(check = false)
    private DubboOrderService orderService;
    /**
     * 跳转到订单确认页面 http://www.jt.com/order/create.html
     * 业务逻辑: 根据userId,之后查询购物车记录信息.之后在页面中展现购物车数据.
     * 页面取值: ${carts}
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest request, Model model) {

        //User user = (User) request.getAttribute("JT_USER");
        //long userId = user.getId();
        Long userId = UserThreadLocal.get().getId();
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("carts", cartList);
        return "order-cart";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order,HttpServletRequest request){
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
        order.setUserId(userId);
        String status = orderService.saveOrder(order);
        if(StringUtils.isEmpty(status)){
            return SysResult.fail();
        }
        return SysResult.success(status);
    }

    @RequestMapping("/success")
    public String findOrderById(String id,Model model){
        Order order = orderService.findOrderById(id);
        model.addAttribute("order",order);
        String date = LocalDateTime.now().plusDays(5L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("date",date);
        return "success";
    }

}