package com.edu118.common.annotation;

import java.lang.annotation.*;

/*
 *
 * @Date 2021-01-28 16:34
 * @Author huangshaowu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented()
public @interface RedisQueryCache {
    /**
     * Redis的Key
     */
    String key();
    /**
     * 数据结构类型，如hash
     */
    RedisDataType type() default RedisDataType.STRING;
    /**
     * 响应信息
     */
    String message() default "";

}
