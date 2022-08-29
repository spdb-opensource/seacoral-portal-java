package com.bsg.upm.util;

import java.util.Random;

/**
 * 主键处理工具类
 * 
 * @author HCK
 *
 */
public class RandomUtil {

    /**
     * 获取随机字母数字组合
     * 
     * @param length
     *            字符串长度
     * @return
     */
    public static String getRandomCharAndNum(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) { // 字符串
                // 取得65大写字母还是97小写字母
                str += (char) (97 + random.nextInt(26));// 取得小写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
    }

}
