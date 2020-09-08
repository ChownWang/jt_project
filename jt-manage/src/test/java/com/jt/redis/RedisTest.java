package com.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/12 14:59
 * @Version 1.0
 */
//@SpringBootTest
public class RedisTest {
    @Test
    public void test01(){
        Jedis jedis = new Jedis("192.168.126.129",6379);
        //1.向redis中保存数据
        jedis.set("2004", "哈哈哈 今天下雨了 不负众望");
        //2.从redis中获取数据
        String value = jedis.get("2004");
        System.out.println(value);
    }

}
