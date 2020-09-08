package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/14 10:36
 * @Version 1.0
 */
@Configuration
@PropertySource(value = "classpath:/properties/redis.properties")
public class RedisConfig {
    @Value("${redis.nodes}")
    private String redisNodes;

    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        String[] split = redisNodes.split(",");
        for (String s : split) {
            /**
             * 获取host和port 192.168.126.129:7000
             */
            String[] hostAndPost = s.split(":");
            HostAndPort hostAndPort = new HostAndPort(hostAndPost[0],Integer.valueOf(hostAndPost[1]));
            nodes.add(hostAndPort);
        }
        JedisCluster jedisCluster = new JedisCluster(nodes);
        return jedisCluster;
    }
    /**
     * 单台测试

     @Value("${redis.host}")
     private String host;
     @Value("${redis.port}")
     private Integer port;

     //将返回值的结果交给spring容器进行管理,如果以后想要使用该对象则可以直接注入.
     @Bean
     public Jedis jedis() {

     return new Jedis(host, port);
     }
     */

}
