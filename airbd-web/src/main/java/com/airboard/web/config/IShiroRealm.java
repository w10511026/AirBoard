package com.airboard.web.config;

import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserJPAService;
import com.airboard.core.service.system.SysUserRoleService;
import com.airboard.core.vo.SysUserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @Description: 自定义的shiro realm，用作登录用户的认证和授权
 */
public class IShiroRealm extends AuthorizingRealm {

    /**
     * 初始化myshiroRealm的时候需要用到数据库（基于其它方式访问数据的realm，不在讨论范围），
     * 于是不可避免的用到dataSource。如果在初始化shiroRealm的时候容器没有dataSource，
     * 容器就会为我们创建一个，就会产生循环依赖的问题
     * 如果在初始化shiroRealm的时候，容器已经有了dataSource，就不会有循环依赖的问题
     * 我的方法是让shiroRealm初始化的时候某个类变成“懒加载”
     */
    @Lazy
    @Autowired
    SysUserJPAService sysUserJPAService;
    @Lazy
    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * @Description: 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        List<SysUserVO> userList = sysUserJPAService.getByUserName(userName);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        SysUserVO sysUser = userList.get(0);
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassWord(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
    }

    /**
     * @Description: 授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        info.addStringPermission("xxx");
        return info;
    }

}
