package lingoHigh.security;

import lingoHigh.util.exception.TestLingoHighException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by DuHongcai on 2016/9/19.
 */
public class TestShiroWorld {

    private void shiroDemo1(){
        //利用ini配置文件构造SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:security/shiro.ini");
        //创建SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager赋给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前访客Subject
        Subject subject = SecurityUtils.getSubject();

        //模拟接收到用户名密码 并生成token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","1234");
        try{
            subject.login(token);
        }catch (AuthenticationException erroInfo){
            erroInfo.printStackTrace();
        }
       if (subject.isAuthenticated()){
           System.out.println("已登录");
       }
        subject.logout();
        if (!subject.isAuthenticated()){
            System.out.println("已退出或未登录");
        }
    }

    //自定义realm
    private void testCustomRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:security/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        try{
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (subject.isAuthenticated()){
            System.out.println("已登录");
        }
    }

    //自定义多个realm ???????? 为什么第二个不行呢？？？？
    private void testMuliRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:security/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang2","123");
        subject.login(token);
        System.out.println(subject.getPrincipal());
    }

    private static void login(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:security/shiro-authenticator-atLeastTwo-success.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        token.getPrincipal();
        subject.login(token);
    }

    public static void main(String[] args) {
        TestShiroWorld shiroWorld = new TestShiroWorld();
        //shiroWorld.shiroDemo1();
        //shiroWorld.testCustomRealm();
        //shiroWorld.testMuliRealm();
        //login("classpath:security/shiro-authenticator-all-success.ini");
        login();
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();
        Set<String> ps = principalCollection.getRealmNames();
        for (String p:ps) {
            System.out.println(p);
        }
        tearDown();
    }

    private static void tearDown(){
        ThreadContext.unbindSubject();
    }
}
