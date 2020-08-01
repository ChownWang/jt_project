package com.jt.service;

import com.jt.vo.EasyUITable;
import com.jt.vo.EasyUITree;

import java.util.List;

/**
 * @ClassName ItemCatService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 11:53
 * @Version 1.0
 */
public interface ItemCatService {

    String queryItemName(Long id);
    List<EasyUITree> findItemCatByParentId(Long id);
}
