package com.se4f7.prj301.dto.response;

import com.se4f7.prj301.enums.UserStatus;

public class LoginResponseDto {

	private int id;
	private String username;
	private UserStatus status;
	private int role;

	public int getId() {
		return id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public LoginResponseDto(int id, String username, UserStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.status = status;
	}

	public LoginResponseDto() {
		super();
	}

}
