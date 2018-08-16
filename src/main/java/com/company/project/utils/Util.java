package com.company.project.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class Util {


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

}
