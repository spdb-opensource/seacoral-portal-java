package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDO implements Serializable, Comparable<TaskDO> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 任务编码
     */
    private String id;

    /**
     * 所属站点
     */
    private String siteId;

    /**
     * 任务对象类型
     */
    private String objType;

    /**
     * 任务对象编码
     */
    private String objId;

    /**
     * 任务对象名称
     */
    private String objName;

    /**
     * 任务动作类型
     */
    private String actionType;

    /**
     * 是否阻塞
     */
    private Boolean block;

    /**
     * 任务开始时间
     */
    private Date startDateTime;

    /**
     * 任务结束时间
     */
    private Date endDateTime;

    /**
     * 任务状态
     */
    private String state;

    /**
     * 所属者
     */
    private String owner;

    private Long sequence;

    /**
     * 任务创建时间
     */
    private Date gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 子任务
     */
    private List<SubtaskDO> subtasks;

    public TaskDO() {
        subtasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<SubtaskDO> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubtaskDO> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "TaskDO [id=" + id + ", siteId=" + siteId + ", objType=" + objType + ", objId=" + objId + ", objName="
                + objName + ", actionType=" + actionType + ", block=" + block + ", startDateTime=" + startDateTime
                + ", endDateTime=" + endDateTime + ", state=" + state + ", owner=" + owner + ", sequence=" + sequence
                + ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", subtasks=" + subtasks + "]";
    }

    @Override
    public int compareTo(TaskDO taskDO) {
        return taskDO.getSequence().compareTo(this.sequence);
    }
}
