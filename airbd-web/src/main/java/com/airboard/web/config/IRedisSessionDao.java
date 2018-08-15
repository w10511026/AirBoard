package com.airboard.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/14
 */
@Slf4j
public class IRedisSessionDao extends CachingSessionDAO {

    private final static String sessionPrefix = "shiro-redis-session:";

    private final static int sessionTime = 1800;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String getKey(String originalKey) {
        return sessionPrefix + originalKey;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        log.debug("createSession:{}", sessionId.toString());
        redisTemplate.opsForValue().set(getKey(sessionId.toString()), session, sessionTime, TimeUnit.MINUTES);
        return sessionId;
    }

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        log.debug("updateSession:{}", session.getId().toString());
        String key = getKey(session.getId().toString());
        redisTemplate.opsForValue().set(key, session, sessionTime, TimeUnit.MINUTES);
    }

    @Override
    protected void doDelete(Session session) {
        log.debug("delSession:{}", session.getId());
        redisTemplate.delete(getKey(session.getId().toString()));
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.debug("readSession:{}", sessionId.toString());
        // 先从缓存中获取session，如果没有再去数据库中获取
        Object sessionObj = redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        if (sessionObj == null) {
            return null;
        }
        return (Session) sessionObj;
    }
}
