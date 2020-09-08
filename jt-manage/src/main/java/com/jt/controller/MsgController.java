package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MsgController
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/4 9:33
 * @Version 1.0
 */
@RestController
public class MsgController {
    @Value("${server.port}")
    private int port;


    /**主要获取当前访问服务器的端口号信息!*/
    @RequestMapping("/getPort")
    public String getPort(){
        return "当前访问的端口号是"+ port;
    }
}
