package com.bsg.upm.check;

import java.io.Serializable;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.bsg.upm.check.resultenum.ChkRsInterface;

public class CheckResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public static CheckResult success() {
        CheckResult checkResult = new CheckResult();
        checkResult.setCode(ChkRsInterface.OK);
        return checkResult;
    }

    public static CheckResult failure(ChkRsInterface crInterface, Object... arguments) {
        CheckResult checkResult = new CheckResult();
        checkResult.setCode(crInterface.getCode());
        FormattingTuple ft = MessageFormatter.arrayFormat(crInterface.getMsg(), arguments);
        checkResult.setMsg(ft.getMessage());
        return checkResult;
    }

    /**
     * 获取code
     * 
     * @return code code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置code
     * 
     * @param code
     *            code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取msg
     * 
     * @return msg msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置msg
     * 
     * @param msg
     *            msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CheckResult [code=" + code + ", msg=" + msg + "]";
    }

}
