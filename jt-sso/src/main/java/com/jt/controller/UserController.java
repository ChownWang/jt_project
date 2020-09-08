package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.UserService;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;

	/*demo测试*/
	@RequestMapping("/getMsg")
	public String  getMsg(){

		return "sso单点登录系统正常";
	}
	/**
	 * 1.url请求地址: http://sso.jt.com/user/check/{param}/{type}
	 * 2.请求参数:    {需要校验的数据}/{校验的类型是谁}
	 * 3.返回值结果:  SysResult返回  需要包含true/false
	 * 4.JSONP请求方式:  返回值必须经过特殊的格式封装 callback(json)
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param,
								 @PathVariable Integer type,
								 String callback){
		/**1.校验数据库中是否存在该数据
		* 存在true  不存在false
		*/

		boolean flag = userService.checkUser(param,type);
		//int a = 1/0;
		return new JSONPObject(callback, SysResult.success(flag));
	}

	/**
	 * 完成httpClient测试
	 * url:http://sso.jt.com/user/httpClient/saveUser?username=111&password="2222"
	 */
	@RequestMapping("/httpClient/saveUser")
	public SysResult saveUser(User user){

		userService.saveHttpCleint(user);
		return SysResult.success();
	}

	@RequestMapping("/query/{ticket}")
	public JSONPObject findUserByTicket(@PathVariable String ticket,String callback){
		/**
		 * 通过key查询redis是否有user信息
		 */
		String userJson = jedisCluster.get(ticket);
		if (StringUtils.isEmpty(userJson)){
			return new JSONPObject(callback, SysResult.fail());
		}else {
			return new JSONPObject(callback, SysResult.success(userJson));
		}
	}
}
