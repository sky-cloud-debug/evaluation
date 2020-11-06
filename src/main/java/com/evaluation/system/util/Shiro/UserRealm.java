package com.evaluation.system.util.Shiro;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Service.RoleService;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.role;
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
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    RoleService roleService;

    @Autowired
    userService userService;

    @Autowired
    private PasswordReposity passwordReposity;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String PrimaryPrincipal=(String) principalCollection.getPrimaryPrincipal();
        List<role> roles=roleService.findRoleByNumber(PrimaryPrincipal);
        if(!CollectionUtils.isEmpty(roles)){
            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
            roles.forEach(s->{
                simpleAuthorizationInfo.addRole(s.getName());
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal=(String)authenticationToken.getPrincipal();
        user us=userService.findByNumber(principal);
        System.out.println(us);
        if(!ObjectUtils.isEmpty(us)){
            return new SimpleAuthenticationInfo(principal,us.getPassword(), ByteSource.Util.bytes(us.getNumber()),getName());
        }
        return null;
    }
}
