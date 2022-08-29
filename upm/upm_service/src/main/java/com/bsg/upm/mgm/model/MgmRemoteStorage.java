package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.mgm.model.MgmModel.Info;

public class MgmRemoteStorage extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "site")
	private Site site;

	@JSONField(name = "vendor")
	private String vendor;

	@JSONField(name = "model")
	private String model;

	@JSONField(name = "type")
	private String type;

	@JSONField(name = "capacity")
	private BigInteger capacity;

	@JSONField(name = "used")
	private BigInteger used;

	@JSONField(name = "status")
	private String status;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "task")
	private Task task;

	@JSONField(name = "auth")
	private Auth auth;

	@JSONField(name = "created")
	private Info created;

	@JSONField(name = "modified")
	private Info modified;
	

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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigInteger getCapacity() {
		return capacity;
	}

	public void setCapacity(BigInteger capacity) {
		this.capacity = capacity;
	}

	public BigInteger getUsed() {
		return used;
	}

	public void setUsed(BigInteger used) {
		this.used = used;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Info getCreated() {
		return created;
	}

	public void setCreated(Info created) {
		this.created = created;
	}

	public Info getModified() {
		return modified;
	}

	public void setModified(Info modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "MgmRemoteStorage [id=" + id + ", name=" + name + ", site=" + site + ", vendor=" + vendor + ", model="
				+ model + ", type=" + type + ", capacity=" + capacity + ", used=" + used + ", status=" + status
				+ ", enabled=" + enabled + ", desc=" + desc + ", task=" + task + ", auth=" + auth + ", created="
				+ created + ", modified=" + modified + "]";
	}

	public static class Space {
		@JSONField(name = "id")
		private String id;

		@JSONField(name = "enable")
		private boolean enable;

		@JSONField(name = "free")
		private Long free;

		@JSONField(name = "total")
		private Long total;

		@JSONField(name = "lun_num")
		private Long lunNum;

		@JSONField(name = "state")
		private String state;

		/**
		 * 获取id
		 * 
		 * @return id id
		 */
		public String getId() {
			return id;
		}

		/**
		 * 设置id
		 * 
		 * @param id
		 *            id
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 获取enable
		 * 
		 * @return enable enable
		 */
		public boolean isEnable() {
			return enable;
		}

		/**
		 * 设置enable
		 * 
		 * @param enable
		 *            enable
		 */
		public void setEnable(boolean enable) {
			this.enable = enable;
		}

		/**
		 * 获取free
		 * 
		 * @return free free
		 */
		public Long getFree() {
			return free;
		}

		/**
		 * 设置free
		 * 
		 * @param free
		 *            free
		 */
		public void setFree(Long free) {
			this.free = free;
		}

		/**
		 * 获取total
		 * 
		 * @return total total
		 */
		public Long getTotal() {
			return total;
		}

		/**
		 * 设置total
		 * 
		 * @param total
		 *            total
		 */
		public void setTotal(Long total) {
			this.total = total;
		}

		/**
		 * 获取lunNum
		 * 
		 * @return lunNum lunNum
		 */
		public Long getLunNum() {
			return lunNum;
		}

		/**
		 * 设置lunNum
		 * 
		 * @param lunNum
		 *            lunNum
		 */
		public void setLunNum(Long lunNum) {
			this.lunNum = lunNum;
		}

		/**
		 * 获取state
		 * 
		 * @return state state
		 */
		public String getState() {
			return state;
		}

		/**
		 * 设置state
		 * 
		 * @param state
		 *            state
		 */
		public void setState(String state) {
			this.state = state;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Space [id=" + id + ", enable=" + enable + ", free=" + free + ", total=" + total + ", lunNum="
					+ lunNum + ", state=" + state + "]";
		}

	}

	public static class Site {

		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

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

		@Override
		public String toString() {
			return "Site [id=" + id + ", name=" + name + "]";
		}

	}

	public static class Auth {

		@JSONField(name = "ip")
		private String ip;

		@JSONField(name = "port")
		private Integer port;

		@JSONField(name = "username")
		private String username;
		
		@JSONField(name = "vstorename")
		private String vstorename;

		@JSONField(name = "password")
		private String password;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


		public String getVstorename() {
			return vstorename;
		}

		public void setVstorename(String vstorename) {
			this.vstorename = vstorename;
		}

		@Override
		public String toString() {
			return "Auth [ip=" + ip + ", port=" + port + ", username=" + username + ", vstorename=" + vstorename
					+ ", password=" + password + "]";
		}

	}

}
