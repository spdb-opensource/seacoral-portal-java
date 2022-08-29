package com.bsg.upm.dto;
/**
 * 
 * @author yeht
 * @date 2020年7月8日
 */
public class SchemaDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库名schema
     */
    private String dbname;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	@Override
	public String toString() {
		return "SchemaDTO [dbname=" + dbname + "]";
	}
}
