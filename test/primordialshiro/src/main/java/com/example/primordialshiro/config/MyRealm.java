package com.example.primordialshiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 在 Realm 中实现简单的认证操作即可，不做授权，授权的具体写法和 SSM 中的 Shiro 一样。
 * 这里的认证表示用户名必须是 yolo ，用户密码必须是 123 ，满足这样的条件，就能登录成功！
 */
//AuthorizingRealm 既有认证又有授权
public class MyRealm extends AuthorizingRealm {

    /**
     * 完成授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    /**
     * 完成认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = (String) token.getPrincipal();
        if ("yolo".equals(username)) {
            return new SimpleAuthenticationInfo(username, "123", getName());
        }
        return null;
    }
}
