package com.se4f7.prj301.dto.request;

public class LoginRequestDto {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequestDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginRequestDto() {
		super();
	}

}
