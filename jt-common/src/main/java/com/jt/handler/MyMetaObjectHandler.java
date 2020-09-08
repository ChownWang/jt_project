package com.jt.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/22 15:55
 * @Version 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //设定自动填充的属性和属性值
        this.setInsertFieldValByName("created",new Date(),metaObject);
        this.setInsertFieldValByName("updated",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setInsertFieldValByName("updated",new Date(),metaObject);
    }
}