package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class SanDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * SAN品牌编码
     */
    private Long sanVendorId;

    /**
     * 名称
     */
    private String name;

    /**
     * 管理域名
     */
    private String domain;

    /**
     * 起始LUN ID
     */
    private Integer lunStart;

    /**
     * 结束LUN ID
     */
    private Integer lunEnd;

    /**
     * 起始主机 LUN ID
     */
    private Integer hostLunStart;

    /**
     * 结束主机LUN ID
     */
    private Integer hostLunEnd;

    /**
     * 描述
     */
    private String description;

    /**
     * 管理编码
     */
    private String relateId;

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
     * san品牌
     */
    private SanVendorDO sanVendor;

    /**
     * 获取
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * 获取SAN品牌编码
     * 
     * @return sanVendorId SAN品牌编码
     */
    public Long getSanVendorId() {
        return sanVendorId;
    }

    /**
     * 设置SAN品牌编码
     * 
     * @param sanVendorId
     *            SAN品牌编码
     */
    public void setSanVendorId(Long sanVendorId) {
        this.sanVendorId = sanVendorId;
    }

    /**
     * 获取名称
     * 
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *            名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取管理域名
     * 
     * @return domain 管理域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置管理域名
     * 
     * @param domain
     *            管理域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取起始LUNID
     * 
     * @return lunStart 起始LUNID
     */
    public Integer getLunStart() {
        return lunStart;
    }

    /**
     * 设置起始LUNID
     * 
     * @param lunStart
     *            起始LUNID
     */
    public void setLunStart(Integer lunStart) {
        this.lunStart = lunStart;
    }

    /**
     * 获取结束LUNID
     * 
     * @return lunEnd 结束LUNID
     */
    public Integer getLunEnd() {
        return lunEnd;
    }

    /**
     * 设置结束LUNID
     * 
     * @param lunEnd
     *            结束LUNID
     */
    public void setLunEnd(Integer lunEnd) {
        this.lunEnd = lunEnd;
    }

    /**
     * 获取起始主机LUNID
     * 
     * @return hostLunStart 起始主机LUNID
     */
    public Integer getHostLunStart() {
        return hostLunStart;
    }

    /**
     * 设置起始主机LUNID
     * 
     * @param hostLunStart
     *            起始主机LUNID
     */
    public void setHostLunStart(Integer hostLunStart) {
        this.hostLunStart = hostLunStart;
    }

    /**
     * 获取结束主机LUNID
     * 
     * @return hostLunEnd 结束主机LUNID
     */
    public Integer getHostLunEnd() {
        return hostLunEnd;
    }

    /**
     * 设置结束主机LUNID
     * 
     * @param hostLunEnd
     *            结束主机LUNID
     */
    public void setHostLunEnd(Integer hostLunEnd) {
        this.hostLunEnd = hostLunEnd;
    }

    /**
     * 获取描述
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取管理编码
     * 
     * @return relateId 管理编码
     */
    public String getRelateId() {
        return relateId;
    }

    /**
     * 设置管理编码
     * 
     * @param relateId
     *            管理编码
     */
    public void setRelateId(String relateId) {
        this.relateId = relateId;
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
     * 获取san品牌
     * 
     * @return sanVendor san品牌
     */
    public SanVendorDO getSanVendor() {
        return sanVendor;
    }

    /**
     * 设置san品牌
     * 
     * @param sanVendor
     *            san品牌
     */
    public void setSanVendor(SanVendorDO sanVendor) {
        this.sanVendor = sanVendor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SanDO [id=" + id + ", siteId=" + siteId + ", sanVendorId=" + sanVendorId + ", name=" + name
                + ", domain=" + domain + ", lunStart=" + lunStart + ", lunEnd=" + lunEnd + ", hostLunStart="
                + hostLunStart + ", hostLunEnd=" + hostLunEnd + ", description=" + description + ", relateId="
                + relateId + ", gmtCreate=" + gmtCreate + ", creator=" + creator + ", gmtModified=" + gmtModified
                + ", editor=" + editor + ", sanVendor=" + sanVendor + "]";
    }

}
