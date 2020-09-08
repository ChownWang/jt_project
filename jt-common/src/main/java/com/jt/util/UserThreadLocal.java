package com.jt.util;

import com.jt.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserThreadLocal
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/24 14:32
 * @Version 1.0
 */
public class UserThreadLocal {

    /**
     * 1.定义本地线程变量!!!!!
     */
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();
    //ThreadLocal<Map>

    /**
     * 2.定义数据新增的方法
     * @param user
     */
    public static void set(User user){

        threadLocal.set(user);
    }

    /**
     * 3.获取数据
     * @return
     */
    public static User get(){

        return threadLocal.get();
    }

    /**
     * 4.移除方法 使用threadLocal时切记将数据移除.否则极端条件下,容易产出内存泄露的问题
     */
    public static void remove(){

        threadLocal.remove();
    }

    /**
     * 实现数据的移除
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    //@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        UserThreadLocal.remove();
    }
}
