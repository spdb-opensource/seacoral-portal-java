package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderGroupDTO extends BaseDTO implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 站点
     */
    private SiteDTO site;

    /**
     * 区域
     */
    private TypeDTO area;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建类型
     */
    private TypeDTO actionType;

    /**
     * 状态
     */
    private TypeDTO status;

    /**
     * 审批信息
     */
    private String msg;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 创建者
     */
    private String creator;
    
    private String taskId;

    /**
     * 修改时间
     */
    private String gmtModified;

    /**
     * 修改者
     */
    private String editor;

    /**
     * 工单
     */
    private List<OrderDTO> orders = new ArrayList<>();
    
    

//	private Integer sortStatus;
//  public Integer getSortStatus() {
//		return sortStatus;
//	}
//	
//
//	public void setSortStatus(Integer sortStatus) {
//		this.sortStatus = sortStatus;
//	}
    
    public String getTaskId() {
		return taskId;
	}
	

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	

	/**
     * @return the
     */
    public String getId() {
        return id;
    }

    /**
     * @return the 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @return the 站点
     */
    public SiteDTO getSite() {
        return site;
    }

    /**
     * @return the 区域
     */
    public TypeDTO getArea() {
        return area;
    }

    /**
     * @return the 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @return the 创建类型
     */
    public TypeDTO getActionType() {
        return actionType;
    }

    /**
     * @return the 状态
     */
    public TypeDTO getStatus() {
        return status;
    }

    /**
     * @return the 审批信息
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
     * @return the 创建时间
     */
    public String getGmtCreate() {
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
    public String getGmtModified() {
        return gmtModified;
    }

    /**
     * @return the 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @return the 工单
     */
    public List<OrderDTO> getOrders() {
        return orders;
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
     * @param 站点
     *            the site to set
     */
    public void setSite(SiteDTO site) {
        this.site = site;
    }

    /**
     * @param 区域
     *            the area to set
     */
    public void setArea(TypeDTO area) {
        this.area = area;
    }

    /**
     * @param 名称
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param 创建类型
     *            the createType to set
     */
    public void setActionType(TypeDTO actionType) {
        this.actionType = actionType;
    }

    /**
     * @param 状态
     *            the status to set
     */
    public void setStatus(TypeDTO status) {
        this.status = status;
    }

    /**
     * @param 审批信息
     *            the msg to set
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
     * @param 创建时间
     *            the gmtCreate to set
     */
    public void setGmtCreate(String gmtCreate) {
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
    public void setGmtModified(String gmtModified) {
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
     * @param 工单
     *            the orders to set
     */
    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }


	@Override
	public String toString() {
		return "OrderGroupDTO [id=" + id + ", type=" + type + ", site=" + site + ", area=" + area + ", name=" + name
				+ ", actionType=" + actionType + ", status=" + status + ", msg=" + msg + ", owner=" + owner
				+ ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", taskId=" + taskId + ", gmtModified="
				+ gmtModified + ", editor=" + editor + ", orders=" + orders + "]";
	}

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
	

    
	
  

}
