package com.jt.web.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.anno.CacheFind;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;

import javax.annotation.Resource;

/**
 * @ClassName DubboItemServiceImpl
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/21 19:57
 * @Version 1.0
 */
@Service
public class DubboItemServiceImpl implements DubboItemService {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ItemDescMapper itemDescMapper;

    @Override
    @CacheFind(key = "ITEM_DESC_ID")
    public ItemDesc findItemDescById(Long id){
        ItemDesc itemDesc = itemDescMapper.selectById(id);
        return itemDesc;
    }
    @Override
    @CacheFind(key = "ITEM_ID")
    public Item findItemById(Long id){
        Item item = itemMapper.selectById(id);
        return item;
    }

}
