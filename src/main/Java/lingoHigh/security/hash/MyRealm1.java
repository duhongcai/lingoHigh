package lingoHigh.security.hash;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class MyRealm1 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = "liu";
        String salt2 = "0072273a5d87322163795118fdd7c45e";
        String password = "be320beca57748ab9632c4121ccac0db";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));
        return info;
    }
}
