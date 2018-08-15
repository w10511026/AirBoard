package com.airboard.web.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @Description 优化doReadSession和doUpdate次数过于频繁的问题
 */
public class ISessionManager extends DefaultWebSessionManager {

    /**
     * 在这里retrieveSession方法中我先将SessionKey强转成WebSessionKey，从中取出request。
     * 第一次readSession我们把Session写入request中，那么之后如果有重复操作的时候 就可以从request中将最先写入的Session给读出来,
     * 从而对 doReadSession的次数进行优化
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request != null && sessionId != null) {
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionId != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }

    /**
     * doUpdate 次数过多的原因基本来源于 timeout的问题，所以可以在onChange 方法中对timeout进行一个限制 ，从而减少doUpdate的次数
     */
    @Override
    protected void onChange(Session session) {
        if (session.getTimeout() > 10000 * 500) {
            return;
        }
        super.onChange(session);
    }

}
