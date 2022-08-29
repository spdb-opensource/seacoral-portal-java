package com.bsg.upm.dto;

import java.io.Serializable;

import com.bsg.upm.domain.UserDO;

/**
 * @author Administrator
 *
 */
public class UnitTaskDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private UserDO userDTO;
	private String action;
	private String status;
	private String error;
	private String startDateTime;
	private String endDateTime;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public UserDO getUserDTO() {
		return userDTO;
	}
	
	public void setUserDTO(UserDO userDTO) {
		this.userDTO = userDTO;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getStartDateTime() {
		return startDateTime;
	}
	
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public String getEndDateTime() {
		return endDateTime;
	}
	
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public String toString() {
		return "UnitTaskDTO [id=" + id + ", userDTO=" + userDTO + ", action=" + action + ", status=" + status
				+ ", error=" + error + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + "]";
	}
	
	
	

}
