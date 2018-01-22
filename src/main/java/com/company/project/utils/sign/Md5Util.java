package com.company.project.utils.sign;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util extends SignSupport {

	/**
	 * MD5 16进制显示,使用utf-8
	 * 
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String Md5Hex(String data) {
		return Md5Hex(data, "utf-8");
	}


	/**
	 * MD5 16进制显示
	 * 
	 * @author flatychen
	 * @date 2014-4-14
	 * @param data
	 * @return
	 */
	public static String Md5Hex(String data, String charset) {
		return DigestUtils.md5Hex(getContentBytes(data, charset));
	}

	/**
	 * 验证签名是否与一致
	 * @author chenxingwang
	 * @param data 待签数据
	 * @param sign 签名字符串（16进制显示） 
	 * @return
	 */
	public static boolean  verify(String data,String sign) {
    	String mysign = Md5Hex(data);
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}

	
	




}
