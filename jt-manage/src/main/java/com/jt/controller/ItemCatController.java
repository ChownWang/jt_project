package com.jt.controller;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ItemCatController
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 11:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("queryItemName")
    @ResponseBody
    public String queryItemName(@RequestParam(value = "itemCatId") Long id){
        String name = itemCatService.queryItemName(id);
        return name;
    }

    @RequestMapping("list")
    @ResponseBody
    public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id",required = false,defaultValue = "0") Long parentId){
        /**查询一级分类信息*/
            List<EasyUITree> uiTreeList = itemCatService.findItemCatByParentId(parentId);
            return uiTreeList;
    }
}
