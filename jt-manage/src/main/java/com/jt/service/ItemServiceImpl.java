package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemMapper itemMapper;



	/**
	 *
	 * 利用MP方式进行分页查询
	 * @param page 第几页
	 * @param rows 每页显示条数
	 * @return
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryMapper = new QueryWrapper();
		queryMapper.orderByDesc("updated");
		IPage<Item> iPage = new Page<>(page,rows);
		/**根据分页模型执行分页操作,并将结果返回给分页对象.*/
		iPage = itemMapper.selectPage(iPage,queryMapper);
		/**总记录数由分页工具动态获取*/
		long total = iPage.getTotal();
		/**获取当前分页的信息*/
		List<Item> list = iPage.getRecords();
		return  new EasyUITable(total,list);

	}

	@Transactional
	@Override
	public void saveItem(Item item) {
		item.setStatus(1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		itemMapper.insert(item);
	}
}
