package com.airboard.core.aop;

import com.airboard.core.annotation.RedisCache;
import com.airboard.core.base.BaseRedisService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/7/28
 */
@Log4j2
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private BaseRedisService baseRedisService;

    @Pointcut("execution(public * com.airboard.api.service..*.*(..))")
    public void webAspect() {
    }

    @Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //得到类名、方法名和参数
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        //根据类名，方法名和参数生成key
        String key = genKey(className, methodName, args);
        log.info("生成的key[{}]", key);
        //得到被代理的方法
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //得到被代理的方法上的注解
        RedisCache annotation = method.getAnnotation(RedisCache.class);
        Object result = null;
        if (null == annotation) {
            //方法未设置缓存,正常执行
            result = pjp.proceed(args);
        } else if (!baseRedisService.exists(key)) {
            log.info("########缓存未命中########");
            int cacheTime = annotation.cacheTime();
            //缓存不存在，则调用原方法，并将结果放入缓存中
            result = pjp.proceed(args);
            baseRedisService.set(key, result, cacheTime);
        } else {
            //缓存命中
            log.info("########缓存命中########");
            result = baseRedisService.get(key);
        }
        return result;
    }

    /**
     * @Description: 生成key
     */
    private String genKey(String className, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder("SpringBoot:");
        sb.append(className);
        sb.append("_");
        sb.append(methodName);
        sb.append("_");
        for (Object object : args) {
            log.info("obj:" + object);
            if (object != null) {
                sb.append(object + "");
                sb.append("_");
            }
        }
        return sb.toString();
    }

}
