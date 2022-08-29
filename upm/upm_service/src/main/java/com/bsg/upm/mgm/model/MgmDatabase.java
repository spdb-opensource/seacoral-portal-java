package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmDatabase extends MgmModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "character_set")
	private String characterSet;
	
	@JSONField(name = "size")
	private BigInteger size;

	@JSONField(name = "tables")
	private Table[] tables;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

	public Table[] getTables() {
		return tables;
	}

	public void setTables(Table[] tables) {
		this.tables = tables;
	}
 
	public BigInteger getSize() {
		return size;
	}
	

	public void setSize(BigInteger size) {
		this.size = size;
	}
	


	@Override
	public String toString() {
		return "MgmDatabase [name=" + name + ", characterSet=" + characterSet + ", size=" + size + ", tables="
				+ Arrays.toString(tables) + "]";
	}



	public static class Table {
		@JSONField(name = "name")
		private String name;

		@JSONField(name = "size")
		private Integer size;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}

		@Override
		public String toString() {
			return "Table [name=" + name + ", size=" + size + "]";
		}

	}

}
