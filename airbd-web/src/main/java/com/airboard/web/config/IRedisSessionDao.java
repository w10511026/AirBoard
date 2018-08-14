package com.airboard.web.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Getter
    @Setter
    @Value("${shiro.session.session-prefix}")
    private String sessionPrefix;

    @Getter
    @Setter
    @Value("${shiro.session.session-time}")
    private int sessionTime;

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
        Session session = null;
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        }
        return session;
    }
}
