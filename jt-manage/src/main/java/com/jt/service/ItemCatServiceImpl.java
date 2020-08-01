package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ItemCatServiceImpl
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 11:54
 * @Version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private ItemCatMapper itemCatMapper;

    @Override
    public String queryItemName(Long id) {
       ItemCat itemCat =  itemCatMapper.selectById(id);
       String name = itemCat.getName();
       return name;
    }

    @Override
    public List<EasyUITree> findItemCatByParentId(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<ItemCat> list = itemCatMapper.selectList(queryWrapper);
        List<EasyUITree> treeList = new ArrayList<>();
        for (ItemCat itemCat : list) {
            Long id = itemCat.getId();
            String text = itemCat.getName();
            String state = itemCat.getIsParent()?"closed":"open";
            EasyUITree easyUITree = new EasyUITree(id,text,state);
            treeList.add(easyUITree);
        }
        return treeList;
    }
}
