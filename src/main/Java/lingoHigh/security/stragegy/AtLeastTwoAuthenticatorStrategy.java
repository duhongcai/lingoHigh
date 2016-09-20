package lingoHigh.security.stragegy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;

/**
 * Created by DuHongcai on 2016/9/20.
 */
public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    //返回一个权限验证信息
    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo();
    }

    //返回之前合并的验证消息
    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info;
        if (singleRealmInfo == null){
            info = aggregateInfo;
        }else{
            if (aggregateInfo == null){
                info = singleRealmInfo;
            }else{
                info = merge(singleRealmInfo,aggregateInfo);
            }
        }
        return info;
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals()) || aggregate.getPrincipals().getRealmNames().size()<2){
            throw new AuthenticationException("Authention token of type [" + token.getClass() + "]" + "could not " +
                    "be authenticated by any configured realms.please ensure that at least two realm can authenticate these tokens");
        }
        return aggregate;
    }
}
