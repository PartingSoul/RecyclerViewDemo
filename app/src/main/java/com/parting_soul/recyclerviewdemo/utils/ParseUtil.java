package com.parting_soul.recyclerviewdemo.utils;
 
/**
 * @program: ysczy
 * @description: 进制转换
 * @author: Liang Shan
 * @create: 2019-09-19 17:01
 **/
public class ParseUtil {
 
    /**
    * @Description: 16进制字符串转10进制
    * @Param: [hex]
    * @return: java.lang.String
    * @Author: Liang Shan
    * @Date: 2019/9/24 0024
    */
    public static Integer hexStringToDecimal(String hex) {
        Integer result = Integer.valueOf(hex, 16);
        return result;
    }
 
    /**
     * @Description: 16进制字符串转2进制字符串
     * @Param: [hex]
     * @return: java.lang.String
     * @Author: Liang Shan
     * @Date: 2019/9/24 0024
     */
    public static String hexStringToBinaryString(String hex) {
        Integer temp = Integer.valueOf(hex, 16);
        String result = Integer.toBinaryString(temp);
        return result;
    }
 
    /** 
    * @Description: 10进制转16进制字符串 
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: Liang Shan 
    * @Date: 2019/9/24 0024 
    */ 
    public static String decimalToHexString(Integer decimal) {
        String result = Integer.toHexString(decimal);
        return result;
    }
 
    /**
     * @Description: 10进制转2进制字符串
     * @Param: []
     * @return: java.lang.String
     * @Author: Liang Shan
     * @Date: 2019/9/24 0024
     */
    public static String decimalToBinaryString(Integer decimal) {
        String result = Integer.toBinaryString(decimal);
        return result;
    }
 
    /**
     * @Description: 2进制字符串转10进制
     * @Param: [hex]
     * @return: java.lang.String
     * @Author: Liang Shan 
     * @Date: 2019/9/24 0024 
     */
    public static Integer binaryStringToDecimal(String binary) {
        Integer result = Integer.valueOf(binary, 2);
        return result;
    }
 
    /**
     * @Description: 2进制字符串转16进制字符串
     * @Param: []
     * @return: java.lang.String
     * @Author: Liang Shan
     * @Date: 2019/9/24 0024
     */
    public static String binaryStringToHexString(String binary) {
        Integer temp = Integer.valueOf(binary, 2);
        String result = Integer.toHexString(temp);
        return result;
    }
 
    /**
     * @Description: byte[]转字符串
     * @Param: [bytes]
     * @return: java.lang.String
     * @Author: Liang Shan 
     * @Date: 2019/9/20 0020 
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xff);
            if (temp.length() == 1) {
                // 得到的一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
 
    /**
     * @Description: byte[]转String[]
     * @Param: [bytes]
     * @return: java.lang.String[]
     * @Author: Liang Shan 
     * @Date: 2019/9/20 0020 
     */
    public static String[] bytesToStrings(byte[] bytes) {
        String[] strings = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            String temp = Integer.toHexString(bytes[i] & 0xff);
            if (temp.length() == 1) {
                strings[i] = '0' + temp;
            } else {
                strings[i] = temp;
            }
        }
        return strings;
    }
}