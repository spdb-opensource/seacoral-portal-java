package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.List;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.bsg.upm.dto.BaseDTO.InfoDTO;

/**
 * 站点数据传输对象
 * 
 * @author HCK
 *
 */
public class SiteDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一编码
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 地域
	 */
	private TypeDTO region;

	/**
	 * 类型
	 */
	private TypeDTO type;

	/**
	 * 域名
	 */
	private String domain;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 站点描述
	 */
	private String description;

	/**
	 * 创建
	 */
	private InfoDTO created;

	/**
	 * 变更
	 */
	private InfoDTO modified;
	
	private String 	image_registry;

	public String getImage_registry() {
		return image_registry;
	}

	public void setImage_registry(String image_registry) {
		this.image_registry = image_registry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeDTO getRegion() {
		return region;
	}

	public void setRegion(TypeDTO region) {
		this.region = region;
	}

	public TypeDTO getType() {
		return type;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InfoDTO getCreated() {
		return created;
	}

	public void setCreated(InfoDTO created) {
		this.created = created;
	}

	public InfoDTO getModified() {
		return modified;
	}

	public void setModified(InfoDTO modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "SiteDTO [id=" + id + ", name=" + name + ", region=" + region + ", type=" + type + ", domain=" + domain
				+ ", port=" + port + ", version=" + version + ", status=" + status + ", description=" + description
				+ ", created=" + created + ", modified=" + modified + ", image_registry=" + image_registry + "]";
	}

}
