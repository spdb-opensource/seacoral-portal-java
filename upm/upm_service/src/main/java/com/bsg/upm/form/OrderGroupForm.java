package com.bsg.upm.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderGroupForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;
    
    /**
     * 业务系统名
     */
    private String businessSystemName;

    /**
     * 子系统名
     */
    private String businessSubsystemName;
    /**
     * 站点编码
     */
    private String siteId;

    /**
     * 区域代码
     */
    private String areaCode;

    /**
     * 名称
     */
    private String name;
    /**
     * 工单
     */
    private List<OrderForm> orders = new ArrayList<>();

    /**
     * @return the 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the 站点编码
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param 站点编码
     *            the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the 区域代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param 区域代码
     *            the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBusinessSystemName() {
		return businessSystemName;
	}
	

	public void setBusinessSystemName(String businessSystemName) {
		this.businessSystemName = businessSystemName;
	}
	

	public String getBusinessSubsystemName() {
		return businessSubsystemName;
	}
	

	public void setBusinessSubsystemName(String businessSubsystemName) {
		this.businessSubsystemName = businessSubsystemName;
	}
	

	/**
     * @return the 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param 名称
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the 工单
     */
    public List<OrderForm> getOrders() {
        return orders;
    }

    /**
     * @param 工单
     *            the orders to set
     */
    public void setOrders(List<OrderForm> orders) {
        this.orders = orders;
    }

	@Override
	public String toString() {
		return "OrderGroupForm [type=" + type + ", businessSystemName=" + businessSystemName
				+ ", businessSubsystemName=" + businessSubsystemName + ", siteId=" + siteId + ", areaCode=" + areaCode
				+ ", name=" + name + ", orders=" + orders + "]";
	}

 

}
