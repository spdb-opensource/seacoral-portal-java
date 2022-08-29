package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 * @author ZhuXH
 * @date 2019年7月1日
 */
public class GroupUserDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String groupId;
    
    private List<String> groupIds = new ArrayList<>();


	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	

	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getGroupId() {
		return groupId;
	}
	

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public List<String> getGroupIds() {
		return groupIds;
	}
	


	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}


	@Override
	public String toString() {
		return "GroupUserDO [id=" + id + ", username=" + username + ", groupId=" + groupId + ", groupIds=" + groupIds
				+ "]";
	}
	

	

	


}
