package com.jt.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * @ClassName CookieUtil
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/24 14:25
 * @Version 1.0
 */
public class CookieUtil {
    /**
     * 新增cookie
     * @param name
     * @param value
     * @param path
     * @param domain
     * @param maxAge
     * @param response
     */
    public static void addCookie(String name, String value, String path, String domain, int maxAge, HttpServletResponse response){
        //校验自己完成
        Cookie cookie = new Cookie(name,value);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 删除Cookie   0  -1 用枚举类型优化一下
     * @param name
     * @param path
     * @param domain
     * @param response
     */
    public static void deleteCookie(String name, String path, String domain, HttpServletResponse response){
        //校验自己完成
        Cookie cookie = new Cookie(name,"");
        cookie.setPath(path);
        cookie.setDomain(domain);
        //后期维护使用枚举
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * 根据Cookie的name属性获取Cookie对象
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0){
            for(Cookie cookie : cookies){
                if(name.equalsIgnoreCase(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
