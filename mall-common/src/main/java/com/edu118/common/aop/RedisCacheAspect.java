package com.edu118.common.aop;

/*
 *
 * @Date 2021-01-28 16:57
 * @Author huangshaowu
 *
 */

import com.edu118.common.annotation.RedisDataType;
import com.edu118.common.annotation.RedisQueryCache;
import com.edu118.common.config.RedisUtils;
import com.edu118.common.utils.R;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Method;
import java.util.List;
@Aspect
@Log4j2
public class RedisCacheAspect {
    @Autowired
    private RedisUtils redisUtils;
    RedisCacheAspect() {
        log.info("自定义AOP 缓存切面处理");
    }
    //拦截使用了@RedisQueryCache注解的方法
    @Pointcut("@annotation(com.edu118.common.annotation.RedisQueryCache)")
    public void queryCachePointcut(){}

    @Around("queryCachePointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        log.info("AOP 缓存切面处理 >>>> start ");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取注解类
        RedisQueryCache redisQueryCache = method.getAnnotation(RedisQueryCache.class);
        //获取注解上相应的数据
        String key = redisQueryCache.key();
        RedisDataType type = redisQueryCache.type();
        String message = redisQueryCache.message();
        log.info("AOP 获取到的注解数据 >>>> key = {},type = {},message = {}",key,type,message);
        //读取获取数据
        List<Object> redisValue = redisUtils.lGet(key, 0, -1);
        log.info("AOP 读取缓存中的数据 >>>> redisValue数量 = {}",redisValue.size());
        if (!redisValue.isEmpty()) {
            log.info("AOP 缓存切面处理 >>>> end 耗时：{} 毫秒",System.currentTimeMillis() - beginTime);
            return R.ok().put("data",redisValue.get(0));
        }
        // 缓存中没有数据，调用原始方法查询数据库
        Object object = pjp.proceed();
        log.info("AOP 缓存切面处理 >>>> end 耗时：{} 毫秒", System.currentTimeMillis() - beginTime);
        return object;
    }
}
