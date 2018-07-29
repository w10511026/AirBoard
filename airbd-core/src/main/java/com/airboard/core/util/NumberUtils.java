package com.airboard.core.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字相关的工具类
 */
public class NumberUtils {
    public static final String STR_0 = "0";

    public static String percentCalc(Long numerator, Long denominator) {
        if (isEmpty(denominator) || isEmpty(numerator)) {
            return STR_0;
        }
        double num = numerator * 100d / denominator;
        DecimalFormat df = new DecimalFormat("0.0");
        String str = df.format(num);
        return str;
    }

    public static String percentCalc(int numerator, int denominator) {
        if (isEmpty(denominator) || isEmpty(numerator)) {
            return STR_0;
        }
        double num = numerator * 100d / denominator;
        DecimalFormat df = new DecimalFormat("0.0");
        String str = df.format(num);
        return str;
    }

    /**
     * @param number 判断基本数据类型或者包装类是否为null或者0
     * @return boolean
     */
    public static boolean isEmpty(Number number) {
        return number == null || number.doubleValue() == 0;
    }

    /**
     * @param number 判断基本数据类型或者包装类是否为null或者大于等于0
     * @return boolean
     */
    public static boolean isGreaterThanOrEqualZero(Number number) {
        return number == null || number.doubleValue() >= 0;
    }

    /**
     * @param number 判断基本数据类型或者包装类是否为null或者小于0
     * @return boolean
     */
    public static boolean isLessThanZero(Number number) {
        return number == null || number.doubleValue() < 0;
    }

    /**
     * @param number 判断基本数据类型或者包装类是否为null或者小于等于0
     * @return boolean
     */
    public static boolean isLessThanOrEqualToZero(Number number) {
        return number == null || number.doubleValue() <= 0;
    }

    /**
     * 字符按比率转数字
     *
     * @param value        需要转换的值
     * @param ratio        转换比率
     * @param defaultValue 转换失败的默认值
     */
    public static Integer transformValue(String value, String ratio, Integer defaultValue) {
        Integer returnValue = defaultValue;
        try {
            if (StringUtils.isNotEmpty(value) && !"--".equals(value)) {
                returnValue = CalculateUtil.multiply(value, ratio, 2).intValue();
                if (returnValue < 0) {
                    returnValue = defaultValue;
                }
            }
        } catch (Exception e) {
        }
        return returnValue;
    }

    /**
     * @param number 判断基本数据类型或者包装类是否不为null或者不为0
     * @return boolean
     */
    public static boolean isNotEmpty(Number number) {
        return !isEmpty(number);
    }

    /**
     * 将字符类型的小数转换成指定小数点右移后的整数
     *
     * @param number 需要处理的字符串
     * @param index  小数点右移的位数
     * @return int int类型的分
     */
    public static int str2int(String number, int index) {
        int result = 0;
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        String temp[] = number.split("\\.");
        //处理整数
        if (StringUtil.isInteger(temp[0])) {
            result = new Integer(temp[0]) * (int) Math.pow(10, index);
        }
        //处理小数
        if (temp.length > 1 && StringUtil.isInteger(temp[1])) {
            //为了处理0.01这种小数点右边是0的情况
            Integer decimals = new Integer("1" + temp[1]) * (int) Math.pow(10, index);
            //截取已经转换为整数的指定位数并合并到结果中
            result += new Integer(decimals.toString().substring(1, index + 1));
        }
        return result;
    }

    // 分转成元
    public static String cent2yuan(Long cent) {
        if (cent == null) {
            return "";
        }
        BigDecimal bigDec = BigDecimal.valueOf(cent).divide(new BigDecimal(100));
        DecimalFormat df = new DecimalFormat("#0.00");
        String yuan = df.format(bigDec);
        return yuan;
    }


    // 元
    public static String yuan2str(Integer money) {
        if (money == null) {
            return "0.00";
        }
        return yuan2str(Long.valueOf(money));
    }

    // 元
    public static String yuan2str(String money) {
        if (money == null) {
            return "0.00";
        }
        BigDecimal bigDec = new BigDecimal(money);
        DecimalFormat df = new DecimalFormat("#0.00");
        String yuan = df.format(bigDec);
        return yuan;
    }

    // 元
    public static String yuan2str(Long money) {
        if (money == null) {
            return "0.00";
        }
        BigDecimal bigDec = BigDecimal.valueOf(money);
        DecimalFormat df = new DecimalFormat("#0.00");
        String yuan = df.format(bigDec);
        return yuan;
    }

    public static String cent2yuanInt(Integer cent) {
        if (cent == null) {
            return "";
        }
        String yuan = cent2yuan(Long.valueOf(cent));
        return yuan;
    }

    public static boolean isNumber(String str) {
        return org.apache.commons.lang3.math.NumberUtils.isNumber(str);
    }

    public static boolean isNotNumber(String str) {
        return !isNumber(str);
    }

    public static long double2long(Object d) {
        double temp = Double.valueOf(d.toString());
        return (long) temp;
    }

    /*public static void main(String[] args) {
        System.out.println(str2int("1.1001", 2));
        System.out.println(percentCalc(2L, 1L));
    }*/

}
