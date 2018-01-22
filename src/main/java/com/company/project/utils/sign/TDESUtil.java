package com.company.project.utils.sign;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * 3DES加密解密的工具类
 * 
 * @author chenxingwang
 */
public final class TDESUtil extends SignSupport {

	private static final String Algorithm = "DESede"; // 3des加密算法

	private final static String PADDING = "DESede/ECB/NoPadding"; // 填充方式为不填充

	public static String encrypt2Hex(byte[] keybyte, byte[] src) {
		return Hex.encodeHexString(encrypt(keybyte, src));
	}

	/**
	 * 3DES 加密
	 * 
	 * @param keybyte
	 *            加密密钥，长度必须为24字节
	 * @param src
	 *            被加密的数据缓冲区（源）
	 * @return
	 */
	public static byte[] encrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(PADDING);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);// 在单一方面的加密或解密
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 3DES 加密
	 * 
	 * @param keybyte
	 *            加密密钥，长度必须为24字节
	 * @param src
	 *            被加密的数据缓冲区（源）
	 * @return
	 */
	public static byte[] decrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(PADDING);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

}
