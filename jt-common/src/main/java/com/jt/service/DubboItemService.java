package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

/**
 * @ClassName DubboItemService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/21 19:57
 * @Version 1.0
 */
public interface DubboItemService {
    ItemDesc findItemDescById(Long id);
    Item findItemById(Long id);
}
