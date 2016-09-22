package lingoHigh.security.demo.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator  = new SecureRandomNumberGenerator();

    private String algorithName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(){

    }
}
