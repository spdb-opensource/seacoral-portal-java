package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 * @author ZhuXH
 * @date 2019年7月1日
 */
public class DbUserDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private String order_id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String userpwd;

    /**
     * 数据库名
     */
    private String dbname;
    
    /**
     * 类型:user,schema
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DbUserDO [order_id=" + order_id + ", username=" + username + ", userpwd=" + userpwd + ", dbname="
				+ dbname + ", type=" + type + ", createTime=" + createTime + "]";
	}


}
