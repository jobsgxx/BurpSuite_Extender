package UI;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


/**
 * 编码工具类
 * 实现aes加密、解密
 */
public class AESUtil {

    /** 密钥 */
//    private static final String KEY = "xxxxxxxxxx";

    /** 算法 */
//    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

//    public static void main(String[] args) throws Exception {
//        String content = "{\"sysType\":\"ios\",\"methodCode\":\"B100010006\"}";
//        System.out.println("加密前：" + content);
//
//        System.out.println("加密密钥和解密密钥：" + KEY);

//        String encrypt = oldAesEncrypt(content);
//        String encrypt1 = "t/3Shj9EQVmQPkRElGALqfvHx4arLcM8QCjZ+dam9I4WZr+cD3ueFLV9noDceeNFCRpeoNj8jY7tVvL0FsgiD52HJILr4l4aK2WQpUg4J/w7GjSUMznTYJeMCUYvpEZOgHHTDO7V86E/5/sZ+SClzc2YUKvG93KVDQ1X62t8u6AFmDFVhqSsL8nTqGcA6rPP0RFteWySRmhkYe7iaOMFfxMwHQMT3z45iwPkwpovJXk=";
//        System.out.println("加密后：" + encrypt);
//        System.out.println("加密后：" + encrypt1);
//
//        String decrypt = aesDecrypt(encrypt1);
//        System.out.println("解密后：" + decrypt);
//    }

    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String oldAesEncrypt(String content,String algorithmstr,int datablock,String Key) throws Exception {
        return base64Encode(aesEncryptToBytes(content,algorithmstr,datablock,Key));
    }

    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String newAesEncrypt(String content,String algorithmstr,int datablock, String Key) throws Exception {
        return base64Encode(newAesEncryptToBytes(content,algorithmstr,datablock,Key));
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr,String algorithmstr,int datablock,String Key) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr),algorithmstr,datablock,Key);
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
    }

    /**
     * AES加密
     *
     * @param content 待加密的内容
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content,String algorithmstr,int datablock,String Key) throws Exception {
        if (BooleanUtils.toBoolean("false")) {
            return newAesEncryptToBytes(content,algorithmstr,datablock,Key);
        }
        try {
            return oldAesEncryptToBytes(content, algorithmstr,datablock,Key);
        } catch (Exception e) {
            return newAesEncryptToBytes(content,algorithmstr,datablock,Key);
        }
    }

    /**
     * AES 加密
     * @param content 需要加密的内容
     * @param datablock 加密数据块位数
     * @param Key   密钥
     * @return
     * @throws Exception
     */
    public static byte[] oldAesEncryptToBytes(String content,String algorithmstr,int datablock,String Key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(datablock);//128
        Cipher cipher = Cipher.getInstance(algorithmstr); // "AES/ECB/PKCS5Padding"
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES")); // "xxxxxxxxxx"
        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] newAesEncryptToBytes(String content,String algorithmstr,int datablock,String Key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(datablock);//265
        Cipher cipher = Cipher.getInstance(algorithmstr);// "AES/ECB/PKCS5Padding"
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES")); // "xxxxxxxxxx"
        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes,String algorithmstr,int datablock,String Key) throws Exception {
        if (BooleanUtils.toBoolean("false")) {
            return newAesDecryptByBytes(encryptBytes,algorithmstr,datablock,Key);
        }
        try {
            return oldAesDecryptByBytes(encryptBytes,algorithmstr,datablock,Key);
        } catch (Exception e) {
            return newAesDecryptByBytes(encryptBytes,algorithmstr,datablock,Key);
        }
    }

    /**
     * 解密
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public static String oldAesDecryptByBytes(byte[] encryptBytes,String algorithmstr,int datablock,String Key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(datablock);
        Cipher cipher = Cipher.getInstance(algorithmstr);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    public static String newAesDecryptByBytes(byte[] encryptBytes,String algorithmstr,int datablock,String Key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(datablock);
        Cipher cipher = Cipher.getInstance(algorithmstr);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }
}