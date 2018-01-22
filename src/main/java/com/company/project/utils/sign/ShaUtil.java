package com.company.project.utils.sign;

import org.apache.commons.codec.digest.DigestUtils;

public class ShaUtil extends SignSupport {

	/**
	 * SHA1 16进制显示
	 * 
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String Sha1(String data) {
		return DigestUtils.sha1Hex(data);
	}

	/**
	 * SHA256 16进制显示
	 * 
	 * @author chenxingwang
	 * @param data
	 * @return
	 */
	public static String sha256(String data) {
		return DigestUtils.sha256Hex(data);
	}

	/**
	 * SHA384 16进制显示
	 * 
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String Sha384(String data) {
		return DigestUtils.sha384Hex(data);
	}

	/**
	 * SHA512 16进制显示
	 * 
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String Sha512(String data) {
		return DigestUtils.sha512Hex(data);
	}

	/**
	 * 验证签名是否与一致
	 * 
	 * @author chenxingwang
	 * @param data
	 *            待签数据
	 * @param sign
	 *            签名字符串（16进制显示）
	 * @return
	 */
	public static boolean verify256(String data, String sign) {
		String mysign = sha256(data);
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

}
