package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ItemController
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/21 20:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/items")
public class ItemController {
    @Reference(check = false)
    private DubboItemService dubboItemService;

    @RequestMapping("/{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){
        /**
         * 根据id查询商品信息和商品详情信息并返回给页面
         */
        Item item = dubboItemService.findItemById(itemId);
        ItemDesc itemDesc = dubboItemService.findItemDescById(itemId);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }

}
