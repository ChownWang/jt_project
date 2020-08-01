package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

public interface ItemService {
    public EasyUITable findItemByPage(Integer page,Integer rows);
    void saveItem(Item item);
}
