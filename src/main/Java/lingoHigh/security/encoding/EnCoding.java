package lingoHigh.security.encoding;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.security.Key;

/**
 * Created by DuHongcai on 2016/9/21.
 */
public class EnCoding {
    public static void main(String[] args) {
//        String str = "hello";
//        String base64Encoded = Base64.encodeToString(str.getBytes());
//        String str2 = Base64.decodeToString(base64Encoded);
//
//        if (str.equals(str2)){
//            System.out.println("==");
//        }
//        if (str == str2){
//            System.out.println("==");
//        }
//        System.out.println("------------------------------------");
//
//        String str3 = "hello";
//        String HexEncoding = Hex.encodeToString(str.getBytes());
//        String str4 = new String(Hex.decode(HexEncoding.getBytes()));
//        if (str3.equals(str4)){
//            System.out.println("==");
//        }
//        String str = "hello";
//        String salt = "123";
//        System.out.println(new Md5Hash(str,salt,2).toString());

//        String str1 = "hello";
//        String str2 = "123";
//        String simpleHash = new SimpleHash("SHA-1",str1,str2).toString();
//        System.out.println(simpleHash);

        DefaultHashService hashService = new DefaultHashService(); //默认算法使用SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder()
                                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
       // System.out.println(hex);


        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        System.out.println();
        String text = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
        //解密
        String text2 = new String(
                aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text.equals(text2));
        System.out.println(text);
    }

}
