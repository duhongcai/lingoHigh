package lingoHigh.security.realm;

import lingoHigh.security.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by DuHongcai on 2016/9/20.
 */
public class MyRealm extends AuthorizingRealm  {
    //
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role1");
        info.addRole("role2");
        info.addObjectPermission(new WildcardPermission("user1:*"));
        info.addObjectPermission(new BitPermission("+user1+10"));
        info.addObjectPermission(new BitPermission("+user1+4"));
        info.addStringPermission("+user2+10");
        info.addStringPermission("user2:*");
        info.addStringPermission("menu:");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!"zhang".equals(name)){
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        System.out.println("登录成功");
        return new SimpleAuthenticationInfo(name,password,getName()) ;
    }
}
