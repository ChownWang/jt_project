package com.jt.handler;

import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserInterceptor
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/24 14:23
 * @Version 1.0
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    private static final String TICKET = "JT_TICKET";
    private static final String JTUSER = "JT_USER";
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieUtil.getCookieByName(request, TICKET);
        //不为null.则表示用户可能登录.
        if(cookie != null){
            //cookie中存储的是Redis的key ticket密钥
            String ticket = cookie.getValue();
            if(jedisCluster.exists(ticket)){
                /**
                 * redis中存在用户信息，表明已经登录，可以放行
                 */
                String jsonUser = jedisCluster.get(ticket);
                User user = ObjectMapperUtil.toObject(jsonUser,User.class);
                request.setAttribute(JTUSER,user);

                //利用ThreadLocal来保存用户对象，当前线程共享,但在dubbo RPC里面不能使用，因为是不同的线程，中间采用的是tcp/ip协议连接的
                UserThreadLocal.set(user);
                return true;
            }else{
                //Cookie中的记录与Redis中的记录不一致.应该删除Cookie中的数据.
                CookieUtil.deleteCookie(TICKET, "/", "jt.com",response);
            }
        }

        response.sendRedirect("/user/login.html");
        //表示拦截
        return false;
    }
}
