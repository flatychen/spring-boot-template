package com.company.project.utils.web;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil extends WebUtils {

    private static final String[] PROXY_REMOTE_IP_ADDRESS = {
            "X-Forwarded-For", "X-Real-IP", "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP", "HTTP_CLIENT_IP"};

    /**
     * 正式IP源获取
     *
     * @param request
     * @return
     */
    public static String getRealIpAddr(HttpServletRequest request) {
        for (int i = 0; i < PROXY_REMOTE_IP_ADDRESS.length; i++) {
            String ips = request.getHeader(PROXY_REMOTE_IP_ADDRESS[i]);
            if (ips != null && ips.trim().length() > 0) {
                int commaOffset = ips.indexOf(',');
                if (commaOffset < 0) {
                    return ips;
                }
                return ips.substring(0, commaOffset);
            }
        }
        return request.getRemoteHost();
    }

    /**
     * 得到域名
     *
     * @param request
     * @return
     */
    public static String getUrlDomainName(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
//		uri
        return request.getRemoteHost();
    }

    /**
     * IP转换成数值
     *
     * @param ip
     * @return
     */
    public static Long ipAddrToNumber(String ip) {
        String[] items = ip.split("\\.");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }


    /**
     * 数值转换成IP
     *
     * @param ip
     * @return
     */
    public static String numberToIP(Long ip) {
        StringBuilder sb = new StringBuilder();
        sb.append((ip >> 24) & 0xFF).append(".");
        sb.append((ip >> 16) & 0xFF).append(".");
        sb.append((ip >> 8) & 0xFF).append(".");
        sb.append(ip & 0xFF);
        return sb.toString();
    }
}
