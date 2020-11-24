package com.evaluation.system.util.Shiro;

import com.evaluation.system.Service.BasicService;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.user;
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
import org.springframework.util.ObjectUtils;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    BasicService basicService;

    @Autowired
    userService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("来到授权------------------");
        String PrimaryPrincipal=(String) principalCollection.getPrimaryPrincipal();
        String duty = basicService.findDutyByNumber(PrimaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        if(duty.equals("班长")){
            simpleAuthorizationInfo.addRole("monitor");
            System.out.println("monitor");
        }else if(duty.equals("管理员")){
            simpleAuthorizationInfo.addRole("admin");
            System.out.println("admin");
        }else {
            simpleAuthorizationInfo.addRole("student");
            System.out.println("student");
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal=(String)authenticationToken.getPrincipal();
        user us=userService.findByNumber(principal);
        System.out.println("来到认证========="+us);
        if(!ObjectUtils.isEmpty(us)){
            return new SimpleAuthenticationInfo(principal,us.getPassword(), ByteSource.Util.bytes(us.getNumber()),getName());
        }
        return null;
    }
}
