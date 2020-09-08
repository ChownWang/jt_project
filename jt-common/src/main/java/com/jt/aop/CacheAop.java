package com.jt.aop;

import com.jt.anno.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @ClassName CacheAop
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/14 10:55
 * @Version 1.0
 */
@Component
@Aspect
public class CacheAop {
    @Autowired(required = false)
    //private Jedis jedis;
    private JedisCluster jedis;

    /*@Pointcut("@annotation(com.jt.anno.CacheFind)")
    */public void pointCut(){};

    @Before("@annotation(com.jt.anno.CacheFind)")
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知");
        /**
         * 方法所在类的路径
         */
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        /**
         * 方法名
         */
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        System.out.println("方法路径:"+typeName+methodName);
        System.out.println("方法参数:"+args);
        System.out.println("方法目标对象:"+target);
    }

    @Around("@annotation(cacheFind)")
    public Object Around(ProceedingJoinPoint joinPoint, CacheFind cacheFind) throws Throwable {
        /**
         * 1.获取注解上面的key
         */
        String key = cacheFind.key();
        /**
         * 2.获取方法上面的参数parentId
         */
        String argId = joinPoint.getArgs()[0].toString();
        /**
         * 3.拼接参数为redis的key
         */
        key+="::"+argId;
        /**
         * 4.判断redis中是否有对应的缓存
         */
        Object object =null;
        if (jedis.exists(key)){
            /**
             * 存在缓存直接返回
             */
            String jsonResult = jedis.get(key);
            /**
             * 获取返回值类型,
             */
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Class returnType = methodSignature.getReturnType();
            /**
             * 将json对象转换成Object对象
             */
            object = ObjectMapperUtil.toObject(jsonResult, returnType);
            System.out.println("从缓存中取数据");
        }else {
            /**
             * 不存在缓存，先查询数据库返回并存入缓存
             */
            object = joinPoint.proceed();
            /**
             * 将对象转换成json串存入缓存
             */
            String json = ObjectMapperUtil.toJSON(object);
            /**
             * 查询过期时间，有过期时间就设置，没有就默认为0
             */
            int seconds = cacheFind.seconds();
            if (seconds>0){
                jedis.setex(key,seconds,json);
            }else {
                jedis.set(key,json);
            }
        }
        return object;
    }

}
