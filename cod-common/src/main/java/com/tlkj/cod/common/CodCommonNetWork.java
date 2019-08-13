/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Desc 网络工具类
 *
 * @author sourcod
 * @version 1.0
 * @className NetWorkUtil
 * @date 2018/7/27 下午9:09
 */
public class CodCommonNetWork {

    private static Logger logger = LoggerFactory.getLogger(CodCommonNetWork.class);


    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (logger.isInfoEnabled()) {
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }
        String unknown = "unknown";

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!(unknown.equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 判断ip是否在网段中
     * @param network ip
     * @param mask    网段("/"斜杠模式 或 "-"区间模式)
     * @return 返回值
     */
    public static boolean isInRange(String network, String mask) {
        // ip不能为空
        if (StringUtils.isBlank(network) || StringUtils.isBlank(mask)){
            return false;
        }

        // 判断ip格式是否正确
        if (!ipCheck(network)){
            return false;
        }

        // 使用哪种方式验证
        if (mask.split("-").length > 1){
            return ipIsValid(mask, network);
        } else if (mask.split("/").length > 1) {
            String[] networkips = network.split("\\.");
            int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                    | (Integer.parseInt(networkips[1]) << 16)
                    | (Integer.parseInt(networkips[2]) << 8)
                    | Integer.parseInt(networkips[3]);
            int type = Integer.parseInt(mask.replaceAll(".*/", ""));
            int mask1 = 0xFFFFFFFF << (32 - type);
            String maskIp = mask.replaceAll("/.*", "");
            String[] maskIps = maskIp.split("\\.");
            int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                    | (Integer.parseInt(maskIps[1]) << 16)
                    | (Integer.parseInt(maskIps[2]) << 8)
                    | Integer.parseInt(maskIps[3]);

            return (ipAddr & mask1) == (cidrIpAddr & mask1);
        } else {
            return network.equals(mask);
        }
    }

    /**
     * 判断IP是否在指定范围;
     * 区间模式
     */
    public static boolean ipIsValid(String ipSection, String ip) {
        if (StringUtils.isBlank(ipSection) || StringUtils.isBlank(ip)){
            return false;
        }
        ipSection = ipSection.trim();
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)){
            return false;
        }
        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /**
     * 判断是否是ip格式
     * @param text ip地址
     * @return
     */
    public static boolean ipCheck(String text) {
        if (StringUtils.isNotBlank(text)) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            return text.matches(regex);
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "123.123.123.123";
        System.out.println(ipCheck(a));
        System.out.println(isInRange("123.123.255.255", "123.123.0.0/16"));
        System.out.println(isInRange("123.123.123.123", "123.123.123.1-123.123.123.124"));
    }



}
