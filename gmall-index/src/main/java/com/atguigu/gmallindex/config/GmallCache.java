package com.atguigu.gmallindex.config;

import java.lang.annotation.*;

/**
 * @author chao
 * @create 2020-06-02
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {

    /**
     * 缓存的前缀
     * @return
     */
    String prefix();

    /**
     * 防止缓存的击穿：分布式锁名
     * @return
     */
    String lock() default "lock";

    /**
     * 缓存时间。单位分钟
     * @return
     */
    long timeout() default 60L;

    /**
     * 防止雪崩，添加过期时间的随机值
     * @return
     */
    int random() default 0;
}
