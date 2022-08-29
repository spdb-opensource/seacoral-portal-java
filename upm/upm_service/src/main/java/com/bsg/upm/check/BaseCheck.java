package com.bsg.upm.check;

import java.util.regex.Pattern;

import com.bsg.upm.service.BaseService;

/**
 * 业务处理检查基类
 * 
 * @author HCK
 * @date 2018年5月7日
 */
public class BaseCheck extends BaseService {

    /**
     * 正则表达式：手机号验证<br>
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 <br>
     * 此方法中前三位格式有： <br>
     * 13+任意数 <br>
     * 15+除4的任意数<br>
     * 18+除1和4的任意数 <br>
     * 17+除9的任意数 <br>
     * 147
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 校验手机号
     * 
     * @param mobileNO
     *            手机号
     * @return 校验通过返回true，否则返回false
     */
    public boolean isValidMobileNO(String mobileNO) {
        return Pattern.matches(REGEX_MOBILE, mobileNO);
    }

    /**
     * 校验邮箱
     * 
     * @param email
     *            邮箱
     * @return 校验通过返回true，否则返回false
     */
    public boolean isVaildEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

}
