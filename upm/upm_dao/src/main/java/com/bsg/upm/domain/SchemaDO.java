package com.bsg.upm.domain;
/**
 * schema实体类
 * @author yeht
 * @date 2020年7月8日
 */
public class SchemaDO {

	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 库名
     */
    private String dbname;
    
    /**
     * 工单id
     */
    private String order_id;
    


	/**
     * 字符集
     */
    private String characterSet;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}
	
    public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "SchemaDO [dbname=" + dbname + ", characterSet=" + characterSet + "]";
	}
}
