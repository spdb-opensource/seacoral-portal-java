package com.bsg.upm.query;

import java.io.Serializable;

/**
 * 
 * @author Liuxd
 * @date 2019年8月22日
 */
/**
 * @author Administrator
 *
 */
public class UnitCfgsConf implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * 是否允许编码
     */
    private Boolean canset;
    
    /**
     * 是否重启生效
     */
    private Boolean mustRestart;
    
    /**
     * 键名
     */
    private String key;
    
    /**
     * 键值
     */
    private String value;

    
    /**
     * 默认值
     */
    private String defaultValue;
   
    /**
     * 描述
     */
    private String description;
   
    /**
     * 范围
     */
    private String range;


	public Boolean getCanset() {
		return canset;
	}
	

	public void setCanset(Boolean canset) {
		this.canset = canset;
	}
	

	public Boolean getMustRestart() {
		return mustRestart;
	}
	

	public void setMustRestart(Boolean mustRestart) {
		this.mustRestart = mustRestart;
	}
	

	public String getKey() {
		return key;
	}
	

	public void setKey(String key) {
		this.key = key;
	}
	

	public String getValue() {
		return value;
	}
	

	public void setValue(String value) {
		this.value = value;
	}
	

	public String getDefaultValue() {
		return defaultValue;
	}
	

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	

	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getRange() {
		return range;
	}
	

	public void setRange(String range) {
		this.range = range;
	}


	@Override
	public String toString() {
		return "UnitCfgsConf [canset=" + canset + ", mustRestart=" + mustRestart + ", key=" + key + ", value=" + value
				+ ", defaultValue=" + defaultValue + ", description=" + description + ", range=" + range + "]";
	}


    
}
