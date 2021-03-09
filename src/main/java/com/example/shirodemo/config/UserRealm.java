package com.example.shirodemo.config;

import com.example.shirodemo.entity.GeneralUserEntity;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 *
 * @author admin
 * @version 1.0
 * @date 2021/2/23 16:34
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //身份授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("身份授权执行 doGetAuthorizationInfo");
       // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        return null;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份认证执行 doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //数据库中取用户名，密码
        GeneralUserEntity userEntity = userService.queryUser(userToken);
        if (userEntity == null){
            return null;//为null时抛出异常UnknownAccountException
        }
        return new SimpleAuthenticationInfo("",userEntity.getPassword(),"");
    }
}
