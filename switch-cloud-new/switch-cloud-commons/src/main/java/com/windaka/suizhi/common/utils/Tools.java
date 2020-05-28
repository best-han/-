package com.windaka.suizhi.common.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 工具类
 * @author: hjt
 */
public class Tools {
    /**
     * 生成32位不带'-'的UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String getUUID(String xqCode,int length){
        return xqCode+"_"+UUID.randomUUID().toString().replace("-","").substring(0,length);
    }

    /**
     * NULL字符串转换成""
     * @param str
     * @return
     */
    public static String null2String(String str){
        return str==null? "" : str;
    }

    /**
     * 获取请求的真实ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }

        return ip;
    }

    /**
     *
     *@description:
     *@author: zdq
     *@time: 3/20/20 9:31 AM
     *
     */
    //提取字符串 （1，2，3） 为  [1,2,3]数组
    public static String[] extNum(String str) {
        if (str==null){
            return new String[0];
        }
        str=str.replace("(","");
        str=str.replace(")","");
        String[] result=str.split(",");
        return result;
    }

    /**
     * @author ：ygy
     * @date ：2020/5/18 上午11:02
     * @description： 判断 该地址否在青岛市内七区   在返回true   不在返回 false
     */
    public static boolean judgeNatureOfHousehold(String address) {
        boolean j = false;
        String[] area = {"市南", "市北", "黄岛", "崂山", "李沧", "城阳", "即墨"};
        for (int i = 0; i < area.length; i++) {
            if (address.indexOf(area[i]) != -1) {
                j = true;
            }
        }
        return j;
    }
}
