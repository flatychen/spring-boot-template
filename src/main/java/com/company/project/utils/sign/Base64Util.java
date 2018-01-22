package com.company.project.utils.sign;

import org.apache.commons.codec.binary.Base64;

public final class Base64Util extends SignSupport {

	/**
	 * 编码
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(String src) {
		return new String(Base64.encodeBase64(src.getBytes()));
	}

	/**
	 * 编码
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(byte[] src) {
		return new String(Base64.encodeBase64(src));
	}

	/**
	 * 解码
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] decode(String src) {
		return Base64.decodeBase64(src);
	}

	/**
	 * 解码
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] decode(byte[] src) {
		return Base64.decodeBase64(src);
	}
}
