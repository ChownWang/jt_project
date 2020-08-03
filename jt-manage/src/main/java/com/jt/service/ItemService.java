package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tarena
 */
public interface ItemService {
    /**
     * 查询分页
     * @param page 第几页
     * @param rows 每页显示行数
     * @return
     */
    public EasyUITable findItemByPage(Integer page,Integer rows);

    /**
     * 保存商品信息和商品描述，同时insert两张表
     * @param item 商品对象
     */
    void saveItem(Item item, ItemDesc itemDesc);

    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    SysResult queryItemDesc(Long itemId);

    /**
     * 更新商品
     * @param item
     * @return
     */
    SysResult updateItem(Item item,ItemDesc itemDesc);

    /**
     * 根据id删除商品数据
     * @param ids
     * @return
     */
    SysResult deleteItemByIds(List<Long> ids);

    /**
     * 使用restful风格更新商品状态信息(上架=1，下架=2)
     * @param ids
     * @return
     */
    SysResult instockOrReshelf(String itemStatus,List<Long> ids);
}
