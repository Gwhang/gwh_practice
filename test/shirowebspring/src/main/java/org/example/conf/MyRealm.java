package org.example.conf;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("---------->授权");
        return null;
    }

    /**
     *subject.login(token);  在controller中查找执行认证方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("---------->认证，登录");

        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());

        String u = "admin";//数据库的，写死
//        String p = "123";//数据库
        String p = "3cb336337a3bdceb3c0b65bdcc5f122c";
        int isLock = 1;
        if(!username.equals(u)) {//账号错误
            throw new UnknownAccountException("账号异常");
        }
        if(!password.equals(p)) {//密码错误
            throw new IncorrectCredentialsException("密码错误");
        }
        if(isLock!=1){
            throw new LockedAccountException("账户锁定");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,this.getName());

        return info;
    }

}
