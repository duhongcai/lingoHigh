package lingoHigh.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class TestPassword {
    private static Subject login(String configPath,String username,String password){
        Factory<SecurityManager> factory  = new IniSecurityManagerFactory(configPath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return subject;
    }

    private static void testGengeratePassword(){
        String algorithmName = "md5";
        String username = "liu";
        String password = "123";
        String salt1 = username;
        //String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        String salt2 = "f8f33c7b03fa46f0d5abbdef03d682c1";
        int hashIteration = 2;

        SimpleHash hash = new SimpleHash(algorithmName,password,salt1+salt1,hashIteration);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);
    }

// 948c9832c921ef0003e2b0907269934d


    public static void main(String[] args) {
        Subject subject = login("classpath:security/shiro-passwordService.ini","wu","123");
//        System.out.println(subject.getPrincipal());
//        System.out.println(subject.isAuthenticated());
        testGengeratePassword();
    }
}
