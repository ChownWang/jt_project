package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("query")
	public EasyUITable FindItemByPage(Integer page, Integer rows){
		return itemService.findItemByPage(page,rows);
	}

	@RequestMapping("save")
	public SysResult saveItem(Item item, ItemDesc itemDesc){
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}

	@RequestMapping("query/item/desc/{itemId}")
	public SysResult queryItemDesc(@PathVariable Long itemId){
		return itemService.queryItemDesc(itemId);
	}

	@RequestMapping("update")
	public SysResult updateItem(Item item,ItemDesc itemDesc){
		return itemService.updateItem(item,itemDesc);
	}

	@RequestMapping("delete")
	public SysResult deleteItemByIds(Long[] ids){
	 	List<Long> list = Arrays.asList(ids);
		return itemService.deleteItemByIds(list);
	}


	@RequestMapping("{itemStatus}")
	public SysResult instockOrReshelf(@PathVariable String itemStatus,Long[] ids){
		List<Long> list = Arrays.asList(ids);
		return itemService.instockOrReshelf(itemStatus,list);
	}
}
