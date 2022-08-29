package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class ServeUnitRegisParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "category")
    private String category;
    
    @JSONField(name = "container")
    private Container container;
    
    @JSONField(name = "tag")
    private String tag;
    
	@Override
	public String toString() {
		return "ServeUnitRegisParam [name=" + name + ", category=" + category + ", container=" + container + ", tag="
				+ tag + "]";
	}


	public String getName() {
		return name;
	}
	




	public void setName(String name) {
		this.name = name;
	}
	




	public String getCategory() {
		return category;
	}
	




	public void setCategory(String category) {
		this.category = category;
	}
	




	public Container getContainer() {
		return container;
	}
	




	public void setContainer(Container container) {
		this.container = container;
	}
	




	public String getTag() {
		return tag;
	}
	




	public void setTag(String tag) {
		this.tag = tag;
	}
	




	public class Container{
		@JSONField(name = "name")
		private String name;
		@JSONField(name = "host_name")
	    private String hostName;
		@JSONField(name = "category")
	    private String category;
		@JSONField(name = "tag")
	    private String tag;
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getHostName() {
			return hostName;
		}
		
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		
		public String getCategory() {
			return category;
		}
		
		public void setCategory(String category) {
			this.category = category;
		}
		
		public String getTag() {
			return tag;
		}
		
		public void setTag(String tag) {
			this.tag = tag;
		}

		@Override
		public String toString() {
			return "Container [name=" + name + ", hostName=" + hostName + ", category=" + category + ", tag=" + tag
					+ "]";
		}
		
		
	}

   
}
