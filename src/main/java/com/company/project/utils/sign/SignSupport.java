package com.company.project.utils.sign;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

public abstract class SignSupport {

    private static Logger log = LoggerFactory.getLogger(SignSupport.class);

    /**
     * UTF-8得到byte数组
     *
     * @param content
     * @return
     * @author chenxingwang
     */
    public static byte[] getContentBytes(String content) {
        return getContentBytes(content, "UTF-8");
    }

    /**
     * 根据编码得byte数组
     *
     * @param content
     * @param charset
     * @return
     * @author chenxingwang
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("指定的编码集不支持,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 除去参数列表中的空值和参数
     *
     * @return
     * @author chenxingwang
     */
    public static Map<String, String> paramsFilter(Map<String, String> params,
                                                 String[] delParams) {
        Map<String, String> result = new HashMap<String, String>();

        if (params == null || params.size() <= 0) {
            return result;
        }

        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null || value.equals("")
                    || ArrayUtils.contains(delParams, key)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素自然排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, ? extends Object> params, boolean isSort) {

        List<String> keys = new ArrayList<String>(params.keySet());
        if (isSort) {
            Collections.sort(keys);
        }
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key).toString();

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        log.debug("LinkStringResult: {}", prestr.toString());
        return prestr.toString();
    }

    /**
     * 把数组所有元素自然排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, ? extends Object> params) {
        return createLinkString(params, false);
    }

    /**
     * 将int 转换为 byte 数组
     *
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * 将byte数组 转换为int
     *
     * @param b
     * @param offset 位移方式
     * @return
     */
    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

}
