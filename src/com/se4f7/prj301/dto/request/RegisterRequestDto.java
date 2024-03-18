package com.se4f7.prj301.dto.request;

public class RegisterRequestDto {

	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private int role;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public RegisterRequestDto(String username, String password, String lastName, String firstName) {
		super();
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public RegisterRequestDto() {
		super();
	}

}
