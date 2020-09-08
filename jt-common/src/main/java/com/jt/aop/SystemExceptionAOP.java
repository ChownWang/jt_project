package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SystemExceptionAOP
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/14 10:44
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class SystemExceptionAOP {

    //private static final Logger log = LoggerFactory.getLogger(SystemExceptionAOP.class);

    @ExceptionHandler(RuntimeException.class)
    public Object fail(Exception e, HttpServletRequest request){
        //1.获取用户的请求参数
        String callback = request.getParameter("callback");

        //2.判断参数是否有值
        if(StringUtils.isEmpty(callback)){
            //用户请求不是jsonp跨域访问形式
            //打印异常信息
            e.printStackTrace();
            return SysResult.fail();
        }else{
            //jsonp的报错信息.
            e.printStackTrace();
            return new JSONPObject(callback, SysResult.fail());
        }
    }

}
