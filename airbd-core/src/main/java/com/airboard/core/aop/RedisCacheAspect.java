package com.airboard.core.aop;

import com.airboard.core.annotation.RedisCache;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;

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
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.airboard.core.service..*.*(..))")
    public void webAspect() {
    }

    @Around("webAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //得到类名、方法名和参数
        String redisResult = "";
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
        Gson gson = new Gson();
        Object result = null;
        if (null == annotation) {
            //方法未设置缓存,正常执行
            result = pjp.proceed(args);
        } else if (!exists(key)) {
            log.info("########缓存未命中########");
            int cacheTime = annotation.cacheTime();
            //缓存不存在，则调用原方法，并将结果放入缓存中
            result = pjp.proceed(args);
            redisResult = gson.toJson(result);
            redisTemplate.opsForValue().set(key, redisResult, cacheTime);
        } else {
            //缓存命中
            log.info("########缓存命中########");
            boolean isCollection = true;
            try {
                //得到被代理方法的返回值类型,并转换为集合类型，如果可以转换，说明是集合类型
                method.getReturnType().asSubclass(Collection.class);
            } catch (ClassCastException e) {
                isCollection = false;
            }
            //得到被代理的方法上的注解
            //Class clazz = method.getAnnotation(RedisCache.class).type();
            //得到被代理方法的返回值类型
            Class returnType = method.getReturnType();
            redisResult = gson.toJson(redisTemplate.opsForValue().get(key));
            if (isCollection) {
                result = gson.fromJson(redisResult, returnType);
            } else {
                result = gson.fromJson(redisResult, returnType);
            }
        }
        return result;
    }

    private boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
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
