package com.zhenghao.fmis.shiro;

import com.zhenghao.fmis.dao.RoleMapper;
import com.zhenghao.fmis.dao.UsersMapper;
import com.zhenghao.fmis.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String getName() {
        return "ShiroRealm";
    }

    // 授权操作
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("进入授权操作.....");

        String username = (String) principals.getPrimaryPrincipal();
        Users user = usersMapper.getUsersByUserName(username);

        List<String> permissions = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        if ("admin".equals(username)) {
            // 拥有所有的权限
            permissions.add("*:*");
            // 查询所有的角色
            roles  = roleMapper.getAllRoles();
        }
//        else {
//            // 根据用户的id查询用户所具有的角色
//            roles = roleServices.getRolesByUserid(user.getUserid());
//            // 根据用户的id查询用户所具有的权限
//            permissions = permissionServices.getPermissionByUserid(user.getUserid());
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    // 认证操作
    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;

        System.out.println("shiroRealm进入");

        // 1.将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2.获取用户名
        String username = upToken.getUsername();
        // 3.查询数据库，查看是否存在用户名和密码
        String realmName = this.getName();


        System.out.println("输入的用户名为：" + username);
        Users user = usersMapper.getUsersByUserName(username);

        if (user != null) {
            Object principal = username;
            Object credentials = user.getPassword();
            info = new SimpleAuthenticationInfo(principal, credentials, ByteSource.Util.bytes(username) ,realmName);
        } else {
            System.out.println("用户名可能不存在！");
            throw new UnknownAccountException();
        }
        return info;
    }


    // 清除缓存
    public void clearCached() {
        // 获取当前登录的用户凭证，然后清除
        PrincipalCollection pc = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(pc);
    }
}
