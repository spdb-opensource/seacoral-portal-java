package com.bsg.upm.query;


import java.io.Serializable;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月8日
 */

public class ImageParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 镜像ID
     */
    private String imageId;

	/**
     * 类型
     */
    private String type;

    /**
     * 是否启用
     */
    private Boolean enabled;


    public String getImageId() {
		return imageId;
	}


	public void setImageId(String imageId) {
		this.imageId = imageId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "ImageParam [imageId=" + imageId + ", type=" + type + ", enabled=" + enabled + "]";
	}

}
