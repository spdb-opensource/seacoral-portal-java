package com.bsg.upm.param;

import java.io.Serializable;
import java.util.Date;

public class OperateLogDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 操作模块
     */
    private String objType;

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

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

    

    public String getObjType() {
		return objType;
	}
	

	public void setObjType(String objType) {
		this.objType = objType;
	}
	

	/**
     * 获取起始时间
     * 
     * @return startDate 起始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置起始时间
     * 
     * @param startDate
     *            起始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     * 
     * @return endDate 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     * 
     * @param endDate
     *            结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OperateLogDAOParam [siteId=" + siteId + ", objType=" + objType + ", startDate=" + startDate + ", endDate="
                + endDate + "]";
    }

}
