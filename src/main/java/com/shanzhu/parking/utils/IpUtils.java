package com.shanzhu.parking.utils;

import cn.dev33.satoken.context.SaHolder;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * IP related tools
 *
 * @author:     Zi Cheng
 * @date: 2023-12-02
 */
public class IpUtils {

    /**
     * Get the IP address of the requesting host. If it comes in through a proxy, get the real IP address through the firewall
     *
     * @param request http request
     * @return ip address
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //Get the IP configured on the local machine according to the network card
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //For multiple proxies, the first IP is the real IP of the client, and multiple IPs are separated by ','
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * Get request request
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) SaHolder.getRequest().getSource();
    }

}
