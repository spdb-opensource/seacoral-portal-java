package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class SubtaskDO implements Serializable, Comparable<SubtaskDO> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 子任务编码
     */
    private String id;

    /**
     * 任务编码
     */
    private String taskId;

    /**
     * 子任务操作对象类型
     */
    private String objType;

    /**
     * 子任务操作对象编码
     */
    private String objId;

    /**
     * 子任务操作对象编码
     */
    private String objName;

    /**
     * 子任务操作动作类型
     */
    private String actionType;

    /**
     * 子任务优先级
     */
    private Integer priority;

    /**
     * 子任务开始时间
     */
    private Date startDateTime;

    /**
     * 子任务结束时间
     */
    private Date endDateTime;

    /**
     * 子任务状态
     */
    private String state;

    /**
     * 超时
     */
    private Long timeout;

    /**
     * 任务信息
     */
    private String msg;

    private Object dataSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDataSource() {
        return dataSource;
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "SubtaskDO [id=" + id + ", taskId=" + taskId + ", objType=" + objType + ", objId=" + objId + ", objName="
                + objName + ", actionType=" + actionType + ", priority=" + priority + ", startDateTime=" + startDateTime
                + ", endDateTime=" + endDateTime + ", state=" + state + ", timeout=" + timeout + ", msg=" + msg
                + ", dataSource=" + dataSource + "]";
    }

    @Override
    public int compareTo(SubtaskDO subtaskDO) {
        return this.priority.compareTo(subtaskDO.getPriority());
    }

}
