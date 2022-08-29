package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年8月13日
 */
public class MgmBackupRestoreFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JSONField(name = "backup_file_id")
	private String backupFileId;


	public String getBackupFileId() {
		return backupFileId;
	}
	


	public void setBackupFileId(String backupFileId) {
		this.backupFileId = backupFileId;
	}



	@Override
	public String toString() {
		return "MgmBackupRestoreFile [backupFileId=" + backupFileId + "]";
	}
	

	

}
