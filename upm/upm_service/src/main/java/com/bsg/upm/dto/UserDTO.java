package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author ZhuXH
 * @date 2019年7月1日
 */
public class UserDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 所属单位
     */
    private String company;

    /**
     * 紧急联系人
     */
    private String emerContact;

    /**
     * 紧急联系人电话
     */
    private String emerTel;

    /**
     * 认证方式
     */
    private String authType;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 组别
     */
    private List<String> groupNames = new ArrayList<>();

    /**
     * 角色
     */
    private RoleDTO role;

    /**
     * 创建
     */
    private InfoDTO created;

    /**
     * 变更
     */
    private InfoDTO modified;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户名
     */
    private String dbuser;
    /**
     * 密码
     */
    private String dbpwd;

    public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpwd() {
		return dbpwd;
	}

	public void setDbpwd(String dbpwd) {
		this.dbpwd = dbpwd;
	}

	/**
     * 获取 自增主键
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户名
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取 用户姓名
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 电话号码
     * 
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话号码
     * 
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取 电子邮件
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取 所属单位
     * 
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属单位
     * 
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取 紧急联系人
     * 
     * @return emerContact
     */
    public String getEmerContact() {
        return emerContact;
    }

    /**
     * 设置紧急联系人
     * 
     * @param emerContact
     */
    public void setEmerContact(String emerContact) {
        this.emerContact = emerContact;
    }

    /**
     * 获取 紧急联系人电话
     * 
     * @return emerTel
     */
    public String getEmerTel() {
        return emerTel;
    }

    /**
     * 设置紧急联系人电话
     * 
     * @param emerTel
     */
    public void setEmerTel(String emerTel) {
        this.emerTel = emerTel;
    }

    /**
     * 获取 认证方式
     * 
     * @return authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * 设置认证方式
     * 
     * @param authType
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * 获取 是否可用
     * 
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 组别
     * 
     * @return groupNames
     */
    public List<String> getGroupNames() {
        return groupNames;
    }

    /**
     * 设置组别
     * 
     * @param groupNames
     */
    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    /**
     * 获取 角色
     * 
     * @return role
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * 设置角色
     * 
     * @param role
     */
    public void setRole(RoleDTO role) {
        this.role = role;
    }

    /**
     * 获取 创建
     * 
     * @return created
     */
    public InfoDTO getCreated() {
        return created;
    }

    /**
     * 设置创建
     * 
     * @param created
     */
    public void setCreated(InfoDTO created) {
        this.created = created;
    }

    /**
     * 获取 变更
     * 
     * @return modified
     */
    public InfoDTO getModified() {
        return modified;
    }

    /**
     * 设置变更
     * 
     * @param modified
     */
    public void setModified(InfoDTO modified) {
        this.modified = modified;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", name=" + name + ", telephone=" + telephone
				+ ", email=" + email + ", company=" + company + ", emerContact=" + emerContact + ", emerTel=" + emerTel
				+ ", authType=" + authType + ", enabled=" + enabled + ", groupNames=" + groupNames + ", role=" + role
				+ ", created=" + created + ", modified=" + modified + ", password=" + password + ", dbuser=" + dbuser
				+ ", dbpwd=" + dbpwd + "]";
	}

    public class RoleDTO {
        /**
         * 角色编码
         */
        private String id;

        /**
         * 角色名称
         */
        private String name;

        /**
         * 获取 角色编码
         * 
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置角色编码
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 角色名称
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置角色名称
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }
        

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "RoleDTO [id=" + id + ", name=" + name + "]";
        }

    }
}
