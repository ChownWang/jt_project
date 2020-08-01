package com.jt.web;

import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName SystemExceptionAOP
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 16:59
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class SystemExceptionAOP {

    @ExceptionHandler(RuntimeException.class)
    public Object SystemResultException(Exception exception){

        /**exception.printStackTrace();如果有问题,则直接在控制台打印*/
        /**输出日志*/
        log.error("{~~~~~~"+exception.getMessage()+"}", exception);
        /**返回统一的失败数据*/
        return SysResult.fail();
    }
}
