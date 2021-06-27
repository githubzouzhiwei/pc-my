package cn.pconline.pcloud.base.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.security.Security;

/**
 * AES加解密工具类
 * [PKCS#7]
 * @author su*
 *
 */
public class AESUtil {
	
	private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
	
	/**
	 * 对称解密
	 * 微信小程序wx.getUserInfo使用的算法为 AES-128-CBC，数据采用PKCS#7填充
	 * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html#加密数据解密算法
	 * @param encryptedData 密文(Base64)
	 * @param session_key 密钥(Base64)
	 * @param iv 偏移量(Base64)
	 * @return
	 */
	public static String decrypt(String encryptedData, String session_key, String iv) {
		try {
			// 字节转换
			byte[] dataByte = Base64.decode(encryptedData);
			byte[] keyByte = Base64.decode(session_key);
			byte[] ivByte = Base64.decode(iv);
			
			// 转换格式
			Key keySpec = new SecretKeySpec(fillByte(keyByte), KEY_ALGORITHM);
			IvParameterSpec ivSpec = new IvParameterSpec(fillByte(ivByte));
			
			// 初始化
	        Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			
			// 解密
			byte[] resultByte = cipher.doFinal(dataByte);
			return new String(resultByte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 如果不足16位则补足
	 * @param bytes
	 * @return
	 */
	public static byte[] fillByte(byte[] bytes) {
        int base = 16;
        if (bytes.length % base != 0) {
            int groups = bytes.length / base + (bytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(bytes, 0, temp, 0, bytes.length);
            bytes = temp;
        }
        return bytes;
	}
	
}
