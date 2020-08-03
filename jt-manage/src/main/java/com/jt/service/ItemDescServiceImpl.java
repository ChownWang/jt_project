package com.jt.service;

import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ItemDescServiceImpl
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/3 14:38
 * @Version 1.0
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ItemDescMapper itemDescMapper;


}
