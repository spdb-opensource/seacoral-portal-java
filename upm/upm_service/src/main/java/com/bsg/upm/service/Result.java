package com.bsg.upm.service;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.resultenum.ChkRsInterface;

public class Result {

    public static final int SUCCESS = 200;

    private int code;
    private String msg;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS);
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(object);
        return result;
    }

    // public static Result warn(ChkRsInterface checkResult, Object...
    // arguments) {
    // Result result = new Result();
    // result.setCode(checkResult.getCode());
    // FormattingTuple ft = MessageFormatter.arrayFormat(checkResult.getMsg(),
    // arguments);
    // result.setMsg(ft.getMessage());
    // return result;
    // }
    //
    // public static Result warn(Object object, ChkRsInterface checkResult,
    // Object... arguments) {
    // Result result = new Result();
    // result.setCode(checkResult.getCode());
    // FormattingTuple ft = MessageFormatter.arrayFormat(checkResult.getMsg(),
    // arguments);
    // result.setMsg(ft.getMessage());
    // result.setData(object);
    // return result;
    // }

    public static Result failure(ChkRsInterface checkResult, Object... arguments) {
        Result result = new Result();
        result.setCode(checkResult.getCode());
        FormattingTuple ft = MessageFormatter.arrayFormat(checkResult.getMsg(), arguments);
        result.setMsg("[" + checkResult.getCode() + "] " + ft.getMessage());
        return result;
    }

    public static Result failure(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result failure(CheckResult checkResult) {
        Result result = new Result();
        result.setCode(checkResult.getCode());
        result.setMsg("[" + checkResult.getCode() + "] " + checkResult.getMsg());
        return result;
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

    /**
     * 获取data
     * 
     * @return data data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置data
     * 
     * @param data
     *            data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
