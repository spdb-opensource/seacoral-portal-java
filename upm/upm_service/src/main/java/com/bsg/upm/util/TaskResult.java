package com.bsg.upm.util;

public class TaskResult {

    private boolean success;
    private String msg;
    private Object obj;

    /**
     * 获取success
     * 
     * @return success success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置success
     * 
     * @param success
     *            success
     */
    public void setSuccess(boolean success) {
        this.success = success;
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
     * 获取obj
     * 
     * @return obj obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * 设置obj
     * 
     * @param obj
     *            obj
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TaskResult [success=" + success + ", msg=" + msg + ", obj=" + obj + "]";
    }

}
