package com.airboard.core.util;

import java.math.BigDecimal;


/**
 * 计算
 */
public class CalculateUtil {
    /**
     * 加法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal add(double doubleValue1, double doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.add(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal add(String doubleValue1, String doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.add(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal subtract(double doubleValue1, double doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.subtract(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal multiply(double doubleValue1, double doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.multiply(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     *
     * @param value1
     * @param value2
     * @param precision
     * @return decimal
     */
    public static BigDecimal multiply(String value1, String value2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(value1);
        BigDecimal bigDecimal2 = new BigDecimal(value2);
        return bigDecimal1.multiply(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal divide(String doubleValue1, String doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.divide(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     *
     * @param doubleValue1
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal divide(double doubleValue1, double doubleValue2, int precision) {
        BigDecimal bigDecimal1 = new BigDecimal(doubleValue1);
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal1.divide(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法
     *
     * @param bigDecimal
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal add(BigDecimal bigDecimal, double doubleValue2, int precision) {
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal.add(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     *
     * @param bigDecimal
     * @param doubleValue2
     * @param precision
     * @return decimal
     */
    public static BigDecimal multiply(BigDecimal bigDecimal, double doubleValue2, int precision) {
        BigDecimal bigDecimal2 = new BigDecimal(doubleValue2);
        return bigDecimal.multiply(bigDecimal2).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

	/*public static void main(String[] args) {
		System.out.println(CalculateUtil.add(0.29,0.01,2).doubleValue());
	}*/
}
