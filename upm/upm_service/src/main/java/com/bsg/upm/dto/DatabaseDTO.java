package com.bsg.upm.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 数据库传输对象
 * 
 * @author swn
 *
 */
public class DatabaseDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String characterSet;
	private BigInteger size;
	private TableDTO[] tables;

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

	public TableDTO[] getTables() {
		return tables;
	}

	public void setTables(TableDTO[] tables) {
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
		return "DatabaseDTO [name=" + name + ", characterSet=" + characterSet + ", size=" + size + ", tables="
				+ Arrays.toString(tables) + "]";
	}



	public class TableDTO {

		private String name;
		private Long Size;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getSize() {
			return Size;
		}

		public void setSize(Long size) {
			Size = size;
		}

		@Override
		public String toString() {
			return "TableDTO [name=" + name + ", Size=" + Size + "]";
		}

	}
}
