package com.bsg.upm.util;

import java.math.BigDecimal;

public class NumberUnits {

    /**
     * 四舍五入
     * 
     * @param dValue
     *            待转换数值
     * @param digits
     *            保留小数位数
     * @return
     */
    public static double retainDigits(double dValue, int digits) {
        BigDecimal bg = new BigDecimal(dValue).setScale(digits, BigDecimal.ROUND_HALF_UP);
        return bg.doubleValue();
    }

    public static double retainDigits(double dValue) {
        return retainDigits(dValue, 2);
    }

    public static double toDouble(Object value) {
        if (value == null) {
            throw new ClassCastException("null");
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        } else if (value instanceof Integer) {
            return Double.valueOf(((Integer) value).toString());
        } else {
            throw new ClassCastException(value.getClass().getName());
        }
    }

    public static Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).longValue();
        } else if (value instanceof Integer) {
            return ((Integer) value).longValue();
        } else if (value instanceof Long) {
            return (Long) value;
        } else {
            throw new ClassCastException(value.getClass().getName());
        }
    }
}
