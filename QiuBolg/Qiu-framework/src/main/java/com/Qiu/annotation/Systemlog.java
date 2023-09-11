package com.Qiu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//表示Systemlog注解类会保持到runtime阶段
@Target(ElementType.METHOD) //表示Systemlog注解类的注解功能只能用于方法上
//'自定义注解'类，以后就可以使用@Systemlog注解了，注解名就是下面那行的接口名
public @interface Systemlog {

    //为controller提供接口的描述信息，用于'日志记录'功能
    String businessName();

}
