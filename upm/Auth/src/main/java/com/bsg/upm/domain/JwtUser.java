package com.bsg.upm.domain;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JwtUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增主键
     */
    private String id;

    /**
     * 登录用户名
     */
    private String user_id;

    /**
     * 用于显示的用户姓名
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
     * 角色编码
     */
    private String roleId;

    /**
     * 组别编码
     */
    private List<String> groupIds;

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
     * 所属角色
     */
    private RoleDO role;

    /**
     * 多重角色
     */
    //private ArrayList<String> roles;

    /**
     * 组别
     */
    private List<GroupDO> groups;
    /**
     * 过期时间
     */
    private Date exp;

    /**
     * 介绍
     */
    @Value("Im super man")
    private String introduction;

    /**
     * 头像
     */
    @Value("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
    private String avatar;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmerContact() {
		return emerContact;
	}

	public void setEmerContact(String emerContact) {
		this.emerContact = emerContact;
	}

	public String getEmerTel() {
		return emerTel;
	}

	public void setEmerTel(String emerTel) {
		this.emerTel = emerTel;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
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

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public RoleDO getRole() {
		return role;
	}

	public void setRole(RoleDO role) {
		this.role = role;
	}

	public List<GroupDO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDO> groups) {
		this.groups = groups;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "JwtUser [id=" + id + ", user_id=" + user_id + ", name=" + name + ", telephone=" + telephone + ", email="
				+ email + ", company=" + company + ", emerContact=" + emerContact + ", emerTel=" + emerTel
				+ ", authType=" + authType + ", enabled=" + enabled + ", roleId=" + roleId + ", groupIds=" + groupIds
				+ ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", gmtModified=" + gmtModified + ", editor="
				+ editor + ", role=" + role + ", groups=" + groups + ", exp=" + exp + ", introduction=" + introduction
				+ ", avatar=" + avatar + "]";
	}


}
