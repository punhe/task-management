package com.se4f7.prj301.service;

import java.sql.SQLException;
import java.util.List;

import com.se4f7.prj301.dto.request.LoginRequestDto;
import com.se4f7.prj301.dto.request.RegisterRequestDto;
import com.se4f7.prj301.dto.response.LoginResponseDto;
import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.entities.UserEntity;

public interface AuthService {

	public boolean register(RegisterRequestDto registerRequestDto) throws SQLException;

	public boolean updateUser(int id, String firstName, String lastName, int status, int role);

	public boolean change(String username, String password);

	public boolean update(String userName, String firstName, String lastName);

	public int getUserRole(String username);

	public int getUserStatus(String username);

	public int getUserIdByUsername(String userName);

	public int count();

	public UserEntity getUserById(int id);

	public LoginResponseDto login(LoginRequestDto loginRequestDto) throws SQLException;

	public UserResponseDto getUserInfo(String username);

	public List<UserEntity> getAllUser(int page);

	public List<UserEntity> getFilterStatus(int status);

	public List<UserEntity> getFilterRole(int role);

	public List<UserEntity> searchUserByName(String username);

	public List<UserEntity> getAllRoleUser();

	public String getUser(int id);

}
