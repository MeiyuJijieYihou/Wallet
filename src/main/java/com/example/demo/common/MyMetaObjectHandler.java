package com.example.demo.common;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.prism.impl.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("[insert]插入数据：");
        metaObject.setValue("updateTime",LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("[update]更新数据：");
        metaObject.setValue("updateTime",LocalDateTime.now());
    }
}
