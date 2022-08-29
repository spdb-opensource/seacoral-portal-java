package com.bsg.upm.param;

import java.io.Serializable;

public class AppDAOParam implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 权限名称
     */
    private String name;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 
     */
    private String code;
    
    /**
     * 显示顺序
     */
    private Integer sequence;
    
    /**
     * 父id
     */
    private Long pid;

	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

	public String getCode() {
		return code;
	}
	

	public void setCode(String code) {
		this.code = code;
	}
	

	public Integer getSequence() {
		return sequence;
	}
	

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	

	public Long getPid() {
		return pid;
	}
	

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	
	@Override
    public String toString() {
		return "AppDO [id="+id+",name="+name+",type="+type+",code="+code+",sequence="+sequence+",pid="+pid+"]";
	}
}
