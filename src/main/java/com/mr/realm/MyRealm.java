package com.mr.realm;

import com.mr.model.User;
import com.mr.service.UserService;
import org.apache.shiro.SecurityUtils;
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

import java.util.List;

/**
 * Created by lenovo on 2018/5/25.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName() {

        return "MyRealm";
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        List<String> roles = userService.getRolesByUserId(user.getId());

        List<String> permission = userService.getPermissionByUserId(user.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermissions(permission);
        info.addRoles(roles);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //注意：前台表单的name值必须为 username 和 password
        //1:获取用户名
        String username = (String)token.getPrincipal();
        //2：通过用户名查询对象
        User user = userService.getUserByUserName(username);
        if (user == null) {
            return null;
        }
        //创建 SimpleAuthenticationInfo
        //四个参数（ 当前用户， 数据库查询出来的密码 ，盐值, 当前realm的名字）
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user , user.getPassword() , ByteSource.Util.bytes(username), getName());
        return info;
    }

    //清除缓存，修改角色和权限之后，需要手动清除
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
