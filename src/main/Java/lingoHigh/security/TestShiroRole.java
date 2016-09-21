package lingoHigh.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;

/**
 * Created by DuHongcai on 2016/9/20.
 */
public class TestShiroRole {

    //获取subject
    private static Subject login(String configPath,String username,String password){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configPath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
        return SecurityUtils.getSubject();
    }

    //基于角色的访问权限控制
    private static void testHasRole(){
        Subject subject = login("classpath:security/role/shiro-role.ini","zhang","123");
        if (subject.isAuthenticated()){
           System.out.println("当前用户已登录，登录名为："+subject.getPrincipal());
            if (subject.hasRole("role1")){
                System.out.println("当前用户拥有权限-->role1");
            }
            //subject.checkPermission("role");
        }
    }

    //基于权限的访问权限控制
    private static void testHashPermission(){
        Subject subject = login("classpath:security/role/shiro-permission.ini","zhang","123");
        subject.checkPermissions("user:create","user:update");
        if (subject.isPermitted("user:create")){
            System.out.println("当前用户有创建user的权限");
        }else{
            System.out.println("当前用户没有创建user的权限");
        }
    }

    private static void testIsPermitted(){
        Subject subject = login("classpath:security/role/shiro-permission.ini","li","123");
        subject.checkPermission("user:view");
        subject.checkPermissions("system:user:update","user:view:1");
        subject.checkPermissions("user:create,delete");
        System.out.println(subject.getPrincipal());
        System.out.println(subject.getSession());
    }

    private static void testPermitted(){
        Subject subject = login("classpath:security/role/shiro-authorizer.ini","zhang","123");
        subject.getPrincipal();
        subject.checkPermission("user1:update");
        subject.checkPermission("user2:update");
        subject.checkPermission("+user1+2");//新增权限
        subject.checkPermission("+user2+8");//查看权限
        subject.checkPermission("+user2+10");
        subject.isPermitted("");
        subject.hasRole("");
        subject.checkPermission("+user1+4");//删除权限
        subject.checkPermission("menu:view");

    }

    public static void main(String[] args) {
        //testHasRole();
        //testHashPermission();
        // testIsPermitted();
        testPermitted();
    }
}
