package com.bsg.upm.query;

import java.io.Serializable;

public class OperateLogParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 操作者
     */
    private String owner;

    /**
     * 操作模块
     */
    private String objType;

    /**
     * 起始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取操作者
     * 
     * @return owner 操作者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置操作者
     * 
     * @param owner
     *            操作者
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取操作模块
     * 
     * @return module 操作模块
     */
    

    /**
     * 设置操作模块
     * 
     * @param module
     *            操作模块
     */
    

    /**
     * 获取起始时间
     * 
     * @return start 起始时间
     */
    public Long getStart() {
        return start;
    }

    public String getObjType() {
		return objType;
	}
	

	public void setObjType(String objType) {
		this.objType = objType;
	}
	

	/**
     * 设置起始时间
     * 
     * @param start
     *            起始时间
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     * 
     * @return end 结束时间
     */
    public Long getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     * 
     * @param end
     *            结束时间
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OperateLogParam [siteId=" + siteId + ", owner=" + owner + ", objType=" + objType + ", start=" + start
                + ", end=" + end + "]";
    }

}
