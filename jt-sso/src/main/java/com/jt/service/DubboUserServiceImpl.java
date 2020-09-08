package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.UUID;

/**
 * @ClassName DubboUserServiceImpl
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/21 15:43
 * @Version 1.0
 */
@Service    /*注意使用Dubbo的注解*/
public class DubboUserServiceImpl implements DubboUserService{  //alt+shift+p

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 1.密码加密
     * 2.邮箱使用手机代替
     * @param user
     */
    @Override
    public void saveUser(User user) {

        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password).setEmail(user.getPhone());
        userMapper.insert(user);
    }

    @Override
    public String doLogin(User user) {
        String password = user.getPassword();
        /**
         * 将密码用MD5加密然后用户名和密码查询数据库是否有这个用户
         */
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",md5Password);
        /**
         * 取得用户，但要进行脱敏处理，将敏感数据覆盖再传给客户端
         */
        User userDB = userMapper.selectOne(queryWrapper);
        /**
         * 判断用户是否为空，空则证明用户名或密码错误
         */
        if (userDB==null){
            return null;
        }
        /**
         * 用户不为空，使用UUID作为key，user对象为value存到cookie和redis中，当用户下一次访问其他页面的时候就判断他是否已经登录过了
         * 提高用户体验
         */
        String ticket  = UUID.randomUUID().toString().replace("-", "");
        /**
         * 将用户敏感数据覆盖
         */
        user.setPassword("sixsixsix");
        /**
         * 将对象转成json存到redis中
         */
        String userJson = ObjectMapperUtil.toJSON(userDB);
        jedisCluster.setex(ticket,60*60*27*7,userJson);
        return ticket;
    }
}