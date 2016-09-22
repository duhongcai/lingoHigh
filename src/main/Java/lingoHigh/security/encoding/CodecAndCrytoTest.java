package lingoHigh.security.encoding;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class CodecAndCrytoTest {

    //测试Base64
    private static void testBase64(){
        String str = "hello";
        String base64encoding = Base64.encodeToString(str.getBytes());
        System.out.println(base64encoding);
        String base64decoding = Base64.decodeToString(base64encoding);
        System.out.println(base64decoding);
        System.out.println(str.equals(base64decoding));
    }

    //测试Hex
    private static void testHex(){
        String str = "hello";
        String hexencoding = Hex.encodeToString(str.getBytes());
        System.out.println(hexencoding);
        String hexdecoding = new String(Hex.decode(hexencoding));
        System.out.println(hexdecoding);
        System.out.println(str.equals(hexdecoding));
    }

    //测试CodeSupport
    private static void testCodeSupport(){
        String str = "hello世界";
        byte[] bytes = CodecSupport.toBytes(str,"utf-8");
        System.out.println(bytes);
        String str2 = CodecSupport.toString(bytes,"utf-8");
        System.out.println(str2);
    }

    //测试随机数生成
    private static void testRandom(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("1234".getBytes());
        System.out.println(randomNumberGenerator.nextBytes().toHex());
        System.out.println(randomNumberGenerator.nextBytes().toString());
    }

    //测试Md5
    private static void testMd5(){
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str,salt,1).toString();
        System.out.println(md5);
    }

    private static void testFuncation(){
        String str = "hello";
        System.out.println("原始参数为："+str);
        System.out.println("============测试Sha1========");
        String salt = "123";
        String sha1 = new Sha1Hash(str,salt).toString();
        System.out.println("Sha1加密："+sha1);
        System.out.println("===========测试Sha256=======");
        String sha256 = new Sha256Hash(str,salt).toString();
        System.out.println("Sha256加密："+sha256);
        System.out.println("===========测试Sha384=======");
    }

    private static void testHashService(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(2);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }


    public static void main(String[] args) {
        //testBase64();
        //testHex();
        //testCodeSupport();
        //testRandom();
        //testMd5();
        //testFuncation();
        testHashService();
    }
}

// 4FqiV8oJlcZhVV5yx6l5+A==