package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * proxy cmha实体类
 * @author yeht
 * @date 2020年8月1日
 */
public class CmhaProxyDO implements Serializable {

	/**
     * order_id
     */
    private String order_id;
	/**
     * 服务架构模式
     * clonere、plication_async、replication_semi_sync
     */
    private String mode;
    /**
     * 服务架构单元数量
     * 最小3
     */
    private Integer replicas;
    /**
     * 端口
     */
    private Integer port;
    /**
     * cpu
     */
    private Integer cpu;
    /**
     * 内存
     */
    private Long memory;
    /**
     * 存储性能等级
     * high、medium、low
     */
    private String performance;
    
    /**
     * log存储卷容量
     */
    private Long log_capacity;
    
    /**
     * data存储卷容量
     */
    private Long data_capacity;
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 类型
     * cmha、proxy
     */
    private String type;
    
    /**
     * 镜像id
     */
    private String img_id;
    
	public String getImg_id() {
		return img_id;
	}

	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getReplicas() {
		return replicas;
	}

	public void setReplicas(Integer replicas) {
		this.replicas = replicas;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Long getMemory() {
		return memory;
	}

	public void setMemory(Long memory) {
		this.memory = memory;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}


	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Long getLog_capacity() {
		return log_capacity;
	}

	public void setLog_capacity(Long log_capacity) {
		this.log_capacity = log_capacity;
	}

	public Long getData_capacity() {
		return data_capacity;
	}

	public void setData_capacity(Long data_capacity) {
		this.data_capacity = data_capacity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CmhaProxyDO [order_id=" + order_id + ", mode=" + mode + ", replicas=" + replicas + ", port=" + port
				+ ", cpu=" + cpu + ", memory=" + memory + ", performance=" + performance + ", log_capacity="
				+ log_capacity + ", data_capacity=" + data_capacity + ", createTime=" + createTime + ", type=" + type
				+ ", img_id=" + img_id + "]";
	}
    
	public static class VolumeDO {

		/**
	     * 存储类型
	     * data、log
	     */
	    private String type;
	    /**
	     * 存储卷容量
	     */
	    private Integer capacity;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Integer getCapacity() {
			return capacity;
		}
		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}
		@Override
		public String toString() {
			return "VolumeForm [type=" + type + ", capacity=" + capacity + "]";
		}
	}

}

    

