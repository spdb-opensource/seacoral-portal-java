package com.bsg.upm.query;


import java.io.Serializable;

/**
 * 
 * @author yeht
 * @date 2021年1月28日
 */

public class HostParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    

	/**
     * 类型
     */
    private String type;



	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}




	@Override
	public String toString() {
		return "HostParam [type=" + type + "]";
	}

}
