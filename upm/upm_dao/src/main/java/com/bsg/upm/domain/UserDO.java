package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 * @author ZhuXH
 * @date 2019年7月1日
 */
public class UserDO implements Serializable {

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
    private String password;

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
     * 组别
     */
    private List<GroupDO> groups;

    /**
     * 获取自增主键
     * 
     * @return id 自增主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置自增主键
     * 
     * @param id
     *            自增主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户名
     * 
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * 
     * @param username
     *            用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     * 
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * 
     * @param password
     *            密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户姓名
     * 
     * @return name 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     * 
     * @param name
     *            用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取电话号码
     * 
     * @return telephone 电话号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话号码
     * 
     * @param telephone
     *            电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取电子邮件
     * 
     * @return email 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     * 
     * @param email
     *            电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取所属单位
     * 
     * @return company 所属单位
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属单位
     * 
     * @param company
     *            所属单位
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取紧急联系人
     * 
     * @return emerContact 紧急联系人
     */
    public String getEmerContact() {
        return emerContact;
    }

    /**
     * 设置紧急联系人
     * 
     * @param emerContact
     *            紧急联系人
     */
    public void setEmerContact(String emerContact) {
        this.emerContact = emerContact;
    }

    /**
     * 获取紧急联系人电话
     * 
     * @return emerTel 紧急联系人电话
     */
    public String getEmerTel() {
        return emerTel;
    }

    /**
     * 设置紧急联系人电话
     * 
     * @param emerTel
     *            紧急联系人电话
     */
    public void setEmerTel(String emerTel) {
        this.emerTel = emerTel;
    }

    /**
     * 获取认证方式
     * 
     * @return authType 认证方式
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * 设置认证方式
     * 
     * @param authType
     *            认证方式
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * 获取是否可用
     * 
     * @return enabled 是否可用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     *            是否可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取角色编码
     * 
     * @return roleId 角色编码
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编码
     * 
     * @param roleId
     *            角色编码
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取组别编码
     * 
     * @return groupIds 组别编码
     */
    public List<String> getGroupIds() {
        return groupIds;
    }

    /**
     * 设置组别编码
     * 
     * @param groupIds
     *            组别编码
     */
    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    /**
     * 获取创建时间
     * 
     * @return gmtCreate 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     * 
     * @param gmtCreate
     *            创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取创建者
     * 
     * @return creator 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     * 
     * @param creator
     *            创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取修改时间
     * 
     * @return gmtModified 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     * 
     * @param gmtModified
     *            修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取修改者
     * 
     * @return editor 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置修改者
     * 
     * @param editor
     *            修改者
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * 获取所属角色
     * 
     * @return role 所属角色
     */
    public RoleDO getRole() {
        return role;
    }

    /**
     * 设置所属角色
     * 
     * @param role
     *            所属角色
     */
    public void setRole(RoleDO role) {
        this.role = role;
    }

    /**
     * 获取组别
     * 
     * @return groups 组别
     */
    public List<GroupDO> getGroups() {
        return groups;
    }

    /**
     * 设置组别
     * 
     * @param groups
     *            组别
     */
    public void setGroups(List<GroupDO> groups) {
        this.groups = groups;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDO [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
                + ", telephone=" + telephone + ", email=" + email + ", company=" + company + ", emerContact="
                + emerContact + ", emerTel=" + emerTel + ", authType=" + authType + ", enabled=" + enabled + ", roleId="
                + roleId + ", groupIds=" + groupIds + ", gmtCreate=" + gmtCreate + ", creator=" + creator
                + ", gmtModified=" + gmtModified + ", editor=" + editor + ", role=" + role + ", groups=" + groups + "]";
    }

}
