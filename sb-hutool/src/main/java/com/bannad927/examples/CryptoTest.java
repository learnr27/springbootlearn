package com.bannad927.examples;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;

/**
 * 加密分为三种：
 * <p>
 * 对称加密（symmetric），例如：AES、DES等
 * 非对称加密（asymmetric），例如：RSA、DSA等
 * 摘要加密（digest），例如：MD5、SHA-1、SHA-256、HMAC等
 *
 * @author chengbb
 * @date 2020.6.18
 */
@Slf4j
public class CryptoTest {

    public static void main(String[] args) {
        //1.加密解密工具-SecureUtil
        /*log.info("SecureUtil.simpleUUID:{}", SecureUtil.simpleUUID());
        log.info("SecureUtil.md5:{}", SecureUtil.md5(SecureUtil.simpleUUID()));*/

        //2.对称加密-SymmetricCrypto
        /*//通用使用
        String content = "test中文";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("decryptStr:{}", decryptStr);*/

        //DESede实现 Java中AES的默认模式是：AES/ECB/PKCS5Padding
        /*String content = "test中文";
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);
        //加密
        byte[] encrypt = des.encrypt(content);
        //解密
        byte[] decrypt = des.decrypt(encrypt);
        //加密为16进制字符串（Hex表示）
        String encryptHex = des.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        //解密为字符串
        String decryptStr = des.decryptStr(encryptHex);
        log.info("decryptStr:{}", decryptStr);*/

        //AES封装
        /*String content = "test中文";
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 构建
        //快速构建
        AES aes = SecureUtil.aes(key);
        //自定义模式和偏移
        //AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0102030405060708".getBytes());
        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("decryptStr:{}", decryptStr);*/

        //DES封装 Java中默认实现为：DES/CBC/PKCS5Padding
        /*//快速构建
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        DES des = SecureUtil.des(key);
        //自定义模式和偏移
        DES des = new DES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "01020304".getBytes());*/

        //SM4
        /*String content = "test中文";
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4");
        String encryptHex = sm4.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);//test中文
        log.info("decryptStr:{}", decryptStr);*/

        /*String content = "test中文";
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding");

        String encryptHex = sm4.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);//test中文
        log.info("decryptStr:{}", decryptStr);*/

        //3.非对称加密-AsymmetricCrypto
       /* RSA rsa = new RSA();
        //获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
        //获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();
        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        log.info("公钥加密，私钥解密:{}", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        log.info("私钥加密，公钥解密:{}", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));*/

        //自助生成密钥对
        /*KeyPair pair = SecureUtil.generateKeyPair("RSA");
        pair.getPrivate();
        pair.getPublic();*/

        /*String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIL7pbQ+5KKGYRhw7jE31hmA"
                + "f8Q60ybd+xZuRmuO5kOFBRqXGxKTQ9TfQI+aMW+0lw/kibKzaD/EKV91107xE384qOy6IcuBfaR5lv39OcoqNZ"
                + "5l+Dah5ABGnVkBP9fKOFhPgghBknTRo0/rZFGI6Q1UHXb+4atP++LNFlDymJcPAgMBAAECgYBammGb1alndta"
                + "xBmTtLLdveoBmp14p04D8mhkiC33iFKBcLUvvxGg2Vpuc+cbagyu/NZG+R/WDrlgEDUp6861M5BeFN0L9O4hz"
                + "GAEn8xyTE96f8sh4VlRmBOvVdwZqRO+ilkOM96+KL88A9RKdp8V2tna7TM6oI3LHDyf/JBoXaQJBAMcVN7fKlYP"
                + "Skzfh/yZzW2fmC0ZNg/qaW8Oa/wfDxlWjgnS0p/EKWZ8BxjR/d199L3i/KMaGdfpaWbYZLvYENqUCQQCobjsuCW"
                + "nlZhcWajjzpsSuy8/bICVEpUax1fUZ58Mq69CQXfaZemD9Ar4omzuEAAs2/uee3kt3AvCBaeq05NyjAkBme8SwB0iK"
                + "kLcaeGuJlq7CQIkjSrobIqUEf+CzVZPe+AorG+isS+Cw2w/2bHu+G0p5xSYvdH59P0+ZT0N+f9LFAkA6v3Ae56OrI"
                + "wfMhrJksfeKbIaMjNLS9b8JynIaXg9iCiyOHmgkMl5gAbPoH/ULXqSKwzBw5mJ2GW1gBlyaSfV3AkA/RJC+adIjsRGg"
                + "JOkiRjSmPpGv3FOhl9fsBPjupZBEIuoMWOC8GXK/73DHxwmfNmN7C9+sIi4RBcjEeQ5F5FHZ";

        RSA rsa = new RSA(PRIVATE_KEY, null);

        String a = "2707F9FD4288CEF302C972058712F24A5F3EC62C5A14AD2FC59DAB93503AA0FA17113A020EE4EA35EB53F"
                + "75F36564BA1DABAA20F3B90FD39315C30E68FE8A1803B36C29029B23EB612C06ACF3A34BE815074F5EB5AA3A"
                + "C0C8832EC42DA725B4E1C38EF4EA1B85904F8B10B2D62EA782B813229F9090E6F7394E42E6F44494BB8";

        byte[] aByte = HexUtil.decodeHex(a);
        byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
        log.info("已知私钥和密文，如何解密密文:{}", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));*/


        //4.摘要加密-Digester和HMac
        //Digester
        /*Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex("cbb");
        log.info("digestHex:{}", digestHex);
        log.info(" DigestUtil.md5Hex(testStr):{}", DigestUtil.md5Hex("cbb"));*/

        //HMac
        /*String testStr = "test中文";
        byte[] key = "password".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
        log.info("mac.digestHex(testStr):{}", mac.digestHex(testStr));*/

        //SM3
        /*Digester digester = DigestUtil.digester("sm3");
        log.info("digester.digestHex:{}", digester.digestHex("cbb"));*/

        //5.签名和验证-Sign
        /*byte[] data = "我是一段测试字符串".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        byte[] signed = sign.sign(data);
        log.info("验证签名:{}", sign.verify(data, signed));*/

        //6.国密算法工具-SmUtil
        //摘要加密算法SM3
        //log.info("SmUtil.sm3:{}", SmUtil.sm3("cbb"));

        //对称加密SM4
       /* String content = "test中文";
        SymmetricCrypto sm4 = SmUtil.sm4();
        String encryptHex = sm4.encryptHex(content);
        log.info("encryptHex:{}", encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("decryptStr:{}", decryptStr);*/

        //非对称加密SM2
        //使用随机生成的密钥对加密或解密
        /*String text = "我是一段测试aaaa";
        SM2 sm2 = SmUtil.sm2();
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        log.info("encryptStr:{}", encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        log.info("decryptStr:{}", decryptStr);*/

        //使用自定义密钥对加密或解密
        String text = "我是一段测试aaaa";
        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        log.info("encryptStr:{}", encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        log.info("decryptStr:{}", decryptStr);
    }
}
