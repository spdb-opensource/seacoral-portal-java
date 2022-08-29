package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderGroupDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 业务子系统编码
     */
    private String businessSubsystemId;
    /**
     * 类型
     */
    private String type;

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
     * 创建类型
     */
    private String actionType;

    /**
     * 工单状态
     */
    private String status;

    /**
     * 
     */
    private String msg;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 服务组编码
     */
    private String relateAppId;

    /**
     * 服务组任务编码
     */
    private String relateTaskId;

    /**
     * 扩展信息
     */
    private String extendJsonStr;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 修改者
     */
    private String editor;
    /**
     * 业务子系统
     */
    private BusinessSubsystemDO businessSubsystem;


    /**
     * 工单
     */
    private List<OrderDO> orders ;

    /**
     * 删除标志位
     */
    private Boolean isDeleted;
    /**
     * @return the
     */
    public String getId() {
        return id;
    }

    public String getBusinessSubsystemId() {
		return businessSubsystemId;
	}
	

	public void setBusinessSubsystemId(String businessSubsystemId) {
		this.businessSubsystemId = businessSubsystemId;
	}
	

	/**
     * @return the 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @return the 站点编码
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @return the 区域代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @return the 名称
     */
    public String getName() {
        return name;
    }


  

    /**
     * @return the 工单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the 所属者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return the 扩展信息
     */
    public String getExtendJsonStr() {
        return extendJsonStr;
    }

    /**
     * @return the 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @return the 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @return the 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @return the 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param the
     *            id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param 站点编码
     *            the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @param 区域代码
     *            the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @param 名称
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


   

    /**
     * @param 工单状态
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param the
     *            msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @param 所属者
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @param 扩展信息
     *            the extendJsonStr to set
     */
    public void setExtendJsonStr(String extendJsonStr) {
        this.extendJsonStr = extendJsonStr;
    }

    /**
     * @param 创建时间
     *            the gmtCreate to set
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @param 创建者
     *            the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @param 修改时间
     *            the gmtModified to set
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @param 修改者
     *            the editor to set
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * @return the 工单
     */
    public List<OrderDO> getOrders() {
        return orders;
    }

    /**
     * @param 工单
     *            the orders to set
     */
    public void setOrders(List<OrderDO> orders) {
        this.orders = orders;
    }

    

   

    public BusinessSubsystemDO getBusinessSubsystem() {
		return businessSubsystem;
	}
	

	public void setBusinessSubsystem(BusinessSubsystemDO businessSubsystem) {
		this.businessSubsystem = businessSubsystem;
	}
	

	public String getActionType() {
		return actionType;
	}
	

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	

	public String getRelateAppId() {
		return relateAppId;
	}
	

	public void setRelateAppId(String relateAppId) {
		this.relateAppId = relateAppId;
	}
	

	public String getRelateTaskId() {
		return relateTaskId;
	}
	

	public void setRelateTaskId(String relateTaskId) {
		this.relateTaskId = relateTaskId;
	}
	

	public Boolean getIsDeleted() {
		return isDeleted;
	}
	

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "OrderGroupDO [id=" + id + ", businessSubsystemId=" + businessSubsystemId + ", type=" + type
				+ ", siteId=" + siteId + ", areaCode=" + areaCode + ", name=" + name + ", actionType=" + actionType
				+ ", status=" + status + ", msg=" + msg + ", owner=" + owner + ", relateAppId=" + relateAppId
				+ ", relateTaskId=" + relateTaskId + ", extendJsonStr=" + extendJsonStr + ", gmtCreate=" + gmtCreate
				+ ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor=" + editor
				+ ", businessSubsystem=" + businessSubsystem + ", orders=" + orders + ", isDeleted=" + isDeleted + "]";
	}


}
